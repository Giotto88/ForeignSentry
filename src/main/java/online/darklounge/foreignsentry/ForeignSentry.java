package online.darklounge.foreignsentry;

import online.darklounge.foreignsentry.commands.commonCommand;
import online.darklounge.foreignsentry.commands.loginCommand;
import online.darklounge.foreignsentry.handler.ConnectionHandler;
import online.darklounge.foreignsentry.localDatabase.ListaSessioni;
import online.darklounge.foreignsentry.localDatabase.ListaTentativiAccesso;
import online.darklounge.foreignsentry.utility.DelayedTask;
import online.darklounge.foreignsentry.utility.ConfigManager;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.Objects;

public class ForeignSentry extends JavaPlugin implements Listener {

    protected ConfigManager GlobalConfig;
    protected ListaSessioni listaAutenticazioni;
    protected ListaTentativiAccesso listaTentativiAccesso;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ForeignSentry > START!");
        saveDefaultConfig();

        GlobalConfig = new ConfigManager(this, "config.yml");
        listaAutenticazioni = new ListaSessioni((int) GlobalConfig.getConfig().get("sessionsTimeout"));
        listaTentativiAccesso = new ListaTentativiAccesso();

        /* Handler da gestire */
        new ConnectionHandler(this,GlobalConfig,listaAutenticazioni,listaTentativiAccesso);
        new DelayedTask(this);

        /*
            >> comando per leggere e scrivere valori in config
            Get and set to write things on config file

            GlobalConfig.getConfig().get("sessionsTimeout"));
            GlobalConfig.getConfig().set("hello","world");
            config.save();
         */


        // Register our command "kit" (set an instance of your command class as executor)
        Objects.requireNonNull(getCommand("login")).setExecutor(new loginCommand(GlobalConfig,listaAutenticazioni,listaTentativiAccesso));
        Objects.requireNonNull(getCommand("fk")).setExecutor(new commonCommand(GlobalConfig,listaAutenticazioni,listaTentativiAccesso));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ForeignSentry > SHUTDOWN!");
    }

// Handler
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
//        getLogger().info(sender.getName()+"ha tirato il comando: "+command.getName());
//        return true;
//        //return super.onCommand(sender, command, label, args);
//    }


}

