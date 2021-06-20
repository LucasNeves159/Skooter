package Modelo;

import Auxiliar.Posicao;

public class QuadradoVermelhoLosango extends Elemento {
    // Construtor do quadrado vermelho losango que recebe uma posicao
    // Eh movivel e nao pode ser quebrado
    public QuadradoVermelhoLosango(Posicao umaPosicao) {
        super("quadradoVermelhoLosango.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMovivel = true;
    }
}
