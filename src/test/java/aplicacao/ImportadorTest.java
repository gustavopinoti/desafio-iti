package aplicacao;

import dto.GetTransacoesDTO;
import dto.TransacaoDTO;
import modelos.Transacoes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import servicos.HttpServico;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;


public class ImportadorTest {

    @InjectMocks
    @Spy
    private Importador importador;

    @Mock
    private HttpServico httpServico;

    @Mock
    private Menu menu;

    @Captor
    private ArgumentCaptor<Transacoes> transacoesCaptor;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nenhumaTransacaoDisponivel() {

        Mockito.doNothing().when(importador).importaArquivo(any());
        Mockito.doNothing().when(importador).importaDaAPI(any());

        importador.importaDados();

        Mockito.verify(importador, times(1)).importaArquivo(any());
        Mockito.verify(importador, times(1)).importaDaAPI(any());
        Mockito.verify(importador, times(0)).iniciaMenu(any());
    }

    @Test
    public void iniciaComDadosDoArquivo() {
        Mockito.doNothing().when(importador).importaDaAPI(any());
        Mockito.doNothing().when(importador).iniciaMenu(any());

        importador.importaDados();

        Mockito.verify(importador, times(1)).importaArquivo(any());
        Mockito.verify(importador, times(1)).importaDaAPI(any());
        Mockito.verify(importador, times(1)).iniciaMenu(transacoesCaptor.capture());

        Transacoes transacoesCapturada = transacoesCaptor.getValue();

        assertThat(transacoesCapturada).isNotNull();
        assertThat(transacoesCapturada.getPagamentos()).isNotEmpty();
        assertThat(transacoesCapturada.getRecebimentos()).isNotEmpty();

    }

    @Test
    public void iniciaComDadosAPI() throws Exception {
        Mockito.doNothing().when(importador).importaArquivo(any());
        Mockito.doNothing().when(importador).iniciaMenu(any());

        GetTransacoesDTO getTransacoesDTOMock = new GetTransacoesDTO();
        TransacaoDTO mockRecebimento = new TransacaoDTO();
        mockRecebimento.setCategoria("Categoria Mock");
        mockRecebimento.setData("10/jan");
        mockRecebimento.setDescricao("Descricao Mock");
        mockRecebimento.setMoeda("R$");
        mockRecebimento.setValor("50,00");

        TransacaoDTO mockRecebimento2 = new TransacaoDTO();
        mockRecebimento2.setCategoria("Categoria 2 Mock");
        mockRecebimento2.setData("15/jun");
        mockRecebimento2.setDescricao("Descricao 2 Mock");
        mockRecebimento2.setMoeda("R$");
        mockRecebimento2.setValor("20,00");


        TransacaoDTO mockPagamento = new TransacaoDTO();
        mockPagamento.setCategoria("Categoria Pagamento Mock");
        mockPagamento.setData("10/jan");
        mockPagamento.setDescricao("Descricao Pagamento Mock");
        mockPagamento.setMoeda("R$");
        mockPagamento.setValor("- 30,00");

        getTransacoesDTOMock.setPagamentos(Collections.singletonList(mockPagamento));
        getTransacoesDTOMock.setRecebimentos(Arrays.asList(mockRecebimento, mockRecebimento2));


        Mockito.doReturn(getTransacoesDTOMock).when(httpServico).getTransacoesAPI();

        importador.importaDados();

        Mockito.verify(importador, times(1)).importaArquivo(any());
        Mockito.verify(importador, times(1)).importaDaAPI(any());
        Mockito.verify(importador, times(1)).iniciaMenu(transacoesCaptor.capture());

        Transacoes transacoesCapturada = transacoesCaptor.getValue();

        assertThat(transacoesCapturada).isNotNull();
        assertThat(transacoesCapturada.getPagamentos()).isNotEmpty();
        assertThat(transacoesCapturada.getPagamentos().size()).isEqualTo(1);
        assertThat(transacoesCapturada.getRecebimentos()).isNotEmpty();
        assertThat(transacoesCapturada.getRecebimentos().size()).isEqualTo(2);

    }

    @Test
    public void importaDaAPIErro() throws Exception {

        doThrow(new Exception()).when(httpServico).getTransacoesAPI();

        try {
            importador.importaDaAPI(new Transacoes());
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void importaArquivoErro() throws Exception {

        try {
            //irá estourar uma exception, porem apenas irá exibir uma mensagem
            importador.importaArquivo(new Transacoes());
        } catch (Exception e){
            fail();
        }

    }

}
