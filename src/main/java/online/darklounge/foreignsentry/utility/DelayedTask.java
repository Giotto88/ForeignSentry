package online.darklounge.foreignsentry.utility;

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

    /**
     * @param runnable
     * @param delay delayTicks is the delay in server ticks (1 tick = 1/20th of a second). For example, to delay for 5 seconds, you would use delayTicks = 5 * 20
     */
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

