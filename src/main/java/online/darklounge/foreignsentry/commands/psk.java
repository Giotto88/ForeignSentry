package online.darklounge.foreignsentry.commands;


import online.darklounge.foreignsentry.ForeignSentry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class psk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( sender instanceof Player){
            Player player = (Player) sender;
            String name = player.getName();
            String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

            ItemStack piccone = new ItemStack(Material.STONE_SWORD,1);

            player.getInventory().addItem(piccone);
            Bukkit.getLogger().info(ForeignSentry.pippoHashMap.toString());
            ForeignSentry.pippoHashMap.addPlayer(name,ip);
            //Bukkit.unbanIP(ip);
            //getLogger().info("IP unbanned");
        }
        return true;
    }
}
