package utilidades;

import java.util.Arrays;
import java.util.Optional;

public enum NumeroMes {


    JAN("Janeiro", 1, "jan", "jan"),
    FEV("Fevereiro", 2, "fev", "feb"),
    MAR("Março", 3, "mar", "mar"),
    ABR("Abril", 4, "abr", "apr"),
    MAI("Maio", 5, "mai", "may"),
    JUN("Junho", 6, "jun", "jun"),
    JUL("Julho", 7, "jul", "jul"),
    AGO("Agosto", 8, "ago", "aug"),
    SET("Setembro", 9, "set", "sep"),
    OUT("Outubro", 10, "out", "oct"),
    NOV("Novembro", 11, "nov", "nov"),
    DEZ("Dezembro", 12, "dez", "dec");


    private String nome;
    private int numero;
    private String nomePt;
    private String nomeEn;

    NumeroMes(String nome, int numero, String nomePt, String nomeEn) {
        this.nome = nome;
        this.numero = numero;
        this.nomePt = nomePt;
        this.nomeEn = nomeEn;
    }

    public static Optional<NumeroMes> getPorNomePt(String name) {
        return Arrays.stream(values()).filter(numeroMes -> numeroMes.getNomePt().equalsIgnoreCase(name.trim())).findFirst();
    }

    public static Optional<NumeroMes> getPorNomeEn(String name) {
        return Arrays.stream(values()).filter(numeroMes -> numeroMes.getNomeEn().equalsIgnoreCase(name.trim())).findFirst();
    }



    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getNomePt() {
        return nomePt;
    }

    public String getNomeEn() {
        return nomeEn;
    }
}
