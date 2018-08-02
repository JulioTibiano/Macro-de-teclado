package parte1;

import java.awt.Color;
import java.io.Serializable;
import main.Estado;

public class Magia extends Item implements Serializable {

    public static final int HEALING = 0,
            SUPPORT = 1,
            CONJURE = 2,
            SPECIAL = 3,
            ATTACK = 4;

    public Magia(String nome,
            int porcentagem,
            RetanguloTibia barra,
            int tempo,
            int tempo_conj,
            int tipo_conj,
            int keyEv
    ) {

        super(nome, porcentagem, barra, tempo, keyEv);
        this.tempo_conj = tempo_conj;
        this.tipo_conj = tipo_conj;

    }

    private final int tempo_conj, tipo_conj;

    @Override
    public boolean isReady() {
        RetanguloTibia barra = this.getBarra();
        Color cor = Estado.getInstancia().getRobot().getPixelColor(barra.getCalculusPOSX(this.getPorcentagem()), barra.getCalculusPOSY());
        if ((barra.nome.equals("VIDA") && cor.getRed() < 155)
                || (barra.nome.equals("MANA") && cor.getBlue() < 120)) {

            if (!Estado.getInstancia().getJogador().isAtivoSpell(tipo_conj)) {
                Estado.getInstancia().getJogador().setTempoSpell(tipo_conj, tempo_conj);
                return true;
            }

        }

        return false;
    }

}
