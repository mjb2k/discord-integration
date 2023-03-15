package com.dominikkorsa.discordintegration.plan

import com.djrapitops.plan.extension.CallEvents
import com.djrapitops.plan.extension.DataExtension
import com.djrapitops.plan.extension.annotation.PluginInfo
import com.djrapitops.plan.extension.annotation.StringProvider

import com.dominikkorsa.discordintegration.DiscordIntegration


import java.util.UUID


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
        text = "Discord ID of player",
        playerName = true
    )
    fun linkedPlayer(playerId: UUID): String {
        // we can easily retrieve the discordId of the player (if it exists
        plugin.db.getDiscordId(playerId) ?: return "unlinked"

        return plugin.db.getTagFromPlayerUUID(playerId) ?: "error with fetching tag"
    }
}