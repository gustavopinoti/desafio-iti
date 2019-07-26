import aplicacao.Menu;
import utilidades.Comunicador;

public class ApplicationRunner extends Comunicador {

    public static void main(String[] args) {

        escreve("Importando dados de arquivo...");
        escreve("Importando dados de API...");

        Menu menu = new Menu();
        menu.iniciaMenu();
    }



}
