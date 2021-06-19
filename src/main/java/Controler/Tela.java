package Controler;

import Modelo.*;
import Auxiliar.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Hero hHero;
    private Fase faseAtual;
    private int level;
    private Posicao posicaoProjetada = new Posicao(0, 0);
    private ArrayList<Elemento> eElementos;
    private ControleDeJogo cControle = new ControleDeJogo();
    private Graphics g2;

    /**
     * Creates new form
     */
    public Tela() {
        Desenhador.setCenario(this); /* Desenhador funciona no modo estatico */
        initComponents();

        this.addMouseListener(this); /* mouse */
        this.addKeyListener(this); /* teclado */

        /* Cria a janela do tamanho do cenario + insets (bordas) da janela */
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        /* Este array vai guardar os elementos graficos */
        eElementos = new ArrayList<Elemento>(100);

        /* Cria eElementos adiciona elementos */
        /* O protagonista (heroi) necessariamente precisa estar na posicao 0 do array */
        hHero = new Hero("skooter_hero_down.png"); /* https://www.online-image-editor.com/ */
        faseAtual = new Fase(100);
        faseAtual.setFase1(hHero);
        eElementos = faseAtual;
        level = 1;
        posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
        /*
         * this.addElemento(hHero); CoronaVirus cTeste = new
         * CoronaVirus("robo_azul.png"); cTeste.setPosicao(5, 5);
         * this.addElemento(cTeste);
         * 
         * CoronaVirus cCorona = new CoronaVirus("robo.png"); cCorona.setPosicao(3, 3);
         * this.addElemento(cCorona);
         * 
         * CoronaVirus cRobo = new CoronaVirus("robo_azul.png"); cCorona.setPosicao(10,
         * 5); this.addElemento(cRobo);
         * 
         * Caveira cCaveira = new Caveira("caveira.png"); cCaveira.setPosicao(10, 9);
         * this.addElemento(cCaveira);
         */
    }

    /*--------------------------------------------------*/
    public void addElemento(Elemento umElemento) {
        eElementos.add(umElemento);
    }

    public void removeElemento(Elemento umElemento) {
        eElementos.remove(umElemento);
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }

    /* Este metodo eh executado a cada Consts.FRAME_INTERVAL milissegundos */
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /* Criamos um contexto gráfico */
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);

        /* Desenha cenário */
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    /* Linha para alterar o background */
                    Image newImage = Toolkit.getDefaultToolkit()
                            .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "background.png");
                    g2.drawImage(newImage, j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE,
                            Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /* Aqui podem ser inseridos novos processamentos de controle */
        if (!this.eElementos.isEmpty() && hHero.getVida() > 0) {
            this.cControle.desenhaTudo(eElementos);
            this.cControle.processaTudo(eElementos);
            // this.cControle.processaEsteiras(eElementos);
            if (!this.cControle.haColecionaveisAinda(eElementos)) {
                if (!this.eElementos.isEmpty()) {
                    ++level;
                }
                if (hHero.getVida() > 0 && level < 5) {
                    switch (level) {
                        case 1:
                            faseAtual.setFase1(hHero);
                            break;
                        case 2:
                            faseAtual.setFase2(hHero);
                            break;
                        case 3:
                            faseAtual.setFase3(hHero);
                            break;
                        case 4:
                            faseAtual.setFase4(hHero);
                            break;
                    }
                    eElementos = faseAtual;
                } else
                    eElementos.clear();
                    System.out.println("Fim de jogo\nObrigado por jogar o Skooter\n\n
                                       Este Jogo foi feito pela dupla\nJonattan Willian da Silva\nLucas Silva Neves\n\n
                                       Agradecimentos especiais ao prof. José Fernando Rodrigues Jr. pela extensa colaboração no desenvolvimento.");
            }
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask redesenhar = new TimerTask() {
            public void run() {
                repaint(); /* (executa o metodo paint) */
            }
        };

        /*
         * Redesenha (executa o metodo paint) tudo a cada Consts.FRAME_INTERVAL
         * milissegundos
         */
        Timer timer = new Timer();
        timer.schedule(redesenhar, 0, Consts.FRAME_INTERVAL);
    }

    private void keyPosicaoProjetada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() - 1, hHero.getPosicao().getColuna());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            posicaoProjetada.setProjecao(hHero.getPosicao().getLinha(), hHero.getPosicao().getColuna() - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            posicaoProjetada.setProjecao(hHero.getPosicao().getLinha(), hHero.getPosicao().getColuna() + 1);
        }
    }

    public void keyPressed(KeyEvent e) {
        /* Movimento do heroi via teclado */
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hHero.moveUp();
            hHero.setImage("skooter_hero_up.png");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hHero.moveDown();
            hHero.setImage("skooter_hero_down.png");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hHero.moveLeft();
            hHero.setImage("skooter_hero_left.png");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hHero.moveRight();
            hHero.setImage("skooter_hero_right.png");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            cControle.quebrarBloco(eElementos, posicaoProjetada);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            hHero.setVida(3);
            hHero.setImage("skooter_hero_down.png");
            faseAtual.setFase1(hHero);
            eElementos = faseAtual;
            level = 1;
            posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());

        }
        keyPosicaoProjetada(e);

        /*
         * Se o heroi for para uma posicao invalida, sobre um elemento intransponivel,
         * volta para onde estava
         */
        if (!cControle.ehPosicaoValida(this.eElementos, hHero.getPosicao(), posicaoProjetada)) {
            hHero.voltaAUltimaPosicao();
            keyPosicaoProjetada(e);
        }

        this.setTitle("-> Cell: " + (hHero.getPosicao().getColuna()) + ", " + (hHero.getPosicao().getLinha()));
    }

    public boolean ehPosicaoValidaRelativaAUmPersonagem(Elemento umPersonagem) {
        return cControle.ehPosicaoValidaRelativaAUmPersonagem(eElementos, umPersonagem);
    }

    public ControleDeJogo getControle() {
        return cControle;
    }

    public void mousePressed(MouseEvent e) {
        // Movimento via mouse
        int x = e.getX();
        int y = e.getY();

        this.setTitle("X: " + x + ", Y: " + y + " -> Cell: " + (y / Consts.CELL_SIDE) + ", " + (x / Consts.CELL_SIDE));

        this.hHero.getPosicao().setPosicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE);

        /*
         * Se o heroi for para uma posicao invalida, sobre um elemento intransponivel,
         * volta para onde estava
         */
        if (!cControle.ehPosicaoValida(this.eElementos, hHero.getPosicao(), posicaoProjetada)) {
            hHero.voltaAUltimaPosicao();
        }

        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2021");
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 561, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     // Variables declaration - do not modify//GEN-BEGIN:variables
     // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
