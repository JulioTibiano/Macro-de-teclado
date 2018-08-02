package main;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.FilaPrioridade.Celula;
import parte1.*;

public class Estado {

    /*
        SINGLETON PATTERN
     */
    private static Estado INSTANCIA = new Estado();
    
    private final String path; 
    public String getPath(){
        return path;
    }

    private Estado() {

        barraVida = new RetanguloTibia(1217, 148, 1303, 148, "VIDA");
        barraMana = new RetanguloTibia(1217, 161, 1303, 161, "MANA");
        jogador = new Jogador();
        try {
            bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
        path = "julio.jul"; // caminho padrao
        
        start(path);
        //clear();
        
        
    }

    public static Estado getInstancia() {
        return INSTANCIA;
    }

    /*
        ATRIBUTOS DA CLASSE
     */
    private Robot bot;
    private Jogador jogador;
    private final RetanguloTibia barraVida;
    private final RetanguloTibia barraMana;

    public Jogador getJogador(){
        return this.jogador;
    }


    public void add(Integer i, Item item) {
        this.map.put(i, item);
        salva(path);
    }

    public Map<Integer, Item> getMap() {
        return this.map;
    }

    /*
        SERIALIZABLE
     */
    private Map<Integer, Item> map;

    public void start(String path) {
        map = Serializador.deserializa(path);
        if (map == null) {
            map = new HashMap<Integer, Item>();
            salva(path);
        }
    }
    public void clear(){
        map = new HashMap<Integer,Item>();
        salva(path);
    }

    public void salva(String path) {
        Serializador.serializa(map, path);
    }

    /*
        SETTERS E GETTERS
     */
    public RetanguloTibia getBarraVida() {
        return barraVida;
    }

    public RetanguloTibia getBarraMana() {
        return barraMana;
    }
    public Robot getRobot() {
        return this.bot;
    }
}
