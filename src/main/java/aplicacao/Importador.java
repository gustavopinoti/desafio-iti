package aplicacao;

import dto.GetTransacoesDTO;
import modelos.Transacao;
import modelos.Transacoes;
import servicos.HttpServico;
import utilidades.Comunicador;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Importador extends Comunicador {

    public void importaDados() {

        Transacoes transacoes = new Transacoes();
        transacoes.setPagamentos(new ArrayList<>());
        transacoes.setRecebimentos(new ArrayList<>());

        escreve("Importando Arquivo...");
        importaArquivo(transacoes);

        escreve("Importando dados de API...");
        importaDaAPI(transacoes);

        if(transacoes.getPagamentos().isEmpty() && transacoes.getRecebimentos().isEmpty()){
            escreve("Nenhuma transação disponível. Tente novamente mais tarde!");
            return;
        }

        Menu menu = new Menu(transacoes);
        menu.iniciaMenu();

    }

    private void importaArquivo(Transacoes transacoes) {

        try{
            InputStream fstream = Importador.class.getResourceAsStream("/dados.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream, StandardCharsets.UTF_8));
            String strLine, data, descricao, valor, categoria;

            Transacoes transacoesArquivo = new Transacoes();
            transacoesArquivo.setRecebimentos(new ArrayList<>());
            transacoesArquivo.setPagamentos(new ArrayList<>());

            Pattern p = Pattern.compile("^(\\d{2}-\\w{3})\\s{1,}(.*)\\s{1,}(-{0,1}(\\d{0,3}\\.{1}){0,}(\\d{0,3},{1}){0,1}\\d{1,2})\\s{0,}(.*)$");
            Matcher m;
            Transacao transacaoArquivo;

            while ((strLine = br.readLine()) != null)   {
                m = p.matcher(strLine);
                if(!m.matches()) {
                    continue;
                }

                data = m.group(1);
                descricao = m.group(2);
                valor = m.group(3);
                categoria = m.group(6);

                transacaoArquivo = Transacao.constroiDeLinhaArquivo(data, descricao, valor, categoria);

                if(transacaoArquivo.getValor() > 0)
                    transacoesArquivo.getRecebimentos().add(transacaoArquivo);
                else
                    transacoesArquivo.getPagamentos().add(transacaoArquivo);
            }

            transacoes.getRecebimentos().addAll(transacoesArquivo.getRecebimentos());
            transacoes.getPagamentos().addAll(transacoesArquivo.getPagamentos());
            fstream.close();
        } catch (Exception e) {
            escreve("Arquivo não encontrado ou dados não coesos. Prosseguindo sem eles!");
        }

    }

    private void importaDaAPI(Transacoes transacoes) {
        try {
            GetTransacoesDTO getTransacoes = HttpServico.getTransacoesAPI();

            Transacoes transacoesAPI = Transacoes.construirDeGetTransacoes(getTransacoes);

            transacoes.getRecebimentos().addAll(transacoesAPI.getRecebimentos());
            transacoes.getPagamentos().addAll(transacoesAPI.getPagamentos());

        } catch (Exception e) {
            escreve("Dados da API estão indisponíveis. Prosseguindo sem eles!");
        }
    }




}
