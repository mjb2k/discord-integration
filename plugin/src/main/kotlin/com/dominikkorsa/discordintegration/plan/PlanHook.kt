package com.dominikkorsa.discordintegration.plan

import com.djrapitops.plan.capability.CapabilityService
import com.djrapitops.plan.extension.ExtensionService
import com.djrapitops.plan.extension.Caller
import com.dominikkorsa.discordintegration.DiscordIntegration
import java.util.Optional

class PlanHook(private var plugin: DiscordIntegration) {
    var caller: Optional<Caller>? = null

     fun hookIntoPlan() {
        if (!this.areAllCapabilitiesAvailable()) return
        registerDataExtension()
    }

    private fun areAllCapabilitiesAvailable(): Boolean {
        val capabilities = CapabilityService.getInstance()
        return capabilities.hasCapability("DATA_EXTENSION_VALUES")
    }

    private fun registerDataExtension() {
        try {
            caller = ExtensionService.getInstance().register(plugin.discordLinkingDataExtension)
        } catch (e: IllegalStateException) {
            plugin.logger.warning("Plan is not enabled to handle discordLinkingDataExtension")
            // Plan is not enabled, handle exception
        } catch (e: IllegalArgumentException) {
            plugin.logger.severe(e.toString())
        }
    }
}