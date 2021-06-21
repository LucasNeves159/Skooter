package Modelo;

import Auxiliar.Posicao;

public class QuadradoVerdeLosango extends Elemento {
    // Construtor do quadrado verde losango que recebe uma posicao
    // Eh movivel e pode ser quebrado
    public QuadradoVerdeLosango(Posicao umaPosicao) {
        super("quadradoVerdeLosango.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bQuebravel = true;
        bMovivel = true;
    }

    // Mesmo construtor so que quando quebrado libera o powerUp
    public QuadradoVerdeLosango(Posicao umaPosicao, boolean powerUp) {
        super("quadradoVerdeLosango.png");
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bQuebravel = true;
        bPowerUp = powerUp;
    }
}
