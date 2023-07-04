package online.darklounge.foreignsentry.handler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import online.darklounge.foreignsentry.ForeignSentry;
import online.darklounge.foreignsentry.localDatabase.ListaSessioni;
import online.darklounge.foreignsentry.localDatabase.ListaTentativiAccesso;
import online.darklounge.foreignsentry.utility.ConfigManager;
import online.darklounge.foreignsentry.utility.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class ConnectionHandler implements Listener  {
    private Long tempoMassimoAccesso = 10L;
    private final ListaSessioni listaAutenticazioni;
    private final ListaTentativiAccesso listaTentativiAccesso;
    private final ConfigManager GlobalConfig;

    public ConnectionHandler(ForeignSentry plugin, Long tempoMassimoAccesso,ConfigManager globalConfig, ListaSessioni listaAutenticazioni, ListaTentativiAccesso listaTentativiAccesso) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.tempoMassimoAccesso = tempoMassimoAccesso;
        this.GlobalConfig = globalConfig;
        this.listaAutenticazioni = listaAutenticazioni;
        this.listaTentativiAccesso = listaTentativiAccesso;
    }

    /**
     * @param e Registra gli eventi di join.
     * @apiNote Ogni volta che un giocatore entra esegui questi comandi
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // STEP 1 - get information
        Player player = e.getPlayer();
        String name = player.getName();
        String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

        // Logga il nome dell'utente alla connessione
        Bukkit.getLogger().info("Connessione stabilita. USERNAME: "+name+" IP: "+ip);

        // STEP 2 - secure the world from foreign
        player.setGameMode(GameMode.ADVENTURE);


        // STEP 3 - check permission
        if(!listaAutenticazioni.isAlreadyLogged(name,ip)){
            player.sendMessage("Login using /login <password>");
            player.sendMessage("Wrong password lead to ip-ban");

            DelayedTask task = new DelayedTask(() -> {

                // DOPO il delay...
                if(listaAutenticazioni.isAlreadyLogged(name,ip)){
                    // >> Accesso riuscito
                    //Bukkit.getLogger().info("OK you stay!!!!!!!!!!!!!!");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD +"|||||||||||! WELCOME BACK! !|||||||||||"));
                }else{
                    // >> Accesso negato
                    Bukkit.getLogger().warning(player.getAddress().getAddress()+"<-----");
                    listaTentativiAccesso.setListaTentativiAccesso(player.getAddress().getAddress());

                    if((Boolean) GlobalConfig.getConfig().get("RealBan")){
                        Bukkit.banIP(ip);
                        player.kickPlayer("HAHA YOU GOT BANNED!!!!!!!!!!!!!!");
                    }else{
                        player.kickPlayer("Tempo d'accesso terminato!\nTentativo numero: "+listaTentativiAccesso.getListaTentativiAccesso(player.getAddress().getAddress()));
                    }
                }

                //stop the task before it finish
                //Bukkit.getScheduler().cancelTask(task.getId());
                //action after the delay will be executed before the timer end

            },20 * tempoMassimoAccesso);
        }

        player.setGameMode(GameMode.SURVIVAL);

        if((Boolean) GlobalConfig.getConfig().get("RealBan")){
            Bukkit.unbanIP(ip);
        }

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