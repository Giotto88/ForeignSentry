package online.darklounge.foreignsentry.utility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Oggetto che si usa per creare e gestire
 * il file di configurazione YAML.
 */
public class ConfigManager {

    private File file;
    private FileConfiguration config;

    public ConfigManager(Plugin plugin, String path) {
        this(plugin.getDataFolder().getAbsolutePath()+"/"+path);
    }

    public ConfigManager(String path) {
        this.file = new File(path);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean save(){
        try{
            this.config.save(this.file);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public Boolean getBoolean(String x){
        return config.getBoolean(x);
    }

    public String getText(String x){
        return config.getString(x);
    }

    public Integer getInteger(String x){
        return config.getInt(x);
    }
}
