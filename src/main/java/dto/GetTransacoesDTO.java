package dto;

import java.util.List;

public class GetTransacoesDTO {

    private List<TransacaoDTO> pagamentos;

    private List<TransacaoDTO> recebimentos;

    public List<TransacaoDTO> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<TransacaoDTO> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<TransacaoDTO> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(List<TransacaoDTO> recebimentos) {
        this.recebimentos = recebimentos;
    }
}
