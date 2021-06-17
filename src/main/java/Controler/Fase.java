package Controler;

import java.util.ArrayList;

import Auxiliar.Posicao;
import Modelo.Coletavel;
import Modelo.Elemento;
import Modelo.Hero;
import Modelo.QuadradoVermelho;
import Modelo.Robo;

public class Fase extends ArrayList<Elemento> {
    public Fase(int iSize) {
        super(iSize);
    }

    public void setFase1(Hero umHero) {
        this.clear();
        this.add(umHero);
        umHero.setPosicao(4, 4);
        this.add(new Coletavel("uva.png", new Posicao(0, 0)));
        this.add(new Coletavel("limao.png", new Posicao(0, 10)));
        this.add(new Coletavel("morango.png", new Posicao(10, 0)));
        this.add(new Coletavel("cereja.png", new Posicao(10, 10)));
        this.add(new Robo("roboAmarelo.png", new Posicao(2, 0)));
        this.add(new Robo("roboAzul.png", new Posicao(10, 1)));
        this.add(new Robo("roboRosa.png", new Posicao(0, 9)));
        this.add(new Robo("roboVerde.png", new Posicao(10, 9)));
        this.add(new QuadradoVermelho(new Posicao(1, 1)));
        this.add(new QuadradoVermelho(new Posicao(1, 3)));
        this.add(new QuadradoVermelho(new Posicao(1, 5)));
        this.add(new QuadradoVermelho(new Posicao(1, 7)));
        this.add(new QuadradoVermelho(new Posicao(1, 9)));
        this.add(new QuadradoVermelho(new Posicao(3, 1)));
        this.add(new QuadradoVermelho(new Posicao(3, 3)));
        this.add(new QuadradoVermelho(new Posicao(3, 5)));
        this.add(new QuadradoVermelho(new Posicao(3, 7)));
        this.add(new QuadradoVermelho(new Posicao(3, 9)));
        this.add(new QuadradoVermelho(new Posicao(5, 1)));
        this.add(new QuadradoVermelho(new Posicao(5, 3)));
        this.add(new QuadradoVermelho(new Posicao(5, 5)));
        this.add(new QuadradoVermelho(new Posicao(5, 7)));
        this.add(new QuadradoVermelho(new Posicao(5, 9)));
        this.add(new QuadradoVermelho(new Posicao(7, 1)));
        this.add(new QuadradoVermelho(new Posicao(7, 3)));
        this.add(new QuadradoVermelho(new Posicao(7, 5)));
        this.add(new QuadradoVermelho(new Posicao(7, 7)));
        this.add(new QuadradoVermelho(new Posicao(7, 9)));
        this.add(new QuadradoVermelho(new Posicao(9, 1)));
        this.add(new QuadradoVermelho(new Posicao(9, 3)));
        this.add(new QuadradoVermelho(new Posicao(9, 5)));
        this.add(new QuadradoVermelho(new Posicao(9, 7)));
        this.add(new QuadradoVermelho(new Posicao(9, 9)));
    }

    public void setFase2(Hero umHero) {
        this.clear();
        this.add(umHero);
        umHero.setPosicao(5, 5);
        this.add(new Coletavel("uva.png", new Posicao(1, 5)));
        this.add(new Coletavel("limao.png", new Posicao(5, 1)));
        this.add(new Coletavel("morango.png", new Posicao(5, 9)));
        this.add(new Coletavel("cereja.png", new Posicao(9, 5)));
        this.add(new Robo("roboAmarelo.png", new Posicao(1, 1)));
        this.add(new Robo("roboAzul.png", new Posicao(9, 9)));
        this.add(new Robo("roboRosa.png", new Posicao(9, 1)));
        this.add(new Robo("roboVerde.png", new Posicao(1, 9)));
        this.add(new QuadradoVermelho(new Posicao(1, 3)));
        this.add(new QuadradoVermelho(new Posicao(1, 7)));
        this.add(new QuadradoVermelho(new Posicao(3, 1)));
        this.add(new QuadradoVermelho(new Posicao(3, 3)));
        this.add(new QuadradoVermelho(new Posicao(3, 5)));
        this.add(new QuadradoVermelho(new Posicao(3, 7)));
        this.add(new QuadradoVermelho(new Posicao(3, 9)));
        this.add(new QuadradoVermelho(new Posicao(5, 3)));
        this.add(new QuadradoVermelho(new Posicao(5, 7)));
        this.add(new QuadradoVermelho(new Posicao(7, 1)));
        this.add(new QuadradoVermelho(new Posicao(7, 3)));
        this.add(new QuadradoVermelho(new Posicao(7, 5)));
        this.add(new QuadradoVermelho(new Posicao(7, 7)));
        this.add(new QuadradoVermelho(new Posicao(7, 9)));
        this.add(new QuadradoVermelho(new Posicao(9, 3)));
        this.add(new QuadradoVermelho(new Posicao(9, 7)));
    }
}
