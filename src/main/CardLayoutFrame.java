/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author julio
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.*;
import parte1.Amuleto;
import parte1.Item;
import parte1.Magia;
import parte1.Poçao;
import parte1.RetanguloTibia;

public class CardLayoutFrame implements ItemListener {

    JPanel cards; //a panel that uses CardLayout
    final static String ABA1 = "Painel 1";
    final static String ABA2 = "Painel 2";
    Estado estado;
    JCheckBox[] checkBox;

    private JPanel painelCentral;

    public void atualizaPainelCentral() {
        Estado estado = Estado.getInstancia();
        painelCentral.removeAll();
        if (estado.getMap().size() > 0) {
            Map<Integer, Item> map = estado.getMap();
            painelCentral.setLayout(new GridLayout(map.size(),1));
            checkBox = new JCheckBox[map.size()];
            Set<Entry<Integer, Item>> entry = map.entrySet();
            
            int x = 0;
            for(Entry<Integer,Item> k : map.entrySet()){
                checkBox[x] = new JCheckBox(k.getKey() + " " + k.getValue());
                painelCentral.add(checkBox[x++]); 
            }
            
            painelCentral.repaint();
            painelCentral.validate();
            
        }

    }

    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = {ABA1, ABA2};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.setLayout(new GridLayout(2, 2));
        JToggleButton playButton = new JToggleButton(" PLAY ");
        playButton.addActionListener(e -> play());
        card1.add(playButton);

        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());
        painelCentral = new JPanel();
        JPanel painelSuperior = new JPanel();
        JPanel painelInferior = new JPanel();

        painelSuperior.setLayout(new FlowLayout());
        //painelCentral.setLayout(new GridLayout(estado.getMap().size(),1));
        painelCentral.setLayout(new GridLayout(10, 1));
        painelInferior.setLayout(new FlowLayout());

        card2.add(painelCentral, BorderLayout.CENTER);
        card2.add(painelSuperior, BorderLayout.NORTH);
        card2.add(painelInferior, BorderLayout.SOUTH);


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, ABA1);
        cards.add(card2, ABA2);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
        
        atualizaPainelCentral();
    }
    
    private boolean tofItemListener = false;



    private static Thread evento = new Thread(new MainThread());

    public boolean buttonPlayIsPressed = true;

    public void play() {
        if (buttonPlayIsPressed) {
            evento.resume();
        } else {
            evento.suspend();
        }
        buttonPlayIsPressed = !buttonPlayIsPressed;
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AutoHealing DEMO v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setBackground(Color.red);
        frame.setCursor(Cursor.HAND_CURSOR);
        frame.setResizable(false);

        //Create and set up the content pane.
        CardLayoutFrame demo = new CardLayoutFrame();
        demo.addComponentToPane(frame.getContentPane());

        //initialize atributs
        evento.start();
        evento.suspend();

        //Display the window.
        frame.setLocation(0, 500);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
