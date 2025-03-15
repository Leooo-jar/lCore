package com.leo.lCore.Manager;

import com.leo.lCore.Main;
import com.leo.lCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {


    private Main main;

    public NametagManager(Main main) {
        this.main = main;
    }

    public void setNametags(Player player) {

        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay() + " ");
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() != target.getUniqueId()) {


            player.getScoreboard().getEntryTeam(main.getRanksManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
            }
        }
    }


public void newTag(Player player) {
        Rank rank = main.getRanksManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }

}
public void removetag(Player player) {

    for (Player target : Bukkit.getOnlinePlayers()) {
        target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
    }


}
}
