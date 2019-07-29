package servicos;

import modelos.CalculosTransacoes;
import modelos.Transacao;
import modelos.Transacoes;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculosServico {


    public static CalculosTransacoes realizaCalculos(Transacoes transacoes) {

        CalculosTransacoes calculos = new CalculosTransacoes();

        calculos.setRecebimentosTotais(calculaSoma(transacoes.getRecebimentos()));

        calculos.setPagamentosTotais(calculaSoma(transacoes.getPagamentos()));

        calculos.setMovimentacaoTotal(calculos.getRecebimentosTotais() + (calculos.getPagamentosTotais() * -1));

        calculos.setGastoPorCategoria(calculaGastosPorCategoria(transacoes));

        Map.Entry<String, Double> maisGastouPorCategoria = calculaMaiorGasto(calculos.getGastoPorCategoria());

        calculos.setGastoMaiorCategoria(maisGastouPorCategoria.getValue());
        calculos.setCategoriaMaisGastou(maisGastouPorCategoria.getKey());

        Map<String, Double> gastoMes = calculaGastoPorMes(transacoes);

        Map.Entry<String, Double> maisGastouPorMes = calculaMaiorGasto(gastoMes);

        calculos.setMesMaisGastou(maisGastouPorMes.getKey());
        calculos.setGastoMaiorMes(maisGastouPorMes.getValue());

        calculos.setTransacoesOrdenadas(ordenaTransacoes(transacoes));

        return calculos;

    }

    private static List<Transacao> ordenaTransacoes(Transacoes transacoes) {
        List<Transacao> transacoesTotais = transacoes.getPagamentos();
        transacoesTotais.addAll(transacoes.getRecebimentos());

        transacoesTotais.sort(Comparator.comparing(Transacao::getNumeroMes).thenComparing(Transacao::getDia));
        return transacoesTotais;
    }

    private static Map<String, Double> calculaGastoPorMes(Transacoes transacoes) {
        return transacoes.getPagamentos().stream().collect(
                Collectors.groupingBy(Transacao::getMes, Collectors.summingDouble(Transacao::getValor)));
    }

    private static Map.Entry<String, Double> calculaMaiorGasto(Map<String, Double> gastoPorCategoria) {
        return gastoPorCategoria.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)).entrySet().iterator().next();
    }

    private static Map<String, Double> calculaGastosPorCategoria(Transacoes transacoes) {
        return transacoes.getPagamentos().stream().collect(
                Collectors.groupingBy(Transacao::getCategoria, Collectors.summingDouble(Transacao::getValor)));
    }

    private static double calculaSoma(List<Transacao> transacoes) {
        return transacoes.stream().mapToDouble(Transacao::getValor).sum();
    }


}
