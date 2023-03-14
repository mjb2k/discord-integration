package com.dominikkorsa.discordintegration.imagemaps

import com.dominikkorsa.discordintegration.DiscordIntegration
import org.bukkit.Bukkit
import java.io.File
import java.nio.file.Path

/* This class copies over the temporary file to a permanent location using
* the filename provided by the user (modified to be lower and spaces replaced with _) */
class ImageMapsMigrator(private val plugin: DiscordIntegration) {

    private val imageMapsInstalled
        get() = Bukkit.getPluginManager().getPlugin("ImageMaps") != null

    fun migrateImage(file: Path, filename: String, basePath: String): Boolean {
        if (!imageMapsInstalled) {
            plugin.logger.warning("ImageMaps is not installed, ignoring request")
            return false
        }
        // will catch exceptions related to copying the file over (in case config is not setup correctly)
        try {
            file.toFile().copyTo(File(basePath + filename))
        } catch (e: Exception) {
            plugin.logger.warning(e.toString())
            return false
        }
        return true
    }
}
