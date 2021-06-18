package Modelo;

import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import java.io.Serializable;

public class QuadradoSeta extends Elemento implements Serializable {
    private final int direcao;

    public QuadradoSeta(int dir, String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        direcao = dir;
        this.bTransponivel = false;
    }
    
    public int getDir(){
        return direcao;
    }

    @Override
    public void autoDesenho() {
        switch(this.getDir()) {
            case 0:
                this.moveUp();
                break;

            case 1:
                this.moveRight();
                break;

            case 2:
                this.moveDown();
                break;

            case 3:
                this.moveLeft();
                break;

            default:
                break;
            }
        
            if (!Desenhador.getTelaDoJogo().ehPosicaoValidaRelativaAUmPersonagem(this)) {
                this.getPosicao().volta();
            }

        super.autoDesenho();
    }
}

