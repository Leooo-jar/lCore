package com.leo.lCore.Commands.AdminCommands;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TestCommand implements CommandExecutor {

   int countdown = 600;
    private Main main;
    private BukkitTask activateTask;

    public TestCommand(Main main) {

        this.main = main;


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (activateTask != null && !activateTask.isCancelled())  {
                player.sendMessage(ChatColor.RED + "A restart is already in progress!");
                return true;

            }

            BukkitRunnable task = new BukkitRunnable() {
          int timeLeft = countdown;

                @Override
                public void run() {
                    if (timeLeft == 600) {
                        Bukkit.broadcastMessage(ChatColor.RED + "\uD83D\uDD14" + ChatColor.GOLD + " the server restarts in 30 seconds");


                    } else if (timeLeft == 300) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + "the server restarts in 20 seconds");


                    } else if (timeLeft == 200) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + "the server restarts in 10 seconds");


                    } else if (timeLeft <= 10 && timeLeft > 0) {
                        Bukkit.broadcastMessage(ChatColor.RED + "The server will restart in " + countdown + " seconds!");


                    } else if (timeLeft == 0) {
                        Bukkit.broadcastMessage(ChatColor.RED + "Restarting now!");
                        Bukkit.getServer().shutdown();
                        this.cancel();
                    }
                    timeLeft--;
                }
            };
           activateTask = task.runTaskTimer(main, 0L, 20L);


        }


        return true;
    }
}
