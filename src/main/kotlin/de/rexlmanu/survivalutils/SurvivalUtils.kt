package de.rexlmanu.survivalutils

import de.rexlmanu.survivalutils.listeners.ArmorStandListener
import de.rexlmanu.survivalutils.listeners.InventoryFullListener
import org.bukkit.plugin.java.JavaPlugin

class SurvivalUtils : JavaPlugin() {

    override fun onEnable() {
        InventoryFullListener(this)
        ArmorStandListener(this)
    }
}