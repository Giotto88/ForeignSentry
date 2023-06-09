package online.darklounge.foreignsentry.commands;
import online.darklounge.foreignsentry.ForeignSentry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AuthedUserManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ForeignSentry.pippoHashMap.reset();
            Bukkit.getLogger().info("AuthedUsers table flushed requested from console");
        } else if (sender instanceof Player){
            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();
            if( ForeignSentry.pippoHashMap.isAlreadyLogged(name,ip)){
                ForeignSentry.pippoHashMap.reset();
                Bukkit.getLogger().info("AuthedUsers table flushed");
            }else{
                player.sendMessage("Non autorizzato");
            }
        }

        return true;
    }
}
