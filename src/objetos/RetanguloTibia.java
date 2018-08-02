
package parte1;

import java.awt.Rectangle;
import java.io.Serializable;


public class RetanguloTibia implements Serializable{
    public String nome;
    private boolean isAtivo;
    public boolean isAtivo(){
        return this.isAtivo;
    }
    public void setAtivo(boolean x){
        this.isAtivo = x;
    }
    private Rectangle R;

    public int POS_VIDAFINAL_X;
    public int POS_VIDAFINAL_Y;
    public int POS_VIDAINICIO_X;
    public int POS_VIDAINICIO_Y;
    
    public int getCalculusPOSX(int x){ 
        if(x<0 || x > 100)
            return POS_VIDAFINAL_X;
        double v = POS_VIDAFINAL_X - POS_VIDAINICIO_X;
        double j = v * x/100;
        
        return POS_VIDAINICIO_X + (int)j;
    }
    public int getCalculusPOSY(){
        return this.POS_VIDAFINAL_Y;
    }

    public RetanguloTibia(int POS_VIDAINICIO_X, int POS_VIDAINICIO_Y, int POS_VIDAFINAL_X, int POS_VIDAFINAL_Y,String nome) {

        this.POS_VIDAFINAL_X = POS_VIDAFINAL_X;
        this.POS_VIDAFINAL_Y = POS_VIDAFINAL_Y;
        this.POS_VIDAINICIO_X = POS_VIDAINICIO_X;
        this.POS_VIDAINICIO_Y = POS_VIDAINICIO_Y;
        this.nome = nome;
        
        int comprimento = POS_VIDAFINAL_X - POS_VIDAINICIO_X;
        int largura = 3;
        R = new Rectangle(POS_VIDAINICIO_X,POS_VIDAFINAL_Y,comprimento,largura);
    }
    
    
    @Override
    public String toString(){
        return R.toString();
    }

    Rectangle getRectangle() {
        return this.R;
    }
    
    
    
}
