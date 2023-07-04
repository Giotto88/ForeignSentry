package online.darklounge.foreignsentry.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import online.darklounge.foreignsentry.localDatabase.ListaSessioni;
import online.darklounge.foreignsentry.localDatabase.ListaTentativiAccesso;
import online.darklounge.foreignsentry.utility.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class loginCommand implements CommandExecutor {

    private final ConfigManager GlobalConfig;
    private final ListaSessioni listaAutenticazioni;
    private final ListaTentativiAccesso listaTentativiAccesso;

    public loginCommand(ConfigManager globalConfig, ListaSessioni listaAutenticazioni, ListaTentativiAccesso listaTentativiAccesso) {
        this.GlobalConfig = globalConfig;
        this.listaAutenticazioni = listaAutenticazioni;
        this.listaTentativiAccesso = listaTentativiAccesso;
    }

    /**
     * @param sender Oggetto rappresentante il player
     * @param command Oggetto rappresentante il nome del comando usato
     * @param label Oggetto rappresentante il nome del comando usato
     * @param args Oggetto rappresentante gli argomenti del comando usato
     * @return nulla
     *
     * Questo oggetto gestisce la verifica dell'autenticazione
     *
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if( sender instanceof Player ){

            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

            // SE gli argomenti sono almeno uno, e il primo argomento è uguale alla password presente nella configurazione e l'utente non è gia autenticato
            // ALLORA segna che l'utente si è autenticato con nome e ip e imposta la sua modalità di gioco a survival
            // INOLTRE rimuovi il ban preventivo se l'autenticazione è andata a buon fine

            if ( args.length > 0 && args[0].equals(GlobalConfig.getConfig().get("password")) && !listaAutenticazioni.isAlreadyLogged(name,ip) ) {

                listaAutenticazioni.aggiungiPlayer(name,ip);
                player.setGameMode(GameMode.SURVIVAL);

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN +"password corretta"));

            }else{
                sender.sendMessage(ChatColor.RED +"sintassi comando o password errata");
            }

        } else {
            sender.sendMessage("[ForeignSentry] Solo i player possono usare questo comando.");
        }
        return true;
    }

}
