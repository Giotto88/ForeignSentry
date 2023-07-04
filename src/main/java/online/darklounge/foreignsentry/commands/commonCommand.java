package online.darklounge.foreignsentry.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import online.darklounge.foreignsentry.ForeignSentry;
public class commonCommand extends ForeignSentry implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isConsole = sender instanceof ConsoleCommandSender;
        boolean isPlayer = sender instanceof Player;
        if (isPlayer){
            boolean isOp = sender.isOp();
        }

        if ( args.length == 0 ){
            sender.sendMessage("sintassi incorretta. usa /fk help");
            return true;
        }

        switch (args[0]){
            case "resetAutenticazioni":
                if(isConsole && isPlayer && sender.isOp()){
                    listaAutenticazioni.reset();
                    Bukkit.getLogger().info("[ForeignSentry] listaAutenticazioni resettata");
                } else {
                    sender.sendMessage("Non autorizzato");
                }
                break;
            case "help":
                sender.sendMessage("Argomenti disponibili <resetAutenticazioni>");
                break;
            default:
                sender.sendMessage("argomento incorretto. usa /fk help");
                return true;
        }

        return true;
    }

}
