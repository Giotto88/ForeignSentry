package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.commands.AuthedUserManager;
import online.darklounge.foreignsentry.commands.psk;
import online.darklounge.foreignsentry.handler.ConnectionHandler;
import online.darklounge.foreignsentry.util.ConfigUtil;
import online.darklounge.foreignsentry.util.DelayedTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import java.util.Objects;

public class ForeignSentry extends JavaPlugin implements Listener {

    protected ConfigUtil GlobalConfig;
    protected AuthedUsers pippoHashMap;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ForeignSentry > START!");
        saveDefaultConfig();

        /* Loop on a config file
            List<String> kitItems = (List<String>) getConfig().getList("kit");
            for(String itemName : kitItems){
                Bukkit.getLogger().info(itemName);
            }
        */

        GlobalConfig = new ConfigUtil(this, "config.yml");
        /* Get and set to write things on config file
            Bukkit.getLogger().info("> CONFIG sessionsTimeout: "+GlobalConfig.getConfig().get("sessionsTimeout"));
            GlobalConfig.getConfig().set("hello","world");
            config.save();
         */

        pippoHashMap = new AuthedUsers((int) GlobalConfig.getConfig().get("sessionsTimeout"));

        new ConnectionHandler(this);
        new DelayedTask(this);

        // Register our command "kit" (set an instance of your command class as executor)
        Objects.requireNonNull(getCommand("login")).setExecutor(new psk());
        Objects.requireNonNull(getCommand("fk")).setExecutor(new AuthedUserManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ForeignSentry > END!");
    }

// Handler
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
//        getLogger().info(sender.getName()+"ha tirato il comando: "+command.getName());
//        return true;
//        //return super.onCommand(sender, command, label, args);
//    }


}

