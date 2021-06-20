package Modelo;

import Controler.Tela;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Mensagem de inicio do jogo
        System.out.println("Seu personagem começa com 3 vidas");
        System.out.println("Boa sorte!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela tTela = new Tela();
                tTela.setVisible(true);
                tTela.createBufferStrategy(2);
                tTela.go();
            }
        });
    }
}
