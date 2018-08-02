
package parte1;

import main.FilaPrioridade;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializador {
    
    public static void serializa(Map<Integer,Item> array, String path){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(path)));
            output.writeObject(array);
            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static Map<Integer,Item> deserializa(String path){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(path)));
            return (Map<Integer,Item>) input.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
