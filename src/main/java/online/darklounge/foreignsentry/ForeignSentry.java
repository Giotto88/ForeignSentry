package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.handler.ConnectionHandler;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

public final class ForeignSentry extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello world");

        new ConnectionHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Goodby world");
    }


}

