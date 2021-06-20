package Modelo;

public class Hero extends Elemento {
    private int vida;

    // Constructor que recebe um nome de imagem para o heroi
    public Hero(String sNomeImagePNG) {
        super(sNomeImagePNG);
        // Vida inicial = 3
        vida = 3;
    }

    // Funcao para voltar a ultima posicao, caso a atual nao seja valida
    public void voltaAUltimaPosicao() {
        this.pPosicao.volta();
    }

    // Getter de vida
    public int getVida() {
        return vida;
    }

    // Setter de vida
    public void setVida(int iVida) {
        vida = iVida;
    }
}
