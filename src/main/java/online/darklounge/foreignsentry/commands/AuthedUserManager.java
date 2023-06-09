package online.darklounge.foreignsentry.commands;
import online.darklounge.foreignsentry.AuthedUsers;
import online.darklounge.foreignsentry.ForeignSentry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AuthedUserManager extends ForeignSentry implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            pippoHashMap.reset();
            Bukkit.getLogger().info("AuthedUsers table flushed requested from console");
        } else if (sender instanceof Player){
            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();
            if( pippoHashMap.isAlreadyLogged(name,ip)){
                pippoHashMap.reset();
                Bukkit.getLogger().info("AuthedUsers table flushed");
            }else{
                player.sendMessage("Non autorizzato");
            }
        }

        return true;
    }
}
