package aplicacao;

import utilidades.Comunicador;

public class Menu extends Comunicador {

    private final static String menu = "------ Menu ------\n" +
            "1 - Histórico de movimentações\n" +
            "2 - Gasto por categoria\n" +
            "3 - Categoria com maior gasto\n" +
            "4 - Mês com maior gasto\n" +
            "5 - Dinheiro total gasto\n" +
            "6 - Dinheiro total recebido\n" +
            "7 - Saldo total de movimentações\n" +
            "0 - Sair\n" +
            "Escolha uma opção: ";

    public void iniciaMenu() {
        Integer opcaoMenu = imprimeObtemOpcaoMenu();

        switch (opcaoMenu){
            case 0 :
                escreve("Até breve!");
                return;
            case 1 :
                escreve("Historico");
                pressioneParaProsseguir();
                break;
            case 2:
                escreve("Gasto por categoria");
                pressioneParaProsseguir();
                break;
            case 3:
                escreve("Categoria com maior gasto");
                pressioneParaProsseguir();
                break;
            case 4:
                escreve("Mes com maior gasto");
                pressioneParaProsseguir();
                break;
            case 5:
                escreve("inheiro total gasto");
                pressioneParaProsseguir();
                break;
            case 6:
                escreve("Dinheiro total recebido");
                pressioneParaProsseguir();
                break;
            case 7:
                escreve("Saldo total de movimentações");
                pressioneParaProsseguir();
                break;
        }

        iniciaMenu();
    }

    private void pressioneParaProsseguir() {
        escreve("Pressione qualquer tecla...");
        entraString();
    }

    private Integer imprimeObtemOpcaoMenu() {
        escreve(menu);
        try {
            Integer opcao = Integer.parseInt(entraString());

            if(opcao < 0 || opcao > 7){
                return imprimeObtemOpcaoMenu();
            }

            return opcao;
        } catch (Exception e){
            return imprimeObtemOpcaoMenu();
        }
    }

}
