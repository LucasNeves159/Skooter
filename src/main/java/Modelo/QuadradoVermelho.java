package Modelo;

import Auxiliar.Posicao;

public class QuadradoVermelho extends Elemento {
    // Construtor do quadrado vermelho que recebe uma posicao
    // Nao eh movivel e nao pode ser quebrado
    public QuadradoVermelho(Posicao umaPosicao) {
        super("quadradoVermelho.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
    }
}
