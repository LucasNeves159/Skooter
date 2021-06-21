package Modelo;

import Auxiliar.Posicao;

public class Coracao extends Robo {
    public Coracao(Posicao umaPosicao) {
        super("coracao.png", umaPosicao);
        this.setPosicao(umaPosicao);
        bTransponivel = true;
        bMortal = false;
    }
    
}
