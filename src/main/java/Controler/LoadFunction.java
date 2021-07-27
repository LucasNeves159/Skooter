package Controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import Auxiliar.Consts;

public class LoadFunction implements SaveLoadFunction {
    public void run(SaveLoad sl) {
        try {
            File fTemp = new File(new java.io.File(".").getCanonicalPath() + Consts.SAVEPATH + "saveData.gz");
            if (!fTemp.exists()) {
                fTemp = new File(new java.io.File(".").getCanonicalPath() + Consts.SAVEPATH + "autoSaveData.gz");
            }

            FileInputStream canoIn = new FileInputStream(fTemp);
            GZIPInputStream descompactador = new GZIPInputStream(canoIn);
            ObjectInputStream deserializador = null;

            // while (true) {
            // try {
            deserializador = new ObjectInputStream(descompactador);
            sl.getT().setLevel(deserializador.readInt());
            sl.getT().setElementos((ArrayList) deserializador.readObject());
            // System.out.println(myData.size());
            // } catch (EOFException e) {
            // break;
            // } catch (Exception e) {
            // // System.out.println("Erro na Leitura do Arquivo !");
            // myData = null;
            // level = 1;
            // break;
            // }
            // }

            deserializador.close();
            descompactador.close();
            canoIn.close();
        } catch (Exception e) {
            sl.getT().getElementos().clear();
        }
    }
}
