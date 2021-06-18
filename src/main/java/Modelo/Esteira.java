package Modelo;

import Auxiliar.Posicao;
import java.io.Serializable;
import java.util.ArrayList;

public class Esteira extends Elemento implements Serializable {
    private int direcao;
    
    public Esteira(int dir, String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        this.setDir(dir);
        bEsteira = true;
    }
    
    private void setDir(int dir){
        direcao = dir;
    }
    
    public int getDir(){
        return direcao;
    }
    
    // socorrooo
    public void arremessar(ArrayList<Elemento> e) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getPosicao().estaNaMesmaPosicao(this.getPosicao())) {
                if (e.get(i).isbMovivel()) {
                    switch (this.getDir()) {
                        case 0:
                            e.get(i).moveUp();
                            break;

                        case 1:
                            e.get(i).moveRight();
                            break;

                        case 2:
                            e.get(i).moveDown();
                            break;

                        case 3:
                            e.get(i).moveLeft();
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
            
}

