package utilidades;

import java.util.Scanner;

public class Comunicador {

    private Scanner scan = new Scanner(System.in);

    public static void escreve(Object objeto) {
        System.out.println(objeto);
    }

    protected String entraString() {
        return scan.nextLine();
    }

}
