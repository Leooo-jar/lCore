package com.leo.lCore.Commands.AdminCommands;

import com.leo.lCore.Main;
import com.leo.lCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

//             /rank <player> <rank>


    private Main main;

    public RankCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

if (sender instanceof Player) {
    Player player = (Player) sender;


    if (player.isOp()) {
        if (args.length == 2) {

            if (Bukkit.getOfflinePlayer(args[0]) != null) { // <PLAYER>
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                for ( Rank rank : Rank.values()) {
                    if (rank.name().equalsIgnoreCase(args[1])) { // <RANKS>
                        main.getRanksManager().setRank(target.getUniqueId(),rank, false);

                        player.sendMessage(ChatColor.GREEN + "You changed " + target.getName() + "'s rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                        if (target.isOnline()) {
                            target.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " set your rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                        }
                        return false;
                    }
                }

                player.sendMessage(ChatColor.RED + "You did not specify a valid rank! Option are Member, Admin, Manager and Owner! ");

            } else {
                player.sendMessage(ChatColor.RED + "This user has never joined the server before!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <Player> <Rank>.");
        }
    } else {
        player.sendMessage(ChatColor.RED + "You must be OP to use this command!");
    }



}


       return false;
    }
}
