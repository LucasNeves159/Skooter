package Modelo;

import Auxiliar.Posicao;

public class Coletavel extends Elemento {
    // Construtor que recebe o nome da imagem a ser carregada
    // e a posicao que esta localizada
    public Coletavel(String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        // Eh transponivel
        bTransponivel = true;
        // Eh um coletavel
        bColetavel = true;
    }
}
