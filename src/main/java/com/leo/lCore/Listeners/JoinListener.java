package com.leo.lCore.Listeners;

import com.leo.lCore.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {


    private final Main main;

    public JoinListener(Main main) {

        this.main = main;



    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player player = e.getPlayer();
        World world = player.getWorld();
        Location location = player.getLocation();


        if (!player.hasPlayedBefore()) {
            Bukkit.getOnlinePlayers().forEach(player1 ->
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f));
            world.spawnParticle(Particle.HEART, location, 20,1,1,0.1);
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "\uD83D\uDDA4 " + ChatColor.GRAY + e.getPlayer().getName() + " Joined the server for the first time.");

        } else {

            Bukkit.broadcastMessage(ChatColor.RED + "\uD83D\uDDA4 " + ChatColor.GRAY + e.getPlayer().getName() + " has joined the server!");

        }



    }

}
