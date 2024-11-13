package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.commands.psk;
import online.darklounge.foreignsentry.handler.ConnectionHandler;
import online.darklounge.foreignsentry.util.ConfigUtil;
import online.darklounge.foreignsentry.util.DelayedTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import java.util.Objects;

public final class ForeignSentry extends JavaPlugin implements Listener {

    public static ConfigUtil GlobalConfig;
    public static AuthedUsers pippoHashMap;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello world");

        saveDefaultConfig();

        /* Loop on a config file
            List<String> kitItems = (List<String>) getConfig().getList("kit");
            for(String itemName : kitItems){
                Bukkit.getLogger().info(itemName);
            }
        */

        GlobalConfig = new ConfigUtil(this, "config.yml");
        /* Get and set to add things on config file
        //Bukkit.getLogger().info("> CONFIG sessionsTimeout: "+GlobalConfig.getConfig().get("sessionsTimeout"));
        //GlobalConfig.getConfig().set("hello","world");
        //config.save();

         */

        pippoHashMap = new AuthedUsers();

        new ConnectionHandler(this);
        new DelayedTask(this);

        // Register our command "kit" (set an instance of your command class as executor)
        Objects.requireNonNull(this.getCommand("psk")).setExecutor(new psk());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Goodbye world");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        getLogger().info(sender.getName()+"ha tirato il comando: "+command.getName());
        return true;
        //return super.onCommand(sender, command, label, args);
    }


}

