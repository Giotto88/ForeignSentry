package online.darklounge.foreignsentry.localDatabase;

import org.bukkit.Bukkit;

import java.net.InetAddress;
import java.util.HashMap;
import java.net.InetSocketAddress;

/**
 * Lista tentativi di accesso falliti
 * IP | Numero tentativi falliti
 *
 */
public class ListaTentativiAccesso {

    private final HashMap<InetAddress, Integer> listaTentativiAccesso;

    public ListaTentativiAccesso() {
        this.listaTentativiAccesso = new HashMap<InetAddress, Integer>();
    }

    public Integer getListaTentativiAccesso(InetAddress x) {
        return listaTentativiAccesso.getOrDefault(x, 0);
    }

    public void setListaTentativiAccesso(InetAddress x){
        listaTentativiAccesso.put(x, (Integer) listaTentativiAccesso.getOrDefault(x, 0)+1);
    }


}
