package me.Snaxolas.PolQuadrants;
import me.Snaxolas.PolQuadrants.Commands.refreshQuadrant;
import me.Snaxolas.PolQuadrants.Commands.setQuadrant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin {
    private void onFirstRun(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        Team ll = board.registerNewTeam("LibLeft");
        ll.setPrefix(ChatColor.DARK_GREEN + "[LibLeft] ");

        Team lc = board.registerNewTeam("LibCenter");
        lc.setPrefix(ChatColor.GREEN + "[LibCenter] ");

        Team lr = board.registerNewTeam("LibRight");
        lr.setPrefix(ChatColor.YELLOW + "[LibRight] ");

        Team rc = board.registerNewTeam("RightCenter");
        rc.setPrefix(ChatColor.GOLD + "[RightCenter] ");

        Team ar = board.registerNewTeam("AuthRight");
        ar.setPrefix(ChatColor.BLUE + "[AuthRight] ");

        Team ac = board.registerNewTeam("AuthCenter");
        ac.setPrefix(ChatColor.DARK_PURPLE + "[AuthCenter] ");

        Team al = board.registerNewTeam("AuthLeft");
        al.setPrefix(ChatColor.DARK_RED + "[AuthLeft] ");

        Team le = board.registerNewTeam("LeftCenter");
        le.setPrefix(ChatColor.RED + "[LeftCenter] ");

        Team ce = board.registerNewTeam("Centrist");
        ce.setPrefix(ChatColor.GRAY + "[Centrist] ");

        Team oc = board.registerNewTeam("OffCompass");
        oc.setPrefix(ChatColor.DARK_GRAY + "[OffCompass] ");

        Team uf = board.registerNewTeam("Unflaired");
        uf.setPrefix(ChatColor.DARK_AQUA + "[Unflaired] ");


    }

    @Override
    public void onEnable(){
        Listener playerJoin = new PlayerJoin(this);
        getServer().getPluginManager().registerEvents(playerJoin,this);

        registerCmds();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
        if(board.getTeam("Centrist") == null){
            onFirstRun();
            Bukkit.getServer().getConsoleSender().sendMessage("Either something went horribly wrong or this server is brand new.");
        }
    }

    @Override
    public void onDisable(){
    }

    public void registerCmds(){
        this.getCommand("setQuadrant").setExecutor(new setQuadrant(this));
        this.getCommand("setQ").setExecutor(new setQuadrant(this));
        this.getCommand("refreshQuadrant").setExecutor(new refreshQuadrant(this));
        this.getCommand("refreshQ").setExecutor(new refreshQuadrant(this));
    }


}


/*
Permissions:

  PolQuadrants.doNotUpdateNick:
    gives a person immunity to the automatic nickname changes this plugin does.

  PolQuadrants.refresh:
    allows use of /refreshQuadrant

  PolQuadrants.refresh.others:
    allows directed refreshes of other players

  PolQuadrants.changeFlair:
    allows use of /setQuadrant

  PolQuadrants.changeFlair.others:
    allows directed use of /setQuadrant at other players

 */