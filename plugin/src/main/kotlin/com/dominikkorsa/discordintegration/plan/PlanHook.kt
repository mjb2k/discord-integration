package com.dominikkorsa.discordintegration.plan

import com.djrapitops.plan.capability.CapabilityService
import com.djrapitops.plan.extension.ExtensionService
import com.dominikkorsa.discordintegration.DiscordIntegration

class PlanHook(private var plugin: DiscordIntegration) {
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
            ExtensionService.getInstance().register(plugin.discordLinkingDataExtension)
        } catch (e: IllegalStateException) {
            plugin.logger.warning("Plan is not enabled to handle discordLinkingDataExtension")
            // Plan is not enabled, handle exception
        } catch (e: IllegalArgumentException) {
            plugin.logger.severe(e.toString())
        }
    }
}