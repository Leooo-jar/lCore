package com.leo.lCore.Commands.AdminCommands;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoMessagesCommand implements CommandExecutor {

    private int countdown = 10;
    private Main main;

    public AutoMessagesCommand(Main main) {

        this.main = main;


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;

            BukkitRunnable task = new BukkitRunnable() {


                @Override
                public void run() {
                    if (countdown > 1) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + "This is a test!");
                        countdown--;
                        this.cancel();


                    } else {
                        if (countdown > 0) {
                            Bukkit.broadcastMessage(ChatColor.RED + "This a test2!");
                            this.cancel();


                        }


                    }

                }
            };
            task.runTaskTimer(main, 0L, 20L);


        }


        return false;
    }
}
