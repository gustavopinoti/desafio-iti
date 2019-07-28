package modelos;

import java.util.Map;

public class CalculosTransacoes {

    private Double gastosTotais;
    private Double recebimentosTotais;
    private Double movimentacaoTotal;
    private Map<String, Double> gastoPorCategoria;
    private String mesMaisGastou;
    private Double gastoMaiorMes;
    private String categoriaMaisGastou;
    private Double gastoMaiorCategoria;

    public Double getGastosTotais() {
        return gastosTotais;
    }

    public void setGastosTotais(Double gastosTotais) {
        this.gastosTotais = gastosTotais;
    }

    public Double getRecebimentosTotais() {
        return recebimentosTotais;
    }

    public void setRecebimentosTotais(Double recebimentosTotais) {
        this.recebimentosTotais = recebimentosTotais;
    }

    public Double getMovimentacaoTotal() {
        return movimentacaoTotal;
    }

    public void setMovimentacaoTotal(Double movimentacaoTotal) {
        this.movimentacaoTotal = movimentacaoTotal;
    }

    public Map<String, Double> getGastoPorCategoria() {
        return gastoPorCategoria;
    }

    public void setGastoPorCategoria(Map<String, Double> gastoPorCategoria) {
        this.gastoPorCategoria = gastoPorCategoria;
    }

    public String getMesMaisGastou() {
        return mesMaisGastou;
    }

    public void setMesMaisGastou(String mesMaisGastou) {
        this.mesMaisGastou = mesMaisGastou;
    }

    public Double getGastoMaiorMes() {
        return gastoMaiorMes;
    }

    public void setGastoMaiorMes(Double gastoMaiorMes) {
        this.gastoMaiorMes = gastoMaiorMes;
    }

    public String getCategoriaMaisGastou() {
        return categoriaMaisGastou;
    }

    public void setCategoriaMaisGastou(String categoriaMaisGastou) {
        this.categoriaMaisGastou = categoriaMaisGastou;
    }

    public Double getGastoMaiorCategoria() {
        return gastoMaiorCategoria;
    }

    public void setGastoMaiorCategoria(Double gastoMaiorCategoria) {
        this.gastoMaiorCategoria = gastoMaiorCategoria;
    }
}
