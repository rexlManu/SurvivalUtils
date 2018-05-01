package de.rexlmanu.survivalutils.listeners

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class InventoryFullListener(javaPlugin: JavaPlugin) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, javaPlugin)
    }

    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        var player = event.player
        if (!event.isCancelled) {
            if (event.isDropItems) {
                if (event.block.type == Material.STONE) return
                if (isInventoryFull(player.inventory, event.block.type)) {
                    player.playSound(player.location, Sound.BLOCK_NOTE_BASS, 1f, 1f)
                    player.sendTitle("§4Achtung!", "§7Dein Inventar ist voll.", 10, 20, 5)
                }
            }

        }
    }

    fun isInventoryFull(inventory: Inventory, material: Material): Boolean {
        var slots = 0
        for (content in inventory.contents) {
            if (content == null) break
            if (content.type == material && content.amount < content.maxStackSize) return false
            slots++
        }
        return slots >= 35
    }
}