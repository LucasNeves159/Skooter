package Modelo;

import Auxiliar.Posicao;

public class Coletavel extends Elemento {
    public Coletavel(String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        bTransponivel = true;
    }
}
