package Controler;

import Auxiliar.Consts;

public class SaveLoad implements Runnable{
    private Tela t;

    // define a tela sendo usada
    public SaveLoad(Tela t) {
        this.t = t;
    }

    // retorna a tela que está sendo usada
    public Tela getT() {
        return t;
    }

    // this se refera à instância
    public void execute(SaveLoadFunction slFunction) {
        slFunction.run(this);
    }

    @Override
    public void run() {
        try {
            while (Consts.AUTOSAVEINTERVAL != 0) {
                SaveFunction saveGame;
                saveGame = new SaveFunction();
                SingletonThread saveState = SingletonThread.getInstance(saveGame);
                execute(saveState.getValue());
                Thread.sleep(Consts.AUTOSAVEINTERVAL * 1000);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
