package Controler;

import Modelo.*;
import Auxiliar.Posicao;
import java.util.ArrayList;

public class ControleDeJogo {
    public boolean powerUp = true;
    
    public void desenhaTudo(ArrayList<Elemento> e) {
        /*
         * for decrescente pra que o Herói seja o ultimo objeto desenhado mantendo-o
         * sempre na tela
         */
        for (int i = e.size() - 1; i >= 0; i--) {
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
                if (eTemp.isbEsteira()) { /* Se for uma esteira, arremessa o heroi */
                    estTemp = (Esteira) eTemp;
                    estTemp.arremessar(e);

                // Se for um objeto transponivel, remove do array
                } else if (eTemp.isbTransponivel()) {
                    // caso ele seja um PowerUp, a execução começa daqui
                    if (powerUp && !eTemp.isbColetavel()) {
                        hHero.setVida(hHero.getVida() + 1); //vida +1
                        powerUp = false; //nunca mais entra nesse loop
                        //procura cada robo na tela
                        for (int j = 1; j < e.size(); j++) {
                            eTemp = e.get(j);
                            if (eTemp.isbMortal()) {
                                // aplica a vulnerabilidade
                                Robo tecoteco = (Robo) eTemp;  
                                tecoteco.ligaVulneravel();
                            }
                        }
                        System.out.println("GodMode por 6 segundos!");
                    }
                    eTemp = e.get(i);
                    e.remove(eTemp);
                }

                // Se for um robo (mortal), tira uma vida do heroi
                else if (eTemp.isbMortal()) {
                    hHero.setVida(hHero.getVida() - 1);
                    powerUp = true;
                    System.out.println("Vidas restantes: " + hHero.getVida());
                    e.clear();
                }
        }
    }

    private boolean ehPosicaoValidaMovivel(ArrayList<Elemento> e, Posicao newP) {
        Elemento eTemp;
        /* Validacao da posicao de todos os elementos com relacao a Posicao newP */
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            eTemp = e.get(i); /* Pega o i-esimo elemento do jogo */
            // Verifica se a posicao seguinte do bloco a ser movimentada eh valida
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
            // Se nao for transponivel, volta para o loop
            if (!eTemp.isbTransponivel())
                // Se for transponivel e esta na mesma posicao que p
                // Se for movivel e se a proxima posicao for valida
                if (eTemp.getPosicao().estaNaMesmaPosicao(p))
                    if (eTemp.isbMovivel()) {
                        // Seta a posicao do elemento para a proxima posicao
                        return ehPosicaoValidaMovivel(e, newP) && eTemp.setPosicao(newP);
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
                 * "se o elemento de interesse for transponivel ou movivel, i.e. Heroi, Vida
                 * ou BlocoVerde (unico com bandeira movivel), e meu
                 * elemento testado for uma esteira, então retorna verdadeiro, do contrário,
                 * falso."
                 */
                return eTemp.isbEsteira() && (umElemento.isbTransponivel() || umElemento.isbMovivel()); // testado
                // robôs vulneraveis sobem em esteiras                                                  // apenas sem o
                                                                                                        // bloco
        }
        return true;
    }

    public boolean haColecionaveisAinda(ArrayList<Elemento> e) {
        for (int i = 1; i < e.size(); i++) { /* Olha todos os elementos */
            // Se for um colecionavel, retorna true
            if (e.get(i).isbColetavel())
                return true;
        }
        return false;
    }

    public boolean quebrarBloco(ArrayList<Elemento> e, Posicao projecao) {
        for (int i = 0; i < e.size(); i++) { /* Olha todos os elementos */
            // Se o heroi estiver "olhando" para o bloco
            if (e.get(i).getPosicao().estaNaMesmaPosicao(projecao)) {
                // Se este for verde (quebravel)
                if (e.get(i).isbQuebravel()) {
                    // Remove do array
                    e.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
