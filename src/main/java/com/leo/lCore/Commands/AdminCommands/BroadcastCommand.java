package com.leo.lCore.Commands.AdminCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length >= 1) {
                String message = String.join(" ", args);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendTitle(ChatColor.YELLOW + "Broadcast", ChatColor.RED + message, 10, 70, 20);


                }

                sender.sendMessage(ChatColor.GRAY + "The message has been sent to all players");


            } else {
                sender.sendMessage(ChatColor.RED + "Use /broadcast <Message>");


            }


        }


        return false;
    }
}
