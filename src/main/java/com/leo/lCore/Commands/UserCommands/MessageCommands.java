package com.leo.lCore.Commands.UserCommands;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.event.WindowStateListener;

public class MessageCommands implements CommandExecutor {

    //               /msg <Player> <Message>
    private Main main;

    public MessageCommands(Main main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");


                    }

                    player.sendMessage(ChatColor.AQUA + "You -> " + ChatColor.DARK_AQUA + target.getName() + ChatColor.WHITE + ": " + builder);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getName() + ChatColor.AQUA + "-> You: " + ChatColor.WHITE + builder);

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                } else {
                    player.sendMessage(ChatColor.RED + "This player was not found");
                }


            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /msg <Player> <Message>");
            }


        }


        return false;
    }
}
