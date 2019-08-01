package modelos;

import dto.TransacaoDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TransacaoTest {

    @Test
    public void constroiDeTransacaoDTOTest() {

        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setCategoria("Categoria Mock");
        transacaoDTO.setData("01/jan");
        transacaoDTO.setDescricao("Descrição Teste");
        transacaoDTO.setMoeda("R$");
        transacaoDTO.setValor("-85,20");

        Transacao transacao = Transacao.constroiDeTransacaoDTO(transacaoDTO);

        assertThat(transacao.getDescricao()).isEqualTo(transacaoDTO.getDescricao());
        assertThat(transacao.getCategoria()).isEqualTo(transacaoDTO.getCategoria().toUpperCase());
        assertThat(transacao.getMoeda()).isEqualTo(transacaoDTO.getMoeda());
        assertThat(transacao.getValor()).isEqualTo(-85.20);
        assertThat(transacao.getDia()).isEqualTo("01");
        assertThat(transacao.getMes()).isEqualTo("Janeiro");
        assertThat(transacao.getNumeroMes()).isEqualTo(1);

    }

    @Test
    public void constroiDeLinhaArquivoTest() {

        Transacao transacao = Transacao.constroiDeLinhaArquivo("02-may", "Teste Descricao", "80,88", "Categoria Mock");

        assertThat(transacao.getDescricao()).isEqualTo("Teste Descricao");
        assertThat(transacao.getCategoria()).isEqualTo("CATEGORIA MOCK");
        assertThat(transacao.getValor()).isEqualTo(80.88);
        assertThat(transacao.getDia()).isEqualTo("02");
        assertThat(transacao.getMes()).isEqualTo("Maio");
        assertThat(transacao.getNumeroMes()).isEqualTo(5);

    }
}
