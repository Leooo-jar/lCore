package com.leo.lCore.Manager;

import com.leo.lCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class RewardManager implements Listener {

    public HashMap<UUID, Integer> playerTime = new HashMap<>();

    private final Main main;



public RewardManager (Main main) {
    this.main = main;

    BukkitRunnable task = new BukkitRunnable() {
        @Override
        public void run() {

            for (UUID playerUUID : playerTime.keySet()) {
                int currentTime = playerTime.get(playerUUID);

                playerTime.put(playerUUID, currentTime + 1);

                if (currentTime == 30) {

                    Player player = Bukkit.getPlayer(playerUUID);
                    if (player != null) {

                        player.sendMessage(ChatColor.GREEN + "Congratulations, you have reached 30 seconds and earned a reward!");
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND));


                    }

                    playerTime.remove(playerUUID);



                }

            }



        }
    };


task.runTaskTimer(main, 0L, 20L);

}


    public void addPlayer(UUID playerUUID) {
        playerTime.put(playerUUID, 0);

    }

    }

