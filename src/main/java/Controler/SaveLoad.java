package Controler;

import Auxiliar.Consts;

public class SaveLoad extends Thread {
    private Tela t;

    public SaveLoad(Tela t) {
        this.t = t;
    }

    public Tela getT() {
        return t;
    }

    public void execute(SaveLoadFunction slFunction) {
        slFunction.run(this);
    }

    @Override
    public void run() {
        try {
            while (Consts.AUTOSAVEINTERVAL != 0) {
                execute(new AutoSaveFunction());
                Thread.sleep(Consts.AUTOSAVEINTERVAL * 1000);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
