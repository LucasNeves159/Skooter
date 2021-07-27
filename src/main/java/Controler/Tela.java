package Controler;

import Modelo.*;
import ReplaceElement.ObjetoProxy;
import Auxiliar.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Hero hHero;
    private Fase faseAtual;
    private int level = 0;
    private Posicao posicaoProjetada = new Posicao(0, 0);
    private ArrayList<Elemento> eElementos;
    private ControleDeJogo cControle = new ControleDeJogo();
    private Graphics g2;
    private SaveLoad autoSave;

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
        faseAtual = new Fase(100);

        /* Cria eElementos e adiciona elementos (Seta a fase 1) */
        /* O protagonista (heroi) necessariamente precisa estar na posicao 0 do array */
        /* Posicao projetada eh a posicao que o heroi esta "olhando" */
        try {
            SaveLoad loadGame = new SaveLoad(this);
            loadGame.execute(new LoadFunction());
            hHero = (Hero) eElementos.get(0);
        } catch (Exception e) {
            hHero = new Hero("skooter_hero_down.png");
            faseAtual.setFase1(hHero);
            eElementos = faseAtual;
            level = 1;
        }
        posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
        System.out.println("Seu personagem começa com " + hHero.getVida() + " vidas");
        System.out.println("Boa sorte!");
        autoSave = new SaveLoad(this);
        autoSave.start();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int i) {
        level = i;
    }

    public void setElementos(ArrayList<Elemento> array) {
        eElementos = array;
    }

    public ArrayList<Elemento> getElementos() {
        return eElementos;
    }

    /* Este metodo eh executado a cada Consts.FRAME_INTERVAL milissegundos */
    @Override
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
            // Se nao houver mais coletaveis e a vida do heroi for maior que 0
            // Avanca uma fase, caso a array de elementos for nao esteja vazio
            // Caso contrario, reinicia a fase
            if (!this.cControle.haColecionaveisAinda(eElementos)) {
                if (!this.eElementos.isEmpty()) {
                    ++level;
                }
                if (hHero.getVida() > 0 && level < 5) {
                    nextLevel();
                    hHero.setImage("skooter_hero_down.png");
                    posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
                    eElementos = faseAtual;

                    // Se as vidas estiverem acabadas ou apos a ultima fase
                    // Mensagem final do jogo
                } else {
                    eElementos.clear();
                    System.out.println("Fim de jogo");
                    System.out.println("Obrigado por jogar o Skooter");
                    System.out.println("Este Jogo foi feito pela dupla:");
                    System.out.println("Jonattan Willian");
                    System.out.println("Lucas Silva Neves");
                    System.out.println(
                            "Agradecimentos especiais ao prof. José Fernando Rodrigues Jr. pela extensa colaboração no desenvolvimento.");
                }
            }
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    private void nextLevel() {
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
        hHero.setImage("skooter_hero_down.png");
        posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
        eElementos = faseAtual;
    }
    public void go() {
        TimerTask redesenhar = new TimerTask() {
            @Override
            public void run() {
                // verifica se o jogo está pausado
                if (!Consts.PAUSADO) {
                    repaint(); /* (executa o metodo paint) */
                }
            }
        };

        while (Consts.PAUSADO){
            // espera o player despausar
        }
        /*
         * Redesenha (executa o metodo paint) tudo a cada Consts.FRAME_INTERVAL
         * milissegundos
         */
        Timer timer = new Timer();
        timer.schedule(redesenhar, 0, Consts.FRAME_INTERVAL);
    }

    // Funcao responsavel por calcular a posicao do objeto que o heroi esta olhando
    // com base na tecla pressionada
    private void keyPosicaoProjetada(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() - 1, hHero.getPosicao().getColuna());
                break;
            case KeyEvent.VK_DOWN:
                posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
                break;
            case KeyEvent.VK_LEFT:
                posicaoProjetada.setProjecao(hHero.getPosicao().getLinha(), hHero.getPosicao().getColuna() - 1);
                break;
            case KeyEvent.VK_RIGHT:
                posicaoProjetada.setProjecao(hHero.getPosicao().getLinha(), hHero.getPosicao().getColuna() + 1);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /* Movimento do heroi via teclado */
        // A depender do movimento, o heroi carrega uma nova imagem para "simular" sua
        // animacao
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // "Seta cima" Movei para cima
                hHero.moveUp();
                hHero.setImage("skooter_hero_up.png");
                break;
            case KeyEvent.VK_DOWN:
                // "Seta baixo" Movei para Baixo
                hHero.moveDown();
                hHero.setImage("skooter_hero_down.png");
                break;
            case KeyEvent.VK_LEFT:
                // "Seta esquerda" Movei para esquerda
                hHero.moveLeft();
                hHero.setImage("skooter_hero_left.png");
                break;
            case KeyEvent.VK_RIGHT:
                // "Seta direita" Movei para direita
                hHero.moveRight();
                hHero.setImage("skooter_hero_right.png");
                break;
            case KeyEvent.VK_SPACE:
                // "ESPACO" quebra o bloco que heroi esta olhando
                cControle.quebrarBloco(eElementos, posicaoProjetada);
                break;
            case KeyEvent.VK_R:
                // "R" reinicia o jogo
                hHero.setVida(3);
                hHero.setImage("skooter_hero_down.png");
                faseAtual.setFase1(hHero);
                eElementos = faseAtual;
                level = 1;
                posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
                break;
            case KeyEvent.VK_0:
                // "1" seta autoSaveInterval = 7
                Consts.AUTOSAVEINTERVAL = 0;
                break;
            case KeyEvent.VK_1:
                // "1" seta autoSaveInterval = 7
                Consts.AUTOSAVEINTERVAL = 7;
                autoSave = new SaveLoad(this);
                autoSave.start();
                break;
            case KeyEvent.VK_2:
                // "1" seta autoSaveInterval = 7
                Consts.AUTOSAVEINTERVAL = 10;
                autoSave = new SaveLoad(this);
                autoSave.start();
                break;
            case KeyEvent.VK_3:
                // "1" seta autoSaveInterval = 7
                Consts.AUTOSAVEINTERVAL = 15;
                autoSave = new SaveLoad(this);
                autoSave.start();
                break;
            case KeyEvent.VK_N:
                // "N" passa para a prox fase
                if (level < 4) {
                    level++;
                    nextLevel();
                }
                break;
            case KeyEvent.VK_B:
                // "M" volta uma fase
                if (level > 1) {
                    level--;
                    nextLevel();
                }
                break;
            case KeyEvent.VK_S:
                // "S" salva o jogo
                SaveLoad saveGame = new SaveLoad(this);
                saveGame.execute(new SaveFunction());
                break;
            case KeyEvent.VK_L:
                // "L" carrega um jogo salvo
                ArrayList<Elemento> aux = eElementos;
                int auxLevel = level;
                try {
                    SaveLoad loadGame = new SaveLoad(this);
                    loadGame.execute(new LoadFunction());
                    hHero = (Hero) eElementos.get(0);
                    posicaoProjetada.setProjecao(hHero.getPosicao().getLinha() + 1, hHero.getPosicao().getColuna());
                } catch (Exception exc) {
                    eElementos = aux;
                    hHero = (Hero) eElementos.get(0);
                    level = auxLevel;
                }
                System.out.println("Seu personagem tem " + hHero.getVida() + " vidas restantes");
                break;
            case KeyEvent.VK_P:
                // Pausa a execução do repaint
                Consts.pauseGame();
                break;
            default:
                break;
        }
        // calcula nova posicao projetada
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public int getElement(Posicao posicao) {
        for (int i = 1; i < eElementos.size(); i++) {
            if (eElementos.get(i).getPosicao().estaNaMesmaPosicao(posicao)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {

            int x = e.getX();
            int y = e.getY();
            x -= getInsets().left;
            y -= getInsets().top;

            int index = getElement(new Posicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE));

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione um .obj.gz dentro da pasta objetos",
                    "gz");
            chooser.setFileFilter(filter);
            try {
                chooser.setCurrentDirectory(new File(new File(".").getCanonicalPath() + Consts.OBJETOPATH));
            } catch (Exception exc) {
                System.out.println("Pasta \"objeto\" não existe");
            }

            int retorno = chooser.showOpenDialog(this);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                if (index != -1) {
                    eElementos.remove(index);
                    eElementos.add(index, new ObjetoProxy(chooser.getSelectedFile().getName())
                            .exibeObjeto(new Posicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE)));
                } else
                    eElementos.add(new ObjetoProxy(chooser.getSelectedFile().getName())
                            .exibeObjeto(new Posicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE)));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
