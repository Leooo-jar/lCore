package com.leo.lCore.Listeners;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {


    private final Main main;

    public QuitListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void OnPlayerQuit (PlayerQuitEvent e) {

        e.setQuitMessage(null);
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "\uD83D\uDD0C " + ChatColor.GRAY + e.getPlayer().getName() + " has left the server.");



    }


}
