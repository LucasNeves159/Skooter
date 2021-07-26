package Controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import Auxiliar.Consts;

public class AutoSaveFunction implements SaveLoadFunction {
    public void run(SaveLoad sl) {
        try {
            File fTemp = new File(new java.io.File(".").getCanonicalPath() + Consts.SAVEPATH + "autoSaveData.gz");
            // File fTemp = new File(Consts.SAVEPATH + "saveData.gz");
            if (fTemp.exists()) {
                fTemp.delete();
            }
            fTemp.createNewFile();

            FileOutputStream canoOut = new FileOutputStream(fTemp, true);
            GZIPOutputStream compactador = new GZIPOutputStream(canoOut);
            ObjectOutputStream serializador = new ObjectOutputStream(compactador);

            serializador.writeInt(sl.getT().getLevel());
            serializador.writeObject(sl.getT().getElementos());
            serializador.flush();

            serializador.close();
            compactador.close();
            canoOut.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel salvar o jogo");
            System.out.println("Tente novamente");
        }
    }

}
