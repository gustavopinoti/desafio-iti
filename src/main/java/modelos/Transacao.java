package modelos;

import dto.TransacaoDTO;

public class Transacao {

    private String dia;
    private String mes;

    private String descricao;

    private String moeda;

    private Double valor;

    private String categoria;

    public static Transacao buildFromTransacaoDTO(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao();

        String[] data = transacaoDTO.getData().split("/");

        transacao.setDia(data[0]);
        transacao.setMes(data[1]);
        transacao.setCategoria(transacaoDTO.getCategoria() != null && transacaoDTO.getCategoria().isEmpty() ? "não possui" : transacaoDTO.getCategoria());
        transacao.setDescricao(transacaoDTO.getDescricao());
        transacao.setMoeda(transacaoDTO.getMoeda());
        transacao.setValor(Double.valueOf(transacaoDTO.getValor().replaceAll(" ", "").replaceAll(",", ".")));

        return transacao;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
