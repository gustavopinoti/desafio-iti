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
                calculosTransacoes.getTransacoesOrdenadas().forEach(transacao -> {

                    escreveFormatado("Valor: %.2f\t\tDescricao: %s\t\tDia: %s\t\tMês: %s\t\tCategoria: %s\n", transacao.getValor(), transacao.getDescricao(), transacao.getDia(), transacao.getMes(), transacao.getCategoria());
                });
                pressioneParaProsseguir();
                break;
            case 2:
                escreve("Gasto por categoria");

                calculosTransacoes.getGastoPorCategoria().entrySet().iterator().forEachRemaining(entry -> {
                    escreveFormatado("Categoria: %s\t\tGasto: %.2f\n", entry.getKey(), entry.getValue());
                });
                pressioneParaProsseguir();
                break;
            case 3:
                escreveFormatado("Categoria com maior gasto: %s\tValor: %.2f\n", calculosTransacoes.getCategoriaMaisGastou(), calculosTransacoes.getGastoMaiorCategoria());
                pressioneParaProsseguir();
                break;
            case 4:
                escreveFormatado("Mês com maior gasto: %s\tValor: %.2f\n", calculosTransacoes.getMesMaisGastou(), calculosTransacoes.getGastoMaiorMes());
                pressioneParaProsseguir();
                break;
            case 5:
                escreveFormatado("A soma de todos os gastos é: %.2f\n", calculosTransacoes.getPagamentosTotais());

                pressioneParaProsseguir();
                break;
            case 6:
                escreveFormatado("A soma de todos os recebimentos é: %.2f\n", calculosTransacoes.getRecebimentosTotais());

                pressioneParaProsseguir();
                break;
            case 7:
                escreveFormatado("Saldo total movimentado é: %.2f\n", calculosTransacoes.getMovimentacaoTotal());

                pressioneParaProsseguir();
                break;
        }

        iniciaMenu();
    }

    private void pressioneParaProsseguir() {
        escreveFormatado("Pressione qualquer tecla...");
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
