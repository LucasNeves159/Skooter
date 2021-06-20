package Modelo;

import Auxiliar.Posicao;

// Classe auxiliar para instanciar uma esteira com direcao para direita
public class EsteiraDireita extends Esteira {
    public EsteiraDireita(Posicao umaPosicao) {
        super(1, "esteiraRight.png", umaPosicao);
    }
}