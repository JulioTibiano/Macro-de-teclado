package main;

import parte1.Timer;

public class Jogador{

    public Jogador() {
        spell = new Status[5];
        for(int x = 0 ; x < spell.length ; x++)
            spell[x] = new Status();
        
        potion = new Status(); 
    }

    /*
     magias[0] = HEALING;
     magias[1] = SUPPORT;
     magias[2] = CONJURE;
     magias[3] = SPECIAL;
     magias[4] = ATTACK;
     
     potion;
    
    
    
     */
    private Status[] spell;
    private Status potion;
    public void setTempoSpell(int tipo , int tempo){
        spell[tipo].setTempo(tempo);
    }
    public void setTempoPotion(int tempo){
        potion.setTempo(tempo);
    }
    public boolean isAtivoSpell(int pos){
        return spell[pos].verifica();
    }
    public boolean isAtivoPotion(){
        return potion.verifica();
    }

    
    public void decrementaTempoStatus() {
        
        for(int x = 0 ; x < spell.length ; x++)
            spell[x].decrementaTempo();
        
        potion.decrementaTempo();
        
    }
    

    public class Status implements Timer {
        public Status(){
            tempo = 0;
        }
        
        private int tempo;
        @Override
        public boolean verifica() {
            return tempo>0;
        }

        @Override
        public void decrementaTempo() {
            if(verifica())
                tempo = tempo - Timer.DELAY;
        }
        
        public void setTempo(int tempo){
            this.tempo = tempo;
        }

    }

}
