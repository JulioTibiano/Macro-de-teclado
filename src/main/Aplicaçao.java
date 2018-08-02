package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import main.FilaPrioridade.Celula;
import parte1.Item;
import parte1.Timer;
import parte1.Utilizavel;

public class Aplicaçao {

    Robot bot;
    public static final int tempo_item = 70;
    private final Estado estado;
    private FilaPrioridade<Utilizavel> filaExec;

    public Aplicaçao() throws AWTException {
        estado = Estado.getInstancia();
        bot = new Robot();
        filaExec = new FilaPrioridade();
        bot.delay(3000);

    }

    public void enfileira() {
        filaExec = new FilaPrioridade();
        Map<Integer, Item> map = estado.getMap();
        Set<Map.Entry<Integer, Item>> set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Item> entry = (Map.Entry) it.next();
            filaExec.insereOrdenado(entry.getValue(), entry.getKey());
        }
        // imprime();
    }
    
    public void imprime(){
        filaExec.imprime();
    }

    public int executa() throws Exception {
        enfileira();
        int count = 0;
        while(!filaExec.isVazia()){
            
            Celula celula = filaExec.remove();
            //System.out.println(celula.item.toString());
            Item item = (Item) celula.item;
            Utilizavel u = (Utilizavel) item;
            if(u.verifica())
                count++;
        }
        return count;
    }

    public void loop() throws Exception {
        int count = executa();
        estado.getJogador().decrementaTempoStatus();
        //if(clickMouse())
            //clicka();
        bot.delay( ((Timer.DELAY) - (count * tempo_item))/2); // - (count * tempo_item)
    }

    public final void clicka() {
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
