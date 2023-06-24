package br.senai.sp.cotia.diaristas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import br.senai.sp.cotia.diaristas.model.Cliente;
import br.senai.sp.cotia.diaristas.model.Diarista;
import br.senai.sp.cotia.diaristas.model.Servico;

public class App {
    public static void main(String[] args) {
        String input;
        /*
         * Exibir a data e hora local utilizando as bibliotecas LocalDateTime e
         * DateTimeFormatter
        */

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraAtual = agora.format(formato);

        String nomeCliente = JOptionPane.showInputDialog(
                        "Olá!\n\n" +
                        "Seja bem-vindo(a) à DiraSoluções!\n\n" +
                        "Estamos muito contentes em tê-lo(a) como nosso(a) cliente. \nNossa equipe de diaristas está pronta para proporcionar um\nserviço de limpeza e organização impecável para a sua casa.\n\n" +
                        "Conte conosco para manter o seu ambiente sempre limpo e\nconfortável. Estamos aqui para atender às suas necessidades\ne superar suas expectativas.\n\n" +
                        "Data atual: " + dataHoraAtual +
                        "\n\nPor favor, informe-nos o seu nome para que possamos\nprosseguir:");
                        
        if (nomeCliente == null) {
            JOptionPane.showMessageDialog(null, "Programa encerrado.");
            System.exit(0);
        }

        Cliente cliente = new Cliente(nomeCliente);

        Diarista diarista1 = new Diarista("Ana");
        Diarista diarista2 = new Diarista("Luíza");
        Diarista diarista3 = new Diarista("Nancy");

        String[] diaristas = { diarista1.getNome(), diarista2.getNome(), diarista3.getNome() };

        String menu = """
                MENU PRINCIPAL

                1. Saldo do Cliente
                2. Saldo das Diaristas
                3. Depositar
                4. Solicitar Serviço
                5. Informações do Serviço
                6. Histórico de Serviços

                0. Sair
                """;
        int opcao = -1;
        do {
            try {
                input = JOptionPane.showInputDialog(menu);
                if (input == null) {
                    break; // sai do do
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida! Digite um número inteiro.");
                continue;
            }

            switch (opcao) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Saldo de " + cliente.getNome() + ": R$" + cliente.getSaldo());
                    break;

                case 2:
                    Object escolhaDiaristaSaldo = JOptionPane.showInputDialog(null, "Escolha a diarista para verificar o saldo:", "Selecionar Diarista", JOptionPane.PLAIN_MESSAGE, null, diaristas, diaristas[0]);

                    Diarista diaristaSaldo = null;
                    if (escolhaDiaristaSaldo.equals(diarista1.getNome())) {
                        diaristaSaldo = diarista1;
                    } else if (escolhaDiaristaSaldo.equals(diarista2.getNome())) {
                        diaristaSaldo = diarista2;
                    } else if (escolhaDiaristaSaldo.equals(diarista3.getNome())) {
                        diaristaSaldo = diarista3;
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }

                    if (diaristaSaldo != null) {
                        JOptionPane.showMessageDialog(null,
                                "Saldo da Diarista " + diaristaSaldo.getNome() + ": R$" + diaristaSaldo.getSaldo());
                    }
                    break;
                case 3:
                    try {
                        input = JOptionPane.showInputDialog("Digite o valor do depósito:");
                        if (input == null) {
                            continue;
                        }
                        double valorDeposito = Double.parseDouble(input);
                        cliente.depositar(valorDeposito);
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Digite um valor válido.");
                    }
                    break;

                case 4:
                    double horasServico = 0;
                    do {
                        try {
                            input = JOptionPane.showInputDialog("Digite a quantidade de horas do serviço (minímo de 1h e máximo 12h):");
                            if (input == null) {
                                break;
                            }
                            horasServico = Double.parseDouble(input);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Horas inválidas! Digite um número decimal.");
                            continue;
                        }
                        if (horasServico < 1 || horasServico > 12) {
                            JOptionPane.showMessageDialog(null,
                                    "Horas inválidas! Por favor, digite um valor entre 1 e 12.");
                        }
                    } while (horasServico < 1 || horasServico > 12);

                    Object escolhaDiarista = JOptionPane.showInputDialog(null, "Escolha a diarista:",
                            "Selecionar Diarista", JOptionPane.PLAIN_MESSAGE, null, diaristas, diaristas[0]);

                    Diarista diaristaSelecionada = null;
                    if (escolhaDiarista.equals(diarista1.getNome())) {
                        diaristaSelecionada = diarista1;
                    } else if (escolhaDiarista.equals(diarista2.getNome())) {
                        diaristaSelecionada = diarista2;
                    } else if (escolhaDiarista.equals(diarista3.getNome())) {
                        diaristaSelecionada = diarista3;
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }

                    if (diaristaSelecionada != null) {
                        cliente.solicitarServico(horasServico, diaristaSelecionada);
                    }
                    break;

                case 5:
                    String info = 
                    """
                        INFORMAÇÕES DO SERVIÇO:
                        
                        - O valor médio por hora de uma diarista é de R$ 18,75.
                        - O serviço pode ser solicitado por um mínimo de 1 hora e um máximo de 12 horas.
                        - O cliente precisa ter saldo suficiente em sua conta para solicitar um serviço.
                        - Após a solicitação do serviço, o valor correspondente é transferido do saldo do cliente para a diarista.
                    """
                    ;
                    JOptionPane.showMessageDialog(null, info);
                    break;
                case 6:
                    List<Servico> historicoServicos = cliente.getHistoricoServicos();
                    StringBuilder historico = new StringBuilder();
                    historico.append("Histórico de Serviços:\n");
                    for (Servico servico : historicoServicos) {
                        historico.append("- Diarista: ").append(servico.getDiarista().getNome()).append("\n");
                        historico.append("  Duração: ").append(servico.getDuracao()).append(" horas\n");
                        historico.append("  Custo: R$").append(servico.getCusto()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, historico.toString());
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}