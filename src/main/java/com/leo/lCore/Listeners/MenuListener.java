package com.leo.lCore.Listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    private org.bukkit.entity.Player Player;

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu")
                && e.getCurrentItem() != null) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()) {
                case 0:
                    break;
                case 20: // Random tp
                    Random random = new Random();
                    Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                    player.teleport(target);
                    player.sendMessage(ChatColor.AQUA + "You teleported to  " + target.getName() + "!");
                    break;
                case 22: // Kill yourself
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "You killed yourself!");

                    break;
                case 24: // Clear Inventory
                    player.closeInventory();
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.RED + "You cleared your inventory!");
                 return;
                default:
                    return;
            }

            player.closeInventory();

        }


    }




}
