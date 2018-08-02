/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import main.Aplicaçao;
import main.Estado;

/**
 *
 * @author julio
 */
public abstract class Item implements Utilizavel, Serializable {

    private String nome;
    private int porcentagem;
    private RetanguloTibia barra;
    private int tempo;
    private int keyEv;

    public Item(String nome, int porcentagem, RetanguloTibia barra, int tempo, int keyEv) {
        this.nome = nome;
        this.porcentagem = porcentagem;
        this.barra = barra;
        this.tempo = tempo;
        this.keyEv = keyEv;
    }

    private boolean isAtivo = true;
    private int tempoTimer = 0;

    public void pressionaTecla() {
        Robot bot = Estado.getInstancia().getRobot();
        bot.keyPress(KeyEvent.VK_CONTROL);
        bot.keyPress(keyEv);
        bot.keyRelease(keyEv);
        bot.keyRelease(KeyEvent.VK_CONTROL);
        bot.delay(Aplicaçao.tempo_item);

    }

    @Override
    public void usa() {
        this.pressionaTecla();
        
    }

    @Override
    public boolean isReady() {
        RetanguloTibia barra = this.getBarra();
        Color cor = Estado.getInstancia().getRobot().getPixelColor(barra.getCalculusPOSX(this.getPorcentagem()), barra.getCalculusPOSY());
        if ((barra.nome.equals("VIDA") && cor.getRed() < 155)
                || (barra.nome.equals("MANA") && cor.getBlue() < 120)) {

            return true;
        }

        return false;
    }

    @Override
    public boolean verifica() { // falta verificar o tempo_conj e tipo_conj
        boolean tof = false;

        if (this.isIsAtivo()) {
            this.setIsAtivo(false);
            this.setTempoTimer(this.getTempo());
            if (this.isReady()) {
                this.usa();
                tof = true;
            }

        } else {
            decrementaTempo();
        }
        return tof;
    }

    @Override
    public void decrementaTempo() {
        this.setTempoTimer(this.getTempoTimer() - Timer.DELAY);

        if (this.getTempoTimer() <= 0) {
            this.setIsAtivo(true);
            this.setTempoTimer(0);
        }
    }
    
    @Override
    public String toString(){
        return this.getNome() +" " +this.getPorcentagem()+"% "+this.getBarra().nome  +" ";
    }
    

    /*
            SETTERS E GETTERS
     */
    public int getTempoTimer() {
        return tempoTimer;
    }

    public void setTempoTimer(int tempoTimer) {
        this.tempoTimer = tempoTimer;
    }

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public RetanguloTibia getBarra() {
        return barra;
    }

    public void setBarra(RetanguloTibia barra) {
        this.barra = barra;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

}
