package com.leo.lCore.Commands.AdminCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            if (args.length >= 1) {

                String message = String.join(" ", args);

                for (Player player : Bukkit.getOnlinePlayers());
                sender.sendMessage(ChatColor.DARK_RED + "ALERT:", ChatColor.RED + message);


            } else {
                sender.sendMessage(ChatColor.GRAY + "Use /alert <Message>");


            }



        }



        return false;
    }
}
