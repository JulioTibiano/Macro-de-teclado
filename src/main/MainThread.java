package main;


import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import parte1.Timer;

public class MainThread implements Runnable {

    private Aplicaçao aplicaçao;
    private Estado estado;
    public MainThread() {
        try {
            estado = Estado.getInstancia();
            aplicaçao = new Aplicaçao();
        } catch (AWTException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        
        try {


            
            do {
                long inicio = System.currentTimeMillis();
                aplicaçao.loop();
                long fim = System.currentTimeMillis();
                
                //System.out.println(fim-inicio);
                
            } while (true);

        } catch (Exception ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}
