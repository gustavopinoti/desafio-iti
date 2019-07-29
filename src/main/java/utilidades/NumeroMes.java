package utilidades;

import java.util.Arrays;
import java.util.Optional;

public enum NumeroMes {


    JAN("Janeiro", 1),
    FEV("Fevereiro", 2),
    MAR("Março", 3),
    ABR("Abril", 4),
    MAI("Maio", 5),
    JUN("Junho", 6),
    JUL("Julho", 7),
    AGO("Agosto", 8),
    SET("Setembro", 9),
    OUT("Outubro", 10),
    NOV("Novembro", 11),
    DEZ("Dezembro", 12);


    private String nome;
    private int numero;

    NumeroMes(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public static Optional<NumeroMes> getByName(String name) {
        Optional<NumeroMes> first = Arrays.stream(values()).filter(numeroMes -> numeroMes.name().equalsIgnoreCase(name.trim())).findFirst();
        return first;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
}
