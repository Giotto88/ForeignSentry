package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.commands.psk;
import online.darklounge.foreignsentry.handler.ConnectionHandler;
import online.darklounge.foreignsentry.util.ConfigUtil;
import online.darklounge.foreignsentry.util.DelayedTask;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import java.util.Objects;

public final class ForeignSentry extends JavaPlugin implements Listener {

    public static ConfigUtil GlobalConfig;
    public static AuthedUsers pippoHashMap;

    @Override
    public void onEnable() {
        // Sequenza startup

        GlobalConfig = new ConfigUtil(this, "config.yml");
        pippoHashMap = new AuthedUsers();
        new ConnectionHandler(this);
        new DelayedTask(this);
        
    	
        getLogger().info("Plugin pronto!");
        saveDefaultConfig();
        
        // Register our command "kit" (set an instance of your command class as executor)
        Objects.requireNonNull(this.getCommand("psk")).setExecutor(new psk());
  
        getLogger().info("------------------------");
        getLogger().info("Configurazione caricata:");
        getLogger().info("> Validita': "+GlobalConfig.getConfig().get("sessionsTimeout")+" (day)");
        getLogger().info("> Password: "+GlobalConfig.getConfig().get("password"));
        getLogger().info("> IP-Ban: "+GlobalConfig.getConfig().get("RealBan"));
        getLogger().info("> TempoAutenticazione: "+GlobalConfig.getConfig().get("secondiAutenticazione")+" (sec)");
        getLogger().info("------------------------");
        
        // DEV garbage //
        
        /* Get and set to add things on config file
        	//GlobalConfig.getConfig().set("hello","world");
        	//config.save();
        */
        /* Loop on a config file
        List<String> kitItems = (List<String>) getConfig().getList("kit");
        for(String itemName : kitItems){
            Bukkit.getLogger().info(itemName);
        }
        */
        
    }

    @Override
    public void onDisable() {
        // Sequenza shutdown
        // getLogger().info("Goodbye world");
    	getLogger().info("Chiusura eseguita!");
    }
    
    /**
     * 
     * // DEV garbage //
     * 
     * 
     * import org.bukkit.command.Command;
     * import org.bukkit.command.CommandSender;
     * 
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
	        getLogger().info(sender.getName()+"ha tirato il comando: "+command.getName());
	        return true;
	        //return super.onCommand(sender, command, label, args);
	    }
    */


}

