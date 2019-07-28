package aplicacao;

import dto.GetTransacoesDTO;
import modelos.Transacoes;
import servicos.HttpServico;
import utilidades.Comunicador;

import java.util.ArrayList;

public class Importador implements Comunicador {

    public void importaDados() {

        Transacoes transacoes = new Transacoes();
        transacoes.setPagamentos(new ArrayList<>());
        transacoes.setRecebimentos(new ArrayList<>());

        escreve("Importando Arquivo...");
        importaArquivo(transacoes);

        escreve("Importando dados de API...");
        importaDaAPI(transacoes);

        Menu menu = new Menu(transacoes);
        menu.iniciaMenu();

    }

    private void importaArquivo(Transacoes transacoes) {

    }

    private void importaDaAPI(Transacoes transacoes) {
        try {
            GetTransacoesDTO getTransacoes = HttpServico.getTransacoesAPI();

            Transacoes transacoesAPI = Transacoes.buildFromGetTransacoes(getTransacoes);

            transacoes.getRecebimentos().addAll(transacoesAPI.getRecebimentos());
            transacoes.getPagamentos().addAll(transacoesAPI.getPagamentos());

        } catch (Exception e) {
            escreve("Dados da API estão indisponíveis. Prosseguindo sem eles!");
        }
    }




}
