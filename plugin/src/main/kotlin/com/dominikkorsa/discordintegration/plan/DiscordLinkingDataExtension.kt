package com.dominikkorsa.discordintegration.plan

import com.djrapitops.plan.extension.CallEvents
import com.djrapitops.plan.extension.DataExtension
import com.djrapitops.plan.extension.annotation.PluginInfo
import com.djrapitops.plan.extension.annotation.StringProvider

import com.dominikkorsa.discordintegration.DiscordIntegration
import discord4j.core.`object`.entity.Member
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.apache.commons.lang.ObjectUtils.Null

import org.bukkit.entity.Player
import org.junit.Test.None
import reactor.core.publisher.Mono


@PluginInfo(
    name = "DiscordIntegration"
)
class DiscordLinkingDataExtension(private var plugin: DiscordIntegration) : DataExtension {

    // triggers the update for the player
    override fun callExtensionMethodsOn(): Array<CallEvents?>? {
        return arrayOf(
            CallEvents.PLAYER_JOIN,
            CallEvents.PLAYER_LEAVE
        )
    }

    @StringProvider(
        text = "Discord ID of player"
    )
    suspend fun linkedPlayer(player: Player): String {
        // we can easily retrieve the discordId of the player (if it exists)
        val discordId = plugin.db.getDiscordId(player.uniqueId) ?: return "unlinked"

        // now we need to identify the username associated with this id
        // we can do this by searching all of the "guilds" the bot is a part of
        // and using the existing getMember() function the client has
        val guilds = plugin.client.getGuilds()
        var member: Member? = null
        guilds?.asFlow()?.map {guild ->
            if (member == null) //once we find member we can stop searching
                member = guild.getMemberById(discordId).awaitFirstOrNull()
        }
        var username = member?.username

        // finally we return the username if we found one, if not then we return "not found" for this user
        // this can happen if the user has linked their discord but is no longer in the server
        return username?.toString() ?: "user not found on discord server"
    }
}