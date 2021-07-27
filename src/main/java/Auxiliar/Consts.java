package Auxiliar;

import java.io.File;

public class Consts {
    // public static final int CELL_SIDE = 85;
    public static final int CELL_SIDE = 60;
    public static final int RES = 11;
    // public static final int FRAME_INTERVAL = 100;
    public static int FRAME_INTERVAL = 100;
    public static boolean PAUSED = false;
    public static final int TIMER_DISPARO = 20; /* Em numero de frames (redesenhos) */
    public static final String PATH = File.separator + "imgs" + File.separator;
    public static final String SAVEPATH = File.separator + "save" + File.separator;
    public static final String OBJETOPATH = File.separator + "objeto" + File.separator;
    // Tempo do autoSave em segundos
    public static int AUTOSAVEINTERVAL = 10;
    
    public static void pauseGame(){
        if (PAUSED){
            FRAME_INTERVAL = 100;
            PAUSED = false;
            System.out.println("teje pausado");
        } else {
            FRAME_INTERVAL = 0;
            PAUSED = true;
            System.out.println("teje jogando");
        }
    }
}
