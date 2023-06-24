package br.senai.sp.cotia.diaristas.model;

public class Diarista {
    private String nome;
    private double saldo;

    public Diarista(String nome) {
        this.nome = nome;
        this.saldo = 0;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void transferir(double valor) {
        saldo += valor;
    }
}
