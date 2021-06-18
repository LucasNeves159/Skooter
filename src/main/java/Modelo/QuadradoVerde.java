package Modelo;

import Auxiliar.Posicao;

public class QuadradoVerde extends Elemento {
    public QuadradoVerde(Posicao umaPosicao) {
        super("quadradoVerde.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMortal = false;
        bQuebravel = true;
    }
}
