package Modelo;

import Auxiliar.Posicao;

// Classe auxiliar para instanciar uma esteira com direcao para baixo
public class EsteiraBaixo extends Esteira {
    public EsteiraBaixo(Posicao umaPosicao) {
        super(2, "esteiraDown.png", umaPosicao);
    }
}
