package online.darklounge.foreignsentry.localDatabase;

import java.util.HashMap;
import java.net.InetSocketAddress;

/**
 * Lista tentativi di accesso falliti
 * IP | Numero tentativi falliti
 *
 */
public class ListaTentativiAccesso {

    private final HashMap<InetSocketAddress, Integer> listaTentativiAccesso;

    public ListaTentativiAccesso() {
        this.listaTentativiAccesso = new HashMap<InetSocketAddress, Integer>();
    }

    public Integer getListaTentativiAccesso(InetSocketAddress x) {
        return listaTentativiAccesso.get(x);
    }

    public void setListaTentativiAccesso(InetSocketAddress x){
        if(listaTentativiAccesso.containsKey(x)){
            listaTentativiAccesso.put(x,listaTentativiAccesso.get(x)+1);
        }else{
            listaTentativiAccesso.put(x,1);
        }
    }


}
