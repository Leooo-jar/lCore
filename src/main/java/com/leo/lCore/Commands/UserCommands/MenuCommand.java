package com.leo.lCore.Commands.UserCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;


            Inventory inv = Bukkit.createInventory(player, 45, ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu");
// TELEPORT
            ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
            ItemMeta teleportMeta = teleport.getItemMeta();
            teleportMeta.setDisplayName(ChatColor.BLUE + "Random Teleport");
            teleportMeta.setLore(Arrays.asList(ChatColor.GRAY + "Teleport to a random player!"));
            teleport.setItemMeta(teleportMeta);

            inv.setItem(20, teleport);


            // KILL YOURSELF

            ItemStack kys = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta kysMeta = kys.getItemMeta();
            kysMeta.setDisplayName(ChatColor.DARK_RED + "Kill Yourself");
            kysMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kills You"));
            kys.setItemMeta(kysMeta);

            inv.setItem(22, kys);

            // CLEAR INVENTORY

            ItemStack clear = new ItemStack(Material.BUCKET);
            ItemMeta clearMeta = clear.getItemMeta();
            clearMeta.setDisplayName(ChatColor.WHITE + "Clear Inventory");
            clearMeta.setLore(Arrays.asList(ChatColor.GRAY + "Clears you inventory"));
            clear.setItemMeta(clearMeta);

            inv.setItem(24, clear);


            // CLOSE BUTTOM

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.GOLD + "Close!");
            close.setItemMeta(closeMeta);

            inv.setItem(0, close);

            // FRAME

            ItemStack frame = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
                inv.setItem(i, frame);


            }


            player.openInventory(inv);


        }


        return false;
    }
}
