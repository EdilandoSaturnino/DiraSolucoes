package br.senai.sp.cotia.diaristas.model;

public class Servico {

    private Diarista diarista;
    private double duracao;
    private double custo;

    public Servico(Diarista diarista, double duracao, double custo) {
        this.diarista = diarista;
        this.duracao = duracao;
        this.custo = custo;
    }

    public Diarista getDiarista() {
        return diarista;
    }

    public double getDuracao() {
        return duracao;
    }

    public double getCusto() {
        return custo;
    }
}