package com.leo.lCore.Commands.AdminCommands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;


            if (args.length == 1) ;
            String targetName = args[0];

            if (Bukkit.getBanList(BanList.Type.NAME).isBanned(targetName)) {
                Bukkit.getBanList(BanList.Type.NAME).pardon(targetName);

                player.sendMessage(ChatColor.GREEN + "Successfully UnBanned " + targetName);

            } else {
                player.sendMessage(ChatColor.RED + "This player is not banned!");

            }


        } else {
            player.sendMessage(ChatColor.RED + "Use /unban <player>");
        }


        return false;
    }
}
