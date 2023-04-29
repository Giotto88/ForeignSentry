package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.commands.psk;
import online.darklounge.foreignsentry.handler.ConnectionHandler;
//import online.darklounge.foreignsentry.commands.Secretasked;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.util.logging.Level;

public final class ForeignSentry extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello world");

        new ConnectionHandler(this);

        // Register our command "kit" (set an instance of your command class as executor)
        this.getCommand("psk").setExecutor(new psk());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Goodby world");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        getLogger().info(sender.getName()+"ha tirato il comando: "+command.getName());
        return true;
        //return super.onCommand(sender, command, label, args);
    }


}

