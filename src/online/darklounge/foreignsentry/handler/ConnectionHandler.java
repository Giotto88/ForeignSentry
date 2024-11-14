package online.darklounge.foreignsentry.handler;

import online.darklounge.foreignsentry.ForeignSentry;
import online.darklounge.foreignsentry.util.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.util.Objects;

public class ConnectionHandler implements Listener {
	
    public ConnectionHandler(ForeignSentry plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // STEP 1
    	// player --> nome, gamemode
        Player player = e.getPlayer();
        String name = player.getName();
        InetAddress ip = Objects.requireNonNull(player.getAddress()).getAddress();
        GameMode oldGameMode = player.getGameMode();
        
        // TODO: Registrare tentativo di login dopo la task.
        
        // STEP 2
        player.setGameMode(GameMode.ADVENTURE);
        
        // STEP 3 - AutenticazionePrecedente? = no
        if(!ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip.getHostAddress())){
        	
            player.sendMessage(ChatColor.GOLD + "L'accesso al server e' ristretto!");
            player.sendMessage(ChatColor.GOLD + "Accedi con la password scelta.");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "/psk <password>");
            player.sendMessage(ChatColor.GOLD + "Hai 10 secondi per autenticarti");
            player.sendMessage(ChatColor.RED + "Hai 5 tentativi prima di un ip-ban");
            
            new DelayedTask(() -> {
                if(ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip.getHostAddress())){
                    // Bukkit.getLogger().info("OK you stay!!!!!!!!!!!!!!");
                    Bukkit.getLogger().info("[ForeignSentry] "+name+":"+ip.getHostAddress()+" Ha un accesso valido.");
                    player.sendMessage(ChatColor.GREEN + "Accesso autenticato!");
                    
                }else{
                	Bukkit.getLogger().info("[ForeignSentry] "+name+":"+ip.getHostAddress()+" Non e' autorizzato.");
                    // Bukkit.getLogger().info(">> LOGIN FAILED");
                    if((Boolean) ForeignSentry.GlobalConfig.getConfig().get("RealBan")){
                        Bukkit.banIP(ip);
                        Bukkit.getLogger().warning("[ForeignSentry] L' IP di "+name+" ("+ip.getHostAddress()+") e' stato bandito");
                        player.kickPlayer("HAHA YOU GOT BANNED!!!!!!!!!!!!!!");
                    }else{
                        // player.kickPlayer("out of here fool!");
                    	player.kickPlayer("Accesso non autorizzato! Out of here fool!");
                    }
                }
            }, 20 * (int)ForeignSentry.GlobalConfig.getConfig().get("secondiAutenticazione"));
            
            // DEV garbage
            //stop the task before it finish
            //Bukkit.getScheduler().cancelTask(task.getId());
            //action after the delay will be executed before the timer end
            
        } else {
        	// STEP 3 - AutenticazionePrecedente? = si
            Bukkit.getLogger().info("[ForeignSentry] "+name+":"+ip.getHostAddress()+" Aveva un accesso valido precedente.");
            player.sendMessage(ChatColor.GREEN + "Accesso pre-autenticato!");
            player.setGameMode(oldGameMode);
        }
    }
}


// DEV garbage //

//DEV garbage: DelayedTask task = // salvare l'id della task per fermarla prima riga 44
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