package online.darklounge.foreignsentry.commands;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import online.darklounge.foreignsentry.ForeignSentry;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Objects;

public class psk implements CommandExecutor {


    /**
     * @param sender Oggetto rappresentante il player
     * @param command Oggetto rappresentante il nome del comando usato
     * @param label Oggetto rappresentante il nome del comando usato
     * @param args Oggetto rappresentante gli argomenti del comando usato
     * @return nulla
     *
     * Questo oggetto gestisce le chiamate dei vari comandi
     * TODO: spostare nella cartella handler
     *
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( sender instanceof Player ){
            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

            /*
            give item on login
            ItemStack piccone = new ItemStack(Material.STONE_SWORD,1);
            player.getInventory().addItem(piccone);

            itera su args
            for(String arg : args){
                Bukkit.getLogger().info(arg);
            }
            */

            // SE gli argomenti sono almeno uno, e il primo argomento è uguale alla password presente nella configurazione e l'utente non è gia autenticato
            // ALLORA segna che l'utente si è autenticato con nome e ip e imposta la sua modalità di gioco a survival
            // INOLTRE rimuovi il ban preventivo se l'autenticazione è andata a buon fine
            if (args.length > 0 && args[0].equals(ForeignSentry.GlobalConfig.getConfig().get("password")) && !ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip) ) {
                //ADD THE plAYER TO THE AUTHED LIST OF PLAYER
                ForeignSentry.pippoHashMap.addPlayer(name,ip);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN.toString() +"password corretta"));
                player.setGameMode(GameMode.SURVIVAL);
            }
        } else {
            sender.sendMessage("Only player can run this command.");
        }
        return true;
    }
}
