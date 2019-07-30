package modelos;

import dto.GetTransacoesDTO;

import java.util.ArrayList;
import java.util.List;

public class Transacoes {

    private List<Transacao> pagamentos;

    private List<Transacao> recebimentos;

    public static Transacoes construirDeGetTransacoes(GetTransacoesDTO transacoesDTO) {

        Transacoes transacoes = new Transacoes();
        transacoes.setPagamentos(new ArrayList<>());
        transacoes.setRecebimentos(new ArrayList<>());

        transacoesDTO.getPagamentos().forEach(transacao -> {
            transacoes.getPagamentos().add(Transacao.constroiDeTransacaoDTO(transacao));
        });

        transacoesDTO.getRecebimentos().forEach(transacao -> {
            transacoes.getRecebimentos().add(Transacao.constroiDeTransacaoDTO(transacao));
        });

        return transacoes;
    }

    public List<Transacao> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Transacao> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Transacao> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(List<Transacao> recebimentos) {
        this.recebimentos = recebimentos;
    }
}
