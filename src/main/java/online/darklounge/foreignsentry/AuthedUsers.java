package online.darklounge.foreignsentry;

// Java program to demonstrate working of HashTable
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Date;

public class AuthedUsers {

    private final int NDAY = 5; // Giorni per considerare valido il login
    private HashMap<String, Long> authRegistry;

    public AuthedUsers() {
        this.authRegistry = new HashMap<>();
    }

    /**
     * Aggiungi una entry composta da nome utente più ip all'interno della hashmap
     * @param username
     * @param ip
     * @return
     */
    public boolean addPlayer(String username, String ip){
        Date currentDate = new Date();
        authRegistry.put(username+":"+ip,currentDate.getTime());
        System.out.println(">> LOGIN: "+username+":"+ip+" at: "+currentDate.getTime());
        //Bukkit.getLogger().info(">> LOGIN: "+username+":"+ip+" at: "+currentDate.getTime());
        return true;
    }

    public boolean isAlreadyLogged(String username, String ip){
        Date currentDate = new Date();
        Long lastlogin = authRegistry.get(username+":"+ip);
        return lastlogin != null && ( currentDate.getTime() - lastlogin ) < (86400 * NDAY);
    }

    public void removePlayer(String username, String ip){
        authRegistry.remove(username+":"+ip);
    }

}
