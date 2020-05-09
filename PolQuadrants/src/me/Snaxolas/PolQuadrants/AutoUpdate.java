package me.Snaxolas.PolQuadrants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;

public class AutoUpdate {
    public static boolean AutoUpdate(Player p, Main plugin){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        if(board.getEntryTeam(p.getName()) == null) {
            //first join
            board.getTeam("Unflaired").addEntry(p.getName());
            String toNick = ChatColor.getLastColors(board.getEntryTeam(p.getName()).getPrefix()) + ChatColor.stripColor(p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "nick " + p.getName() + " " + toNick);
            p.sendMessage(ChatColor.DARK_RED + "You do not currently have a quadrant flair.");
            p.sendMessage(ChatColor.GOLD + "Do /setQuadrant <QuadrantName> to set your flair and chat color. " +
                    "e.g. \"/setQuadrant Centrist\" would change your flair and chat color accordingly.");

            return true;
        }

        File file = new File(plugin.getDataFolder().getParent() + File.separator + "Essentials" + File.separator + "userdata" + File.separator + p.getUniqueId() + ".yml");

        if(!file.exists()){
            p.sendMessage(ChatColor.DARK_RED + "Tell Snax he fucked up, Error code: FILE_BROKE");


        }

        YamlConfiguration yc = YamlConfiguration.loadConfiguration(file);

        String nick = yc.getString("nickname");

        if(nick == null){
            nick = p.getName();
        }

        //checks if person's nick should not be changed e.g. an admin with a nickname prefix.
        if(!(p.hasPermission("PolQuadrants.doNotUpdateNick"))){
            String toNick = ChatColor.getLastColors(board.getEntryTeam(p.getName()).getPrefix()) + ChatColor.stripColor(nick);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "nick " + p.getName() + " " + toNick);
        }

        if(board.getEntryTeam(p.getName()).getName().equals("Unflaired")){
            p.sendMessage(ChatColor.GOLD + "Do /setQuadrant <QuadrantName> to set your flair and chat color. " +
                    "e.g. \"/setQuadrant Centrist\" would change your flair and chat color accordingly.");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "nick" + p.getName() + " " + ChatColor.DARK_AQUA + p.getName());
            return true;
        }

        return false;
    }

}
