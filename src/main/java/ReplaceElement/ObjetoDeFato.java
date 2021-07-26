package ReplaceElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

import Auxiliar.Consts;
import Auxiliar.Posicao;
import Modelo.Elemento;

public class ObjetoDeFato implements Objeto {
    private Elemento elem;

    public ObjetoDeFato(String sNomeArquivo) {

        carregaObjetoDoDisco(sNomeArquivo);
    }

    private void carregaObjetoDoDisco(String sArquivoObjeto) {
        try {
            File fTemp = new File(new java.io.File(".").getCanonicalPath() + Consts.OBJETOPATH + sArquivoObjeto);

            FileInputStream canoIn = new FileInputStream(fTemp);
            GZIPInputStream descompactador = new GZIPInputStream(canoIn);
            ObjectInputStream deserializador = new ObjectInputStream(descompactador);

            elem = (Elemento) deserializador.readObject();

            deserializador.close();
            descompactador.close();
            canoIn.close();
        } catch (Exception e) {
            elem = null;
        }
    }

    @Override
    public Elemento exibeObjeto(Posicao p) {
        elem.setPosicao(p);
        return elem;
    }
}
