package online.darklounge.foreignsentry.localDatabase;

import org.bukkit.Bukkit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListaSessioni {

    private final int durataAutenticazione;
    private final HashMap<String,Long> listaAutenticazioni;

    /**
     * Costruttore senza parametro. Validità sessione di default a 5gg.
     */
    public ListaSessioni() {
        this.listaAutenticazioni = new HashMap<>();
        this.durataAutenticazione = 5;
    }

    /**
     * @param durataAutenticazione validità della sessione definita da username e ip.
     */
    public ListaSessioni(int durataAutenticazione) {
        this.listaAutenticazioni = new HashMap<>();
        this.durataAutenticazione = durataAutenticazione;
    }


    public boolean isAlreadyLogged(String username, String ip){
        Date currentDate = new Date();
        return ( listaAutenticazioni.containsKey(username+":"+ip) && (currentDate.getTime() - listaAutenticazioni.get(username+":"+ip) ) <  (86400L * durataAutenticazione) );
    }

    /**
     * Registrazione di avvenuta autenticazione
     * @param username nome utente
     * @param ip indirizzo ip
     *
     *           Ogni x autenticazioni controlla se eliminare quelle vecchie
     */
    public void aggiungiPlayer(String username, String ip){
        Date currentDate = new Date();
        listaAutenticazioni.put(username+":"+ip,(currentDate.getTime()));

        // TODO: da sostituire --> [evento] autenticazione registrata
        // Bukkit.getLogger().info(">> LOGIN: "+username+":"+ip+" at: "+currentDate.getTime());

        if(listaAutenticazioni.size()%10==0){
            pulizia();
        }
    }

    /**
     * Rimuove tutti le chiavi contenti il parametro specificato
     * TODO: sistemare il metodo
     * @param data username o ip
     */
    public void rimuoviPlayer(String data){
        //stackoverflow.com/questions/1884889/iterating-over-and-removing-from-a-map
        listaAutenticazioni.keySet().removeIf(e->(e.contains(data))); // <-- remove here
    }

    /**
     * Cancella la mappa
     */
    public void reset() {
        listaAutenticazioni.clear();
    }

    /**
     * Se i giorni da cui si è svolata l'autenticazione (differenza) sono maggiori dei giorni consentiti il record verrà eliminato.
     * TODO: ottimizzare il controllo
     */
    private void pulizia() {
        System.out.println("Pulizia eseguita");
        Date currentDate = new Date();
        listaAutenticazioni.values().removeIf(e->( (currentDate.getTime() - e) > (86400L * durataAutenticazione) )); // <-- remove here
    }

    private void stampa() {
        for (Map.Entry<String, Long> entry : listaAutenticazioni.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            Bukkit.getLogger().info(key + " : " + value);
        }
    }

//    public static void main(String[] args) {
//        ListaAutenticazioni pippo = new ListaAutenticazioni(1);
//        pippo.aggiungiPlayer("uno","1.1.1.1");
//        pippo.aggiungiPlayer("uno","1.1.1.2");
//        pippo.aggiungiPlayer("uno","1.1.1.3");
//        pippo.aggiungiPlayer("uno","1.1.1.4");
//        pippo.aggiungiPlayer("due","1.1.1.1");
//        pippo.aggiungiPlayer("due","1.1.1.2");
//        pippo.aggiungiPlayer("uno","1.1.1.1");
//        pippo.aggiungiPlayer("uno","1.1.1.2");
//        pippo.aggiungiPlayer("uno","1.1.1.3");
//        pippo.aggiungiPlayer("uno","1.1.1.2");
//        pippo.aggiungiPlayer("tre","1.1.1.2");
//        pippo.aggiungiPlayer("quattro","1.1.1.2");
//        pippo.aggiungiPlayer("cinque","1.1.1.2");
//        pippo.aggiungiPlayer("sei","1.1.1.2");
//        pippo.rimuoviPlayer("1.1.1.1");
//        pippo.reset();
//        System.out.println("-----------");
//        pippo.stampa();
//    }




}
