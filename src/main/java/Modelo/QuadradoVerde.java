package Modelo;

import Auxiliar.Posicao;

public class QuadradoVerde extends Elemento {
    // Construtor do quadrado verde que recebe uma posicao
    // Nao eh movivel e pode ser quebrado
    public QuadradoVerde(Posicao umaPosicao) {
        super("quadradoVerde.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bQuebravel = true;
    }

    // Mesmo construtor so que quando quebrado libera o powerUp
    public QuadradoVerde(Posicao umaPosicao, boolean powerUp) {
        super("quadradoVerde.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bQuebravel = true;
        bPowerUp = powerUp;
    }
}
