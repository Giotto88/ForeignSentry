package online.darklounge.foreignsentry.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DelayedTask implements Listener {
    private static Plugin plugin = null;
    private int id = -1;

    public DelayedTask(Plugin istance){
        plugin = istance;
    }

    public DelayedTask(Runnable runnable){
        this(runnable, 0);
    }

    public DelayedTask(Runnable runnable, long delay){
        if(plugin.isEnabled()){
            id= Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay);
        } else {
            runnable.run();
        }
    }

    public int getId() {
        return id;
    }
}
