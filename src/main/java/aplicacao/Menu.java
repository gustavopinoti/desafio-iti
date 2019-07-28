package aplicacao;

import modelos.CalculosTransacoes;
import modelos.Transacoes;
import servicos.CalculosServico;
import utilidades.Comunicador;

public class Menu implements Comunicador {

    private CalculosTransacoes calculosTransacoes;

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
        Integer opcaoMenu = imprimeEObtemOpcaoMenu();

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

                calculosTransacoes.getGastoPorCategoria().entrySet().iterator().forEachRemaining(entry -> {
                    escreveFormatado("Categoria: %s\t\tGasto: %s\n", entry.getKey(), entry.getValue());
                });
                pressioneParaProsseguir();
                break;
            case 3:
                escreveFormatado("Categoria com maior gasto: %s\tValor: %s\n", calculosTransacoes.getCategoriaMaisGastou(), calculosTransacoes.getGastoMaiorCategoria());
                pressioneParaProsseguir();
                break;
            case 4:
                escreveFormatado("Mes com maior gasto: %s\tValor: %s\n", calculosTransacoes.getMesMaisGastou(), calculosTransacoes.getGastoMaiorMes());
                pressioneParaProsseguir();
                break;
            case 5:
                escreveFormatado("A soma de todos os gastos é: %s\n", calculosTransacoes.getGastosTotais().toString());

                pressioneParaProsseguir();
                break;
            case 6:
                escreveFormatado("A soma de todos os recebimentos é: %s\n", calculosTransacoes.getRecebimentosTotais().toString());

                pressioneParaProsseguir();
                break;
            case 7:
                escreveFormatado("Saldo total movimentado é: %s\n", calculosTransacoes.getMovimentacaoTotal().toString());

                pressioneParaProsseguir();
                break;
        }

        iniciaMenu();
    }

    private void pressioneParaProsseguir() {
        escreve("Pressione qualquer tecla...");
        entraString();
    }

    private Integer imprimeEObtemOpcaoMenu() {
        escreve(menu);
        try {
            Integer opcao = Integer.parseInt(entraString());

            if(opcao < 0 || opcao > 7){
                return imprimeEObtemOpcaoMenu();
            }

            return opcao;
        } catch (Exception e){
            return imprimeEObtemOpcaoMenu();
        }
    }

    public Menu(Transacoes transacoes) {
        calculosTransacoes = CalculosServico.realizaCalculos(transacoes);
    }
}
