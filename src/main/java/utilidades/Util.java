package utilidades;

import java.text.Normalizer;

public class Util {

    public static String removeCaracteresEspeciais(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static Double stringParaDouble(String textoNumerico){
        return Double.valueOf(textoNumerico.replaceAll(" ", "").replace(".", "").replaceAll(",", "."));
    }

}
