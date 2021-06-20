package Modelo;

import Auxiliar.Posicao;

// Classe auxiliar para instanciar uma esteira com direcao para esquerda
public class EsteiraEsquerda extends Esteira {
    public EsteiraEsquerda(Posicao umaPosicao) {
        super(3, "esteiraLeft.png", umaPosicao);
    }
}
