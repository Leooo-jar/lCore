package com.leo.lCore.Manager;

import com.leo.lCore.Main;
import com.leo.lCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private Main main;
    private File file;
    private YamlConfiguration config;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Main main) { // Instancia hacia el Main
        this.main = main;
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();

        }


        file = new File(main.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }


    public void setRank(UUID uuid, Rank ranks, boolean firstJoin) {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)) {
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }
            for (String perm : getRank(uuid).getPermissions()) {
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }


            for (String perm : ranks.getPermissions()) {
                attachment.setPermission(perm, true);
            }
        }


        config.set(uuid.toString(), ranks.name());
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            main.getNametagManager().removetag(player);
            main.getNametagManager().newTag(player);
        }


    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));

    }
    public HashMap<UUID, PermissionAttachment> getPerms() {return perms;}
}
