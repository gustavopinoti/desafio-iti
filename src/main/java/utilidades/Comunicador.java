package utilidades;

import java.util.Scanner;

public interface Comunicador {

    Scanner scan = new Scanner(System.in);

    default void escreve(Object objeto) {
        System.out.println(objeto);
    }

    default void escreveFormatado(String objeto, Object ...parametros) {
        System.out.printf(objeto, parametros);
    }

    default String entraString() {
        return scan.nextLine();
    }

}
