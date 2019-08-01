package modelos;

import dto.GetTransacoesDTO;
import dto.TransacaoDTO;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


public class TransacoesTest {

    @Test
    public void construirDeGetTransacoesTest() {

        TransacaoDTO transacaoDTOMock = new TransacaoDTO();

        transacaoDTOMock.setCategoria("");
        transacaoDTOMock.setData("10/jan");
        transacaoDTOMock.setDescricao("Primeira Transacao");
        transacaoDTOMock.setMoeda("R$");
        transacaoDTOMock.setValor("50,00");

        TransacaoDTO transacaoDTOMock2 = new TransacaoDTO();

        transacaoDTOMock2.setCategoria("Categoria Mock");
        transacaoDTOMock2.setData("15/fev");
        transacaoDTOMock2.setDescricao("Segunda Transacao");
        transacaoDTOMock2.setMoeda("R$");
        transacaoDTOMock2.setValor("-20,00");

        GetTransacoesDTO transacoesDTO = new GetTransacoesDTO();
        transacoesDTO.setPagamentos(Collections.singletonList(transacaoDTOMock));
        transacoesDTO.setRecebimentos(Collections.singletonList(transacaoDTOMock2));

        Transacoes transacoes = Transacoes.construirDeGetTransacoes(transacoesDTO);

        assertThat(transacoes).isNotNull();
        assertThat(transacoes.getPagamentos()).isNotEmpty();
        assertThat(transacoes.getPagamentos().size()).isEqualTo(1);
        assertThat(transacoes.getRecebimentos()).isNotEmpty();
        assertThat(transacoes.getRecebimentos().size()).isEqualTo(1);

    }

}
