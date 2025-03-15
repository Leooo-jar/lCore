package com.leo.lCore.Commands.UserCommands;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplayCommand implements CommandExecutor {


    //                /r <Message>
    private Main main;

    public ReplayCommand(Main main) {

        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");


                        }

                        player.sendMessage(ChatColor.AQUA + "You -> " + ChatColor.DARK_AQUA + target.getName() + ChatColor.WHITE + ": " + builder);
                        target.sendMessage( ChatColor.DARK_AQUA + player.getName() + ChatColor.AQUA + "-> You: " + ChatColor.WHITE + builder);
                        main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());


                    } else {
                        player.sendMessage(ChatColor.RED + "The person you messaged has gone offline!");
                    }



                } else {
                    player.sendMessage(ChatColor.RED + "You haven't message anyone yet!");


                }


            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /r <Message>");
            }



        }


        return false;
    }
}
