package servicos;

import modelos.CalculosTransacoes;
import modelos.Transacao;
import modelos.Transacoes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculosServico {


    public static CalculosTransacoes realizaCalculos(Transacoes transacoes) {

        CalculosTransacoes calculos = new CalculosTransacoes();

        calculos.setRecebimentosTotais(transacoes.getRecebimentos().stream().mapToDouble(Transacao::getValor).sum());
        calculos.setGastosTotais(transacoes.getPagamentos().stream().mapToDouble(Transacao::getValor).sum());
        calculos.setMovimentacaoTotal(calculos.getRecebimentosTotais() + (calculos.getGastosTotais() * -1));

        calculos.setGastoPorCategoria(transacoes.getPagamentos().stream().collect(
                Collectors.groupingBy(Transacao::getCategoria, Collectors.summingDouble(Transacao::getValor))));


        Map.Entry<String, Double> maisGastouPorCategoria = calculos.getGastoPorCategoria().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)).entrySet().iterator().next();


        calculos.setGastoMaiorCategoria(maisGastouPorCategoria.getValue());
        calculos.setCategoriaMaisGastou(maisGastouPorCategoria.getKey());

        Map<String, Double> gastoMes = transacoes.getPagamentos().stream().collect(
                Collectors.groupingBy(Transacao::getMes, Collectors.summingDouble(Transacao::getValor)));

        Map.Entry<String, Double> maisGastouPorMes = gastoMes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)).entrySet().iterator().next();

        calculos.setMesMaisGastou(maisGastouPorMes.getKey());
        calculos.setGastoMaiorMes(maisGastouPorMes.getValue());


        return calculos;

    }


}
