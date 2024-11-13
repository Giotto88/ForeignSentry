package online.darklounge.foreignsentry.handler;

import online.darklounge.foreignsentry.ForeignSentry;
import online.darklounge.foreignsentry.util.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetAddress;
import java.util.Objects;

public class ConnectionHandler implements Listener {
    public ConnectionHandler(ForeignSentry plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // STEP 1
        Player player = e.getPlayer();
        String name = player.getName();
        InetAddress ip = Objects.requireNonNull(player.getAddress()).getAddress();
        // STEP 2
        player.setGameMode(GameMode.ADVENTURE);
        // STEP 3
        if(!ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip.getHostAddress())){
            player.sendMessage("Login using /psk <password>");
            player.sendMessage("Wrong password lead to ip-ban");
            DelayedTask task = new DelayedTask(() -> {
                if(ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip.getHostAddress())){
                    Bukkit.getLogger().info("OK you stay!!!!!!!!!!!!!!");
                }else{
                    Bukkit.getLogger().info(">> LOGIN FAILED");
                    if((Boolean) ForeignSentry.GlobalConfig.getConfig().get("RealBan")){
                        Bukkit.banIP(ip);
                        player.kickPlayer("HAHA YOU GOT BANNED!!!!!!!!!!!!!!");
                    }else{
                        player.kickPlayer("out of here fool!");
                    }
                }
            },20 * 8);
            //stop the task before it finish
            //Bukkit.getScheduler().cancelTask(task.getId());
            //action after the delay will be executed before the timer end
        }else{
            player.setGameMode(GameMode.SURVIVAL);
        }


        // Logga il nome dell'utente alla connessione
        // Bukkit.getLogger().info("Gotcha "+name+":"+ip);


        //Bukkit.banIP(player.getAddress().getAddress().getHostAddress());

    }

    //@EventHandler
    //public void onTorchPlace(BlockPlaceEvent eve){
    //    Block block = eve.getBlock();
    //    if(block.getType() != Material.TORCH){
    //        return;
    //    }
    //
    //    //Log ogni volta che viene piazzata una torcia
    //    Bukkit.getLogger().info("Gotcha a torch!!");
    //}


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