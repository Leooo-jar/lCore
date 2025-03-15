package com.leo.lCore.Commands.UserCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_GREEN + "-----------------------");
                sender.sendMessage(ChatColor.GREEN + "Survival Core Made by Leo");
                sender.sendMessage(ChatColor.GREEN + "Version:" + ChatColor.GOLD +" 1.0 BETA");
                sender.sendMessage(ChatColor.DARK_GREEN + "-----------------------");
                return true;


            }

            sender.sendMessage(ChatColor.GRAY + "This command can only be executed by players!");
            return true;

        }


        return false;
    }
}
