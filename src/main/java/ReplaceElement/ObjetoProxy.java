package ReplaceElement;

import Auxiliar.Posicao;
import Modelo.Elemento;

public class ObjetoProxy implements Objeto {
    private String sArquivoObjeto;
    private ObjetoDeFato oObjetoDeFato;

    public ObjetoProxy(String sNomeArquivo) {
        sArquivoObjeto = sNomeArquivo;
    }

    @Override
    public Elemento exibeObjeto(Posicao p) {
        if (oObjetoDeFato == null) {
            oObjetoDeFato = new ObjetoDeFato(sArquivoObjeto);
        }
        Elemento elem = oObjetoDeFato.exibeObjeto(p);
        return elem;
    }

}
