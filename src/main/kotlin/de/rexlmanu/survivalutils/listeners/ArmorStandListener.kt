package de.rexlmanu.survivalutils.listeners

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.plugin.java.JavaPlugin

class ArmorStandListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    @EventHandler(priority = EventPriority.LOW)
    fun onPlayerInteractAtEntityEvent(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        if (!event.isCancelled) {
            if (event.player.inventory.itemInMainHand == null) return
            if (event.rightClicked.type == EntityType.ARMOR_STAND && event.player.inventory.itemInMainHand.type == Material.STICK) {
                val armorStand = event.rightClicked as ArmorStand
                armorStand.setArms(!armorStand.hasArms())
                playSound(Sound.UI_BUTTON_CLICK, player)
                cancel(event)
                if (armorStand.hasArms())
                    player.sendMessage("§7Der ArmorStand hat jetzt Arme.")
                else
                    player.sendMessage("§7Der ArmorStand hat jetzt §ckeine §7Arme mehr.")

            }
        }
    }

    private fun playSound(sound: Sound, player: Player) = player.playSound(player.location, sound, 1f, 1f)
}