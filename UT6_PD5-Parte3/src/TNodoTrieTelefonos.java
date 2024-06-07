

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {
    private HashMap<Character, TNodoTrieTelefonos> hijos;
    TAbonado abonado;
    private boolean esAbonado;
    public TNodoTrieTelefonos() {
        hijos = new HashMap<>();
        esAbonado = false;
        abonado = null;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieTelefonos nodo = this;
        for(int c = 0; c < primerosDigitos.length(); c++) {
            char character = primerosDigitos.charAt(c);
            if(nodo == null) {
                return; //No encontramos un abonado que tenga ese prefijo;
            }
            nodo = nodo.hijos.get(character);
        }
        //Llegado a este punto, tenemos los abonados los cuáles los primerosDigitos es un prefijo de su telefono,
        //Ahora tenemos que guardarlos en una estructura
        //SOLO RECOGEMOS LOS  ABONADOS EN ESTE CASO QUE TIENEN ESTAS CARACTERÍSTICAS.
        nodo.obtenerAbonados(abonados);
    }

    private void obtenerAbonados(LinkedList<TAbonado> abonados) {
        //Esta función obtiene los abonados recorriendo el trie de manera recursiva.
        if(esAbonado) {
            abonados.add(abonado);
        }
        for(TNodoTrieTelefonos hijo : hijos.values()) {
            if(hijo != null) {
                hijo.obtenerAbonados(abonados);
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrieTelefonos nodo = this;
        String numeroAbonado = unAbonado.getTelefono();
        for(int c = 0; c < numeroAbonado.length(); c++) {
            char character  = numeroAbonado.charAt(c);
            nodo.hijos.putIfAbsent(character, new TNodoTrieTelefonos());
            nodo = nodo.hijos.get(character);
        }
        nodo.esAbonado = true;
        nodo.abonado = unAbonado;
    }

}
