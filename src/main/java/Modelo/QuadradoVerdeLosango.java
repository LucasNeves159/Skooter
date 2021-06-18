package Modelo;

import Auxiliar.Posicao;

public class QuadradoVerdeLosango extends Elemento {
    public QuadradoVerdeLosango(Posicao umaPosicao) {
        super("quadradoVerdeLosango.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMortal = false;
        bQuebravel = true;
        bMovivel = true;
    }
}
