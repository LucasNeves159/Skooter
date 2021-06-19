package Controler;

import Modelo.Elemento;
import Modelo.Esteira;
import Modelo.Hero;
import Auxiliar.Posicao;
import java.util.ArrayList;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e) {
        /*
         * for decrescente pra que o Herói seja o ultimo objeto desenhado
         * mantendo-o sempre na tela
        */
        for (int i = e.size()-1; i >= 0; i--) {
            e.get(i).autoDesenho();
        }
    }

    public void processaTudo(ArrayList<Elemento> e) {
        Hero hHero = (Hero) e.get(0); /* O heroi (protagonista) eh sempre o primeiro do array */
        Elemento eTemp;
        Esteira estTemp;
        /* Processa todos os demais em relacao ao heroi */
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            /* Verifica se o heroi se sobrepoe ao i-ésimo elemento */
            if (hHero.getPosicao().estaNaMesmaPosicao(eTemp.getPosicao()))
                /* Nem todos os elementos podem ser transpostos pelo heroi */
                if (eTemp.isbEsteira()) {
                    estTemp = (Esteira) eTemp;
                    estTemp.arremessar(e);
                } else if (eTemp.isbTransponivel())
                    e.remove(eTemp);
                else if (eTemp.isbMortal()) {
                    hHero.setVida(hHero.getVida() - 1);
                    System.out.println("Vidas restantes: "+hHero.getVida());
                    e.clear();
                }
        }
    }

    // Verifica se é esteira e chama o método de mover objetos
    // public void processaEsteiras(ArrayList<Elemento> e) {
    // Esteira eTemp;

    // for (int i = 0; i < e.size(); i++) {
    // if (e.get(i).isbEsteira()) {
    // eTemp = (Esteira) e.get(i);
    // eTemp.arremessar(e);
    // }
    // }
    // }

    private boolean ehPosicaoValidaMovivel(ArrayList<Elemento> e, Posicao newP) {
        Elemento eTemp;
        /* Validacao da posicao de todos os elementos com relacao a Posicao newP */
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            if (eTemp.getPosicao().estaNaMesmaPosicao(newP))
                return false;
        }
        return true;
    }

    public boolean ehPosicaoValida(ArrayList<Elemento> e, Posicao p, Posicao newP) {
        Elemento eTemp;
        /* Validacao da posicao de todos os elementos com relacao a Posicao p */
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            if (!eTemp.isbTransponivel())
                if (eTemp.getPosicao().estaNaMesmaPosicao(p))
                    if (eTemp.isbMovivel()) {
                        if (ehPosicaoValidaMovivel(e, newP) && eTemp.setPosicao(newP))
                            return true;
                        else
                            return false;
                    }
                    /*
                     * A posicao p é invalida, pois ha um elemento (i-esimo eTemp) intransponivel lá
                     */
                    else
                        return false;
        }
        return true;
    }
    
    public boolean ehPosicaoValidaRelativaAUmPersonagem(ArrayList<Elemento> e, Elemento umElemento) {
        Elemento eTemp;
        /* Validacao da posicao de todos os elementos com relacao a umElemento */
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            if (eTemp == umElemento)
                continue;
            if (eTemp.getPosicao().estaNaMesmaPosicao(umElemento.getPosicao()))
                /*
                * Esta gambiarra significa: "se o elemento de interesse for transponivel ou movivel,
                * i.e. Heroi (unico transponivel que se move) ou BlocoVerde (unico com bandeira movivel),
                * e meu elemento testado for uma esteira, então retorna verdadeiro, do contrário, falso."
                */
                return eTemp.isbEsteira() && (umElemento.isbTransponivel() || umElemento.isbMovivel()); //testado apenas sem o bloco
        }
        return true;
    }

    public boolean haColecionaveisAinda(ArrayList<Elemento> e) {
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            if (e.get(i).isbColetavel())
                return true;
        }
        return false;
    }

    public boolean quebrarBloco(ArrayList<Elemento> e, Posicao projecao) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getPosicao().estaNaMesmaPosicao(projecao)) {
                if (e.get(i).isbQuebravel()) {
                    e.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
