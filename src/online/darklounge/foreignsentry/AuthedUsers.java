package online.darklounge.foreignsentry;

import java.util.HashMap;
import java.time.Duration;
import java.util.Date;

public class AuthedUsers {

	/**
	 * NDAY: giorni di validità username:IP
	 * authRegistry: hashmasp | username:IP | timestamp autenticazione |
	 */
	private final int NDAY;
    private final HashMap<String, Long> authRegistry;

    public AuthedUsers() {
    	this.NDAY = (int) ForeignSentry.GlobalConfig.getConfig().get("sessionsTimeout");
        this.authRegistry = new HashMap<>();
        // TODO: prevedere un caso di default in caso di config non funzionante
    }

    /**
     * Aggiunta key: name:ip value: timestamp unix
     * @param username Player.name
     * @param ip Player.ip
     */
    public void addPlayer(String username, String ip){
        Date currentDate = new Date();
        authRegistry.put( username+":"+ip, currentDate.getTime() );
    }

    /**
     * @param username nome utente
     * @param ip indirizzo ip
     * @return boolean SE gia autenticato E se l'ultimo login e' meno di NDAY gg fa
     */
    public boolean isAlreadyLogged(String username, String ip){
        Date currentDate = new Date();
        Long lastlogin = authRegistry.get(username + ":" + ip);
        
        if (lastlogin == null) {
            return false; // L'utente non è mai stato loggato con questo IP
        }

        // Calcola la durata in millisecondi per evitare overflow
        long maxInterval = Duration.ofDays(NDAY).toMillis();

        // Log per il debug
        // Bukkit.getLogger().info("[ForeignSentry][DEBUG] " + currentDate.getTime() + " - " + lastlogin);

        return (currentDate.getTime() - lastlogin) <= maxInterval;
    }

    /**
     * Rimuove la tupla dalla tabella autenticazioni
     * @param username
     * @param ip
     */
    public void removePlayer(String username, String ip){
        authRegistry.remove(username+":"+ip);
    }

}
