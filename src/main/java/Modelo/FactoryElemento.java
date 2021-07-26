package Modelo;

import Auxiliar.Posicao;

public class FactoryElemento {
    public static Elemento getElemento(String name, Posicao posicao) {
        switch (name) {
            case "Uva":
                return new Uva(posicao);
            case "Limao":
                return new Limao(posicao);
            case "Morango":
                return new Morango(posicao);
            case "Cereja":
                return new Cereja(posicao);
            case "EsteiraCima":
                return new EsteiraCima(posicao);
            case "EsteiraBaixo":
                return new EsteiraBaixo(posicao);
            case "EsteiraDireita":
                return new EsteiraDireita(posicao);
            case "EsteiraEsquerda":
                return new EsteiraEsquerda(posicao);
            case "QuadradoVerde":
                return new QuadradoVerde(posicao);
            case "QuadradoVerdeCoracao":
                return new QuadradoVerde(posicao, true);
            case "QuadradoVerdeLosango":
                return new QuadradoVerdeLosango(posicao);
            case "QuadradoVerdeLosangoCoracao":
                return new QuadradoVerdeLosango(posicao, true);
            case "QuadradoVermelho":
                return new QuadradoVermelho(posicao);
            case "QuadradoVermelhoLosango":
                return new QuadradoVermelhoLosango(posicao);
            case "RoboAmarelo":
                return new RoboAmarelo(posicao);
            case "RoboAzul":
                return new RoboAzul(posicao);
            case "RoboRosa":
                return new RoboRosa(posicao);
            case "RoboVerde":
                return new RoboVerde(posicao);

            default:
                return null;
        }
    }
}
