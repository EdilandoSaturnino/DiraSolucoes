package br.senai.sp.cotia.diaristas.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Cliente {
    private String nome;
    private double saldo;
    private List<Servico> historicoServicos;

    public Cliente(String nome) {
        this.nome = nome;
        this.saldo = 0;
        this.historicoServicos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void solicitarServico(double horas, Diarista diarista) {
        double valorPorHora = 18.75; /* valor médio por hora */
        double valorTotal = horas * valorPorHora; 
        if (horas >= 1 && horas <= 12) {
            if (saldo >= valorTotal) { 
                saldo -= valorTotal;
                diarista.transferir(valorTotal);
                Servico servico = new Servico(diarista, horas, valorTotal);
                historicoServicos.add(servico);
                JOptionPane.showMessageDialog(null, "Serviço solicitado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Horas inválidas!");
        }
    }

    public List<Servico> getHistoricoServicos() {
        return historicoServicos;
    }
}
