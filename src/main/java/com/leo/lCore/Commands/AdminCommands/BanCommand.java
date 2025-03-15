package com.leo.lCore.Commands.AdminCommands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;


            if (args.length < 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));


                    Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + reason, null, null);
                    player.kickPlayer(ChatColor.RED + reason);

                    player.sendMessage(ChatColor.GREEN + "Successfully banned " + target.getName() + " for: " + reason);



                }


            } else {
                player.sendMessage(ChatColor.GOLD + "This player is not online!");
            }


        } else {
            player.sendMessage(ChatColor.RED + "Use /ban <Player> <Reason>");
        }


        return false;
    }
}
