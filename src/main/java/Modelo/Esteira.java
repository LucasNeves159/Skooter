package Modelo;

import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import java.util.ArrayList;

public class Esteira extends Elemento {
    // Direcao em que a esteira empurra o objeto
    private int direcao;

    /*
     * Construtor que recebe uma direcao, um nome de imagem e uma posicao 0 - cima 1
     * - direita 2 - baixo 3 - esquerda
     */
    public Esteira(int dir, String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        this.setDir(dir);
        bEsteira = true;
    }

    // Setter
    private void setDir(int dir) {
        direcao = dir;
    }

    // Getter
    public int getDir() {
        return direcao;
    }

    // Mecânica da esteira
    public void arremessar(ArrayList<Elemento> e) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getPosicao().estaNaMesmaPosicao(this.getPosicao())) {
                // se está em cima da esteira, joga o objeto pra direção apontada
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
                // Verifica se a posição jogada eh valida e corrige caso não for
                if (!Desenhador.getTelaDoJogo().ehPosicaoValidaRelativaAUmPersonagem(e.get(i))) {
                    e.get(i).getPosicao().volta();
                }
                break;
            }
        }
    }

}
