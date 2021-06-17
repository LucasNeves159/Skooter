package Modelo;

import Auxiliar.Posicao;

public class QuadradoVermelho extends Elemento {
    public QuadradoVermelho(Posicao umaPosicao) {
        super("quadradoVermelho.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
    }
}
