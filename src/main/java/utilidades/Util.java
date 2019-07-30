package utilidades;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String removeCaracteresEspeciais(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static Double stringParaDouble(String textoNumerico){
        return Double.valueOf(textoNumerico.replaceAll(" ", "").replace(".", "").replaceAll(",", "."));
    }

}
