package com.leo.lCore.Commands.AdminCommands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BanWaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return false;
            }

            List<String> playersToBan = new ArrayList<>();

            playersToBan.add("Test1");
            playersToBan.add("Test2");

            for (String playerName : playersToBan) {
                Player targetPlayer = Bukkit.getPlayer(playerName);
                if (targetPlayer != null) {
                    targetPlayer.kickPlayer(ChatColor.GOLD + "You have been banned in a banwave.");
                    Bukkit.getBanList(BanList.Type.NAME).addBan(playerName, "Banned in Banwave", null, null);
                    player.sendMessage("Player", playerName + "has been banned");

                }

            }


        }



        return false;
    }
}
