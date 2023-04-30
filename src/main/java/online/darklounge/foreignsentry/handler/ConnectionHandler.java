package online.darklounge.foreignsentry.handler;

import online.darklounge.foreignsentry.AuthedUsers;
import online.darklounge.foreignsentry.ForeignSentry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class ConnectionHandler implements Listener {
    public ConnectionHandler(ForeignSentry plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String name = player.getName();
        String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

        // Logga il nome dell'utente alla connessione
        Bukkit.getLogger().warning("Gotcha "+name+":"+ip);


        //Bukkit.banIP(player.getAddress().getAddress().getHostAddress());

    }

    @EventHandler
    public void onTorchPlace(BlockPlaceEvent eve){
        Block block = eve.getBlock();
        if(block.getType() != Material.TORCH){
            return;
        }

        //Log ogni volta che viene piazzata una torcia
        Bukkit.getLogger().warning("Gotcha a torch!!");
    }


}

//    @EventHandler(priority = EventPriority.LOW)
//    public void onPlayerJoin(PlayerJoinEvent e) {
//        Player player = e.getPlayer();
//        String name = player.getName();
//
//        getLogger().info("Gotcha "+name);
//
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (!player.isOnline()) {
//                    return;
//                }
//
//                player.sendMessage("");
//                player.sendMessage(" §eHello, " + name + "!");
//                player.sendMessage("");
//                player.sendMessage("  §7Before we start, please select");
//                player.sendMessage("  §7your favorite login plugin.");
//                player.sendMessage("");
//                player.sendMessage("");
//
//            }
//        };
//        //.runTaskLater(plugin, 30);
//
//        e.setJoinMessage("#sadjkasdnioh12981213");
//
//        player.setWalkSpeed(0F);
//        player.setFlySpeed(0F);
//
//    }