package Modelo;

import Auxiliar.Posicao;

public class QuadradoVermelhoLosango extends Elemento {
    public QuadradoVermelhoLosango(Posicao umaPosicao) {
        super("quadradoVermelhoLosango.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMovivel = true;
    }
}
