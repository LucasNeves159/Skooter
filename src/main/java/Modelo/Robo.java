package Modelo;

import java.util.Random;

import Auxiliar.Desenhador;
import Auxiliar.Posicao;

public class Robo extends Elemento {
    // Construtor de robo que recebe um nome de imagem e uma posicao inicial
    // Mata o heroi caso se choque com ele
    public Robo(String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMortal = true;
    }

    // Move-se randomicamente
    public void autoDesenho() {
        Random r = new Random();
        int iDirecao = r.nextInt(4);
        switch (iDirecao) {
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
