package Modelo;

import java.util.Random;

import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import java.util.Timer;
import java.util.TimerTask;

public class Robo extends Elemento {
    private Timer timer;
    // Construtor de robo que recebe um nome de imagem e uma posicao inicial
    // Mata o heroi caso se choque com ele
    public Robo(String sNomeImagePNG, Posicao umaPosicao) {
        super(sNomeImagePNG);
        this.setPosicao(umaPosicao);
        bTransponivel = false;
        bMortal = true;
    }

    // Move-se randomicamente
    @Override
    public void autoDesenho() {
        Random r = new Random();
        int iDirecao = r.nextInt(4);
        switch (iDirecao) {
            case 0:
                this.moveUp();
                break;

            case 1:
                this.moveRight();
                break;

            case 2:
                this.moveDown();
                break;

            case 3:
                this.moveLeft();
                break;

            default:
                break;
        }
        if (!Desenhador.getTelaDoJogo().ehPosicaoValidaRelativaAUmPersonagem(this)) {
            this.getPosicao().volta();
        }
        super.autoDesenho();
    }
    
    //Método que define estado de vulnerabilidade
    public void setVulneravel(){
        bTransponivel = !bTransponivel;
        bMortal = !bMortal;
    }
    
    public void ligaVulneravel(){
        timer = new Timer();
        TimerTask desliga;
        
        //definindo a task de desligar!
        desliga = new TimerTask() {
            public void run() {
                setVulneravel();
                System.out.println("Perigo!");
            }
        };
        
        //esse roda primeiro
        this.setVulneravel();
        //"temporizador agendado para rodar 'desliga' em 6 segundos"
        timer.schedule(desliga, 6000);
    }
}
