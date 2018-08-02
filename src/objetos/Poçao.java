package parte1;

import java.awt.Color;
import java.io.Serializable;
import main.Estado;

public class Poçao extends Item implements Serializable{

    public static final int TEMPO = 1000; // delay :  1s

    public Poçao(String nome, int porcentagem, RetanguloTibia barra, int tempo, int keyEv) {
        super(nome, porcentagem, barra, TEMPO, keyEv);
    }

    @Override
    public boolean isReady() {
        RetanguloTibia barra = this.getBarra();
        Color cor = Estado.getInstancia().getRobot().getPixelColor(barra.getCalculusPOSX(this.getPorcentagem()), barra.getCalculusPOSY());
        if ((barra.nome.equals("VIDA") && cor.getRed() < 155)
                || (barra.nome.equals("MANA") && cor.getBlue() < 120)) {
            if(!Estado.getInstancia().getJogador().isAtivoPotion()){
                Estado.getInstancia().getJogador().setTempoPotion(TEMPO);
                return true;
            }
        }

        return false;
    }
}
