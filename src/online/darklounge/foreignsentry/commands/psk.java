package online.darklounge.foreignsentry.commands;


import online.darklounge.foreignsentry.ForeignSentry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Objects;

public class psk implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	// Se comando eseguito da un Player
        if( sender instanceof Player ){
            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

        	// SE psk ha un argomento, E l'argomento 0 è uguale a psk, E l'utente non e' gia' loggato
        	// ALLORA aggiungi l'autenticazione E imposta la gameMode a survival
            if( args.length > 0 && args[0].equals(ForeignSentry.GlobalConfig.getConfig().get("password")) && !ForeignSentry.pippoHashMap.isAlreadyLogged(name, ip) ){
            	Bukkit.getLogger().info("[ForeignSentry][DEBUG] "+name+":"+ip+" >> Password corretta");
                
            	ForeignSentry.pippoHashMap.addPlayer(name,ip);
                player.setGameMode(GameMode.SURVIVAL);
                
                // TODO: aggiungere un contatore, possibile bruteforce 
            }else {
            	Bukkit.getLogger().info("[ForeignSentry][DEBUG] "+name+":"+ip+" >> Password errata");
            }
        } else {
            sender.sendMessage("Only player can run this command.");
        }
        return true;
    }
}

/**
 *  // DEV garbage //
    give item on login
    ItemStack piccone = new ItemStack(Material.STONE_SWORD,1);
    player.getInventory().addItem(piccone);

    itera su args
    for(String arg : args){
        Bukkit.getLogger().info(arg);
}
*/