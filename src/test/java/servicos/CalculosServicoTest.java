package servicos;

import modelos.CalculosTransacoes;
import modelos.Transacao;
import modelos.Transacoes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;


public class CalculosServicoTest {


    @InjectMocks
    @Spy
    private CalculosServico servico;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ordenaTransacoesTest() {
        Transacoes transacoes = buildTransacoesMock();

        List<Transacao> transacoesOrdenadas = servico.ordenaTransacoes(transacoes);

        assertThat(transacoesOrdenadas).isNotNull();
        assertThat(transacoesOrdenadas.size()).isEqualTo(5);
        assertThat(transacoesOrdenadas.get(0).getDescricao()).isEqualTo("Primeira Transacao");
        assertThat(transacoesOrdenadas.get(1).getDescricao()).isEqualTo("Segunda Transacao");
        assertThat(transacoesOrdenadas.get(2).getDescricao()).isEqualTo("Terceira Transacao");
        assertThat(transacoesOrdenadas.get(3).getDescricao()).isEqualTo("Quinta Transacao");
        assertThat(transacoesOrdenadas.get(4).getDescricao()).isEqualTo("Quarta Transacao");


    }

    @Test
    public void calculaGastoPorMesEMaiorGastoMes() {
        Transacoes transacoes = buildTransacoesMock();

        Map<String, Double> gastoPorMes = servico.calculaGastoPorMes(transacoes);

        assertThat(gastoPorMes.size()).isEqualTo(2);
        assertThat(gastoPorMes.get("Fevereiro")).isEqualTo(-30);
        assertThat(gastoPorMes.get("Março")).isEqualTo(-25);

        Map.Entry<String, Double> maiorGastoMes = servico.calculaMaiorGasto(gastoPorMes);


        assertThat(maiorGastoMes.getKey()).isEqualTo("Fevereiro");
        assertThat(maiorGastoMes.getValue()).isEqualTo(-30);

    }

    @Test
    public void calculaGastosPorCategoria() {
        Transacoes transacoes = buildTransacoesMock();

        Map<String, Double> gastoPorCategoria = servico.calculaGastosPorCategoria(transacoes);

        assertThat(gastoPorCategoria.size()).isEqualTo(1);
        assertThat(gastoPorCategoria.get("Lazer")).isEqualTo(-55);

    }

    @Test
    public void calculaSoma() {
        Transacoes transacoes = buildTransacoesMock();

        double somaPagamento = servico.calculaSoma(transacoes.getPagamentos());
        double somaRecebimento = servico.calculaSoma(transacoes.getRecebimentos());


        assertThat(somaPagamento).isEqualTo(-55);
        assertThat(somaRecebimento).isEqualTo(130);

    }

    @Test
    public void realizaCalculos() {
        Transacoes transacoes = buildTransacoesMock();

        CalculosTransacoes calculosTransacoes = servico.realizaCalculos(transacoes);

        List<Transacao> transacoesOrdenadas = calculosTransacoes.getTransacoesOrdenadas();

        assertThat(transacoesOrdenadas).isNotNull();
        assertThat(transacoesOrdenadas.size()).isEqualTo(5);
        assertThat(transacoesOrdenadas.get(0).getDescricao()).isEqualTo("Primeira Transacao");
        assertThat(transacoesOrdenadas.get(1).getDescricao()).isEqualTo("Segunda Transacao");
        assertThat(transacoesOrdenadas.get(2).getDescricao()).isEqualTo("Terceira Transacao");
        assertThat(transacoesOrdenadas.get(3).getDescricao()).isEqualTo("Quinta Transacao");
        assertThat(transacoesOrdenadas.get(4).getDescricao()).isEqualTo("Quarta Transacao");

        assertThat(calculosTransacoes.getMesMaisGastou()).isEqualTo("Fevereiro");
        assertThat(calculosTransacoes.getGastoMaiorMes()).isEqualTo(-30);

        Map<String, Double> gastoPorCategoria = calculosTransacoes.getGastoPorCategoria();

        assertThat(gastoPorCategoria.size()).isEqualTo(1);
        assertThat(gastoPorCategoria.get("Lazer")).isEqualTo(-55);

        assertThat(calculosTransacoes.getGastoMaiorCategoria()).isEqualTo(-55);
        assertThat(calculosTransacoes.getCategoriaMaisGastou()).isEqualTo("Lazer");

        assertThat(calculosTransacoes.getPagamentosTotais()).isEqualTo(-55);
        assertThat(calculosTransacoes.getRecebimentosTotais()).isEqualTo(130);

        assertThat(calculosTransacoes.getMovimentacaoTotal()).isEqualTo(185);

        Mockito.verify(servico, times(2)).calculaSoma(any());
        Mockito.verify(servico, times(1)).calculaGastosPorCategoria(any());
        Mockito.verify(servico, times(2)).calculaMaiorGasto(any());
        Mockito.verify(servico, times(1)).calculaGastoPorMes(any());
        Mockito.verify(servico, times(1)).ordenaTransacoes(any());

    }

    private Transacoes buildTransacoesMock() {

        Transacao transacaoRecebimentoMock = new Transacao();

        transacaoRecebimentoMock.setCategoria("");
        transacaoRecebimentoMock.setDia("10");
        transacaoRecebimentoMock.setMes("Janeiro");
        transacaoRecebimentoMock.setNumeroMes(1);
        transacaoRecebimentoMock.setDescricao("Primeira Transacao");
        transacaoRecebimentoMock.setMoeda("R$");
        transacaoRecebimentoMock.setValor(50.00);
        Transacao transacaoRecebimentoMock2 = new Transacao();

        transacaoRecebimentoMock2.setCategoria("");
        transacaoRecebimentoMock2.setDia("15");
        transacaoRecebimentoMock2.setMes("Janeiro");
        transacaoRecebimentoMock2.setNumeroMes(1);
        transacaoRecebimentoMock2.setDescricao("Segunda Transacao");
        transacaoRecebimentoMock2.setMoeda("R$");
        transacaoRecebimentoMock2.setValor(20.00);

        Transacao transacaoRecebimentoMock3 = new Transacao();

        transacaoRecebimentoMock3.setCategoria("Lazer");
        transacaoRecebimentoMock3.setDia("20");
        transacaoRecebimentoMock3.setMes("Fevereiro");
        transacaoRecebimentoMock3.setNumeroMes(2);
        transacaoRecebimentoMock3.setDescricao("Terceira Transacao");
        transacaoRecebimentoMock3.setMoeda("R$");
        transacaoRecebimentoMock3.setValor(-30.00);

        Transacao transacaoRecebimentoMock4 = new Transacao();

        transacaoRecebimentoMock4.setCategoria("");
        transacaoRecebimentoMock4.setDia("25");
        transacaoRecebimentoMock4.setMes("Março");
        transacaoRecebimentoMock4.setNumeroMes(2);
        transacaoRecebimentoMock4.setDescricao("Quarta Transacao");
        transacaoRecebimentoMock4.setMoeda("R$");
        transacaoRecebimentoMock4.setValor(60.00);

        Transacao transacaoRecebimentoMock5 = new Transacao();

        transacaoRecebimentoMock5.setCategoria("Lazer");
        transacaoRecebimentoMock5.setDia("20");
        transacaoRecebimentoMock5.setMes("Março");
        transacaoRecebimentoMock5.setNumeroMes(2);
        transacaoRecebimentoMock5.setDescricao("Quinta Transacao");
        transacaoRecebimentoMock5.setMoeda("R$");
        transacaoRecebimentoMock5.setValor(-25.0);

        Transacoes transacoes = new Transacoes();
        transacoes.setPagamentos(Arrays.asList(transacaoRecebimentoMock3, transacaoRecebimentoMock5));
        transacoes.setRecebimentos(Arrays.asList(transacaoRecebimentoMock2, transacaoRecebimentoMock, transacaoRecebimentoMock4));

        return transacoes;
    }

}
