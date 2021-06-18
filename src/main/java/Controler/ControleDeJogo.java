package Controler;

import Modelo.Elemento;
import Modelo.Hero;
import Auxiliar.Posicao;
import java.util.ArrayList;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (int i = 0; i < e.size(); i++) {
            e.get(i).autoDesenho();
        }
    }

    public void processaTudo(ArrayList<Elemento> e) {
        Hero hHero = (Hero) e.get(0); /* O heroi (protagonista) eh sempre o primeiro do array */
        Elemento eTemp;
        /* Processa todos os demais em relacao ao heroi */
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            /* Verifica se o heroi se sobrepoe ao i-ésimo elemento */
            if (hHero.getPosicao().estaNaMesmaPosicao(eTemp.getPosicao()))
                /* Nem todos os elementos podem ser transpostos pelo heroi */
                if (eTemp.isbTransponivel())
                    e.remove(eTemp);
                else if (eTemp.isbMortal()) {
                    hHero.setVida(hHero.getVida() - 1);
                    e.clear();
                }
        }
    }

    private boolean ehPosicaoValidaAux(ArrayList<Elemento> e, Posicao newP) {
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
                        if (ehPosicaoValidaAux(e, newP) && eTemp.setPosicao(newP))
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
        /* Validacao da posicao de todos os elementos com relacao a Posicao p */
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            if (eTemp == umElemento)
                continue;
            if (eTemp.getPosicao().estaNaMesmaPosicao(umElemento.getPosicao()))
                /*
                 * A posicao p é invalida, pois ha um elemento (i-esimo eTemp) intransponivel lá
                 */
                return false;
        }
        return true;
    }

    public boolean haColecionaveisAinda(ArrayList<Elemento> e) {
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            if (e.get(i).getClass().getName() == "Modelo.Coletavel")
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