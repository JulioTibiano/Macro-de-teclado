
package parte1;

import java.awt.Color;
import java.io.Serializable;
import main.Estado;

public class Amuleto extends Item implements Serializable{
    
    public static final int TEMPO = 10; // delay 0s
    public Amuleto(String nome, int porcentagem, RetanguloTibia barra, int tempo,int keyEv) {
        super(nome, porcentagem, barra, TEMPO , keyEv);
    }


    
}
