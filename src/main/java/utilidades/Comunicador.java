package utilidades;

import java.util.Scanner;

public class Comunicador {

    Scanner scan = new Scanner(System.in);

    protected void escreve(Object objeto) {
        System.out.println(objeto);
    }

    protected void escreveFormatado(String objeto, Object ...parametros) {
        System.out.printf(objeto, parametros);
    }

    protected String entraString() {
        return scan.nextLine();
    }

}
