package online.darklounge.foreignsentry.commands;


import online.darklounge.foreignsentry.ForeignSentry;
import org.bukkit.GameMode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Objects;

public class psk implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( sender instanceof Player){
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

            if(args.length > 0 && args[0].equals(ForeignSentry.GlobalConfig.getConfig().get("password")) && !ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip) ){
                //ADD THE plAYER TO THE AUTHED LIST OF PLAYER
                ForeignSentry.pippoHashMap.addPlayer(name,ip);
                player.setGameMode(GameMode.SURVIVAL);
            }

            //Bukkit.unbanIP(ip);
            //getLogger().info("IP unbanned");
        } else {
            sender.sendMessage("Only player can run this command.");
        }
        return true;
    }
}
