package Modelo;

import Auxiliar.Posicao;

// Classe auxiliar para instanciar uma esteira com direcao para cima
public class EsteiraCima extends Esteira {
    public EsteiraCima(Posicao umaPosicao) {
        super(0, "esteiraUp.png", umaPosicao);
    }
}
