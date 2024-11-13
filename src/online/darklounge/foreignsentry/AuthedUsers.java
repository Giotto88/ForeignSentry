package online.darklounge.foreignsentry;

// Java program to demonstrate working of HashTable
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Date;

public class AuthedUsers {

    // private int NDAY = 5;
	/**
	 * NDAY: giorni di validità username:IP
	 * authRegistry: hashmasp | username:IP | timestamp autenticazione |
	 */
	private final int NDAY;
    private final HashMap<String, Long> authRegistry;

    public AuthedUsers() {
    	this.NDAY = (int) ForeignSentry.GlobalConfig.getConfig().get("sessionsTimeout");
        this.authRegistry = new HashMap<>();
    }

    public AuthedUsers(int NDAY) {
        this.authRegistry = new HashMap<>();
        this.NDAY = NDAY;
    }

    /**
     * Aggiungi una entry composta da nome utente più ip all'interno della hashmap
     * @param username client username
     * @param ip client ip
     */
    public void addPlayer(String username, String ip){
        Date currentDate = new Date();
        authRegistry.put(username+":"+ip,currentDate.getTime());
        Bukkit.getLogger().info(">> LOGIN: "+username+":"+ip+" at: "+currentDate.getTime());
        //Bukkit.getLogger().info(">> LOGIN: "+username+":"+ip+" at: "+currentDate.getTime());
    }

    public boolean isAlreadyLogged(String username, String ip){
        Date currentDate = new Date();
        Long lastlogin = authRegistry.get(username+":"+ip);
        Bukkit.getLogger().info("AUTH STATUS: "+lastlogin);
        return lastlogin != null && ( currentDate.getTime() - lastlogin ) < (86400L * NDAY);
    }

    public void removePlayer(String username, String ip){
        authRegistry.remove(username+":"+ip);
    }

}
