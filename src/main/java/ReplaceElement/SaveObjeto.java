package ReplaceElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import Auxiliar.Consts;
import Auxiliar.Posicao;
import Modelo.Elemento;
import Modelo.FactoryElemento;

public class SaveObjeto {

    public static void main(String[] args) {
        Save("EsteiraCima.obj.gz", FactoryElemento.getElemento("EsteiraCima", new Posicao(0, 0)));
        Save("EsteiraBaixo.obj.gz", FactoryElemento.getElemento("EsteiraBaixo", new Posicao(0, 0)));
        Save("EsteiraDireita.obj.gz", FactoryElemento.getElemento("EsteiraDireita", new Posicao(0, 0)));
        Save("EsteiraEsquerda.obj.gz", FactoryElemento.getElemento("EsteiraEsquerda", new Posicao(0, 0)));
        Save("EsteiraEsquerda.obj.gz", FactoryElemento.getElemento("EsteiraEsquerda", new Posicao(0, 0)));
        Save("QuadradoVerde.obj.gz", FactoryElemento.getElemento("QuadradoVerde", new Posicao(0, 0)));
        Save("QuadradoVerdeCoracao.obj.gz", FactoryElemento.getElemento("QuadradoVerdeCoracao", new Posicao(0, 0)));
        Save("QuadradoVerdeLosango.obj.gz", FactoryElemento.getElemento("QuadradoVerdeLosango", new Posicao(0, 0)));
        Save("QuadradoVerdeLosangoCoracao.obj.gz",
                FactoryElemento.getElemento("QuadradoVerdeLosangoCoracao", new Posicao(0, 0)));
        Save("QuadradoVermelho.obj.gz", FactoryElemento.getElemento("QuadradoVermelho", new Posicao(0, 0)));
        Save("QuadradoVermelhoLosango.obj.gz",
                FactoryElemento.getElemento("QuadradoVermelhoLosango", new Posicao(0, 0)));
        Save("RoboAmarelo.obj.gz", FactoryElemento.getElemento("RoboAmarelo", new Posicao(0, 0)));
        Save("RoboAzul.obj.gz", FactoryElemento.getElemento("RoboAzul", new Posicao(0, 0)));
        Save("RoboVerde.obj.gz", FactoryElemento.getElemento("RoboVerde", new Posicao(0, 0)));
        Save("RoboRosa.obj.gz", FactoryElemento.getElemento("RoboRosa", new Posicao(0, 0)));
    }

    private static void Save(String name, Elemento elem) {
        try {
            File fTemp = new File(new java.io.File(".").getCanonicalPath() + Consts.OBJETOPATH + name);
            // File fTemp = new File(Consts.SAVEPATH + "saveData.gz");
            if (fTemp.exists()) {
                fTemp.delete();
            }
            fTemp.createNewFile();

            FileOutputStream canoOut = new FileOutputStream(fTemp, true);
            GZIPOutputStream compactador = new GZIPOutputStream(canoOut);
            ObjectOutputStream serializador = new ObjectOutputStream(compactador);

            serializador.writeObject(elem);
            serializador.flush();

            serializador.close();
            compactador.close();
            canoOut.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel salvar o elemento");
            System.out.println("Tente novamente");
        }
    }
}
