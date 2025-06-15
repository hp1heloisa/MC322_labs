package simulador;

import java.io.IOException;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;
import simulador.missao.CriarMissao;
import simulador.robo.AgenteInteligente; // Importa a classe AgenteInteligente
import simulador.robo.Robo;
import simulador.robo.TiposRobos;

public class Main {

    public static void main(String[] args) throws IOException, ColisaoException, ForadosLimitesException {
        Ambiente ambiente = new Ambiente("ambiente.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(new java.io.File("input.txt"));
            System.out.println("INFO: Lendo comandos do arquivo 'input.txt'.");
        } catch (java.io.FileNotFoundException e) {
            System.err.println("ERRO: 'input.txt' não encontrado.");
            return; 
        }

        System.out.println("Simulador de Robôs Iniciado. Comandos: ROBO, MISSAO, EXECUTAR, SAIR.");

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine();
            if (linha.isEmpty()) {
                continue;
            }

            String[] infos = linha.split(" ");
            String comando = infos[0].toUpperCase();

            if ("SAIR".equalsIgnoreCase(comando)) {
                break;
            }

            switch (comando) {
                case "ROBO":
                    // A chamada agora está correta, usando o método estático da fábrica.
                    Robo novoRobo = TiposRobos.criarRobo(ambiente, infos);
                    if (novoRobo != null) {
                        ambiente.adicionarEntidade(novoRobo);
                        System.out.println("INFO: Robô '" + novoRobo.getNome() + "' criado.");
                    }
                    break;

                case "MISSAO":
                    if (infos.length < 3) {
                        System.err.println("ERRO: Comando MISSAO incompleto. Use: MISSAO NOME_ROBO TIPO_MISSAO [PARAM...]");
                        continue;
                    }
                    String nomeRoboAlvo = infos[1];
                    Robo roboAlvo = ambiente.getRoboPorNome(nomeRoboAlvo);

                    if (roboAlvo instanceof AgenteInteligente) { // Verifica se o robô pode ter missões
                        AgenteInteligente agenteAlvo = (AgenteInteligente) roboAlvo; // Faz o cast
                        Missao novaMissao = CriarMissao.criarMissao(infos);
                        if (novaMissao != null) {
                            agenteAlvo.adicionarMissao(novaMissao); 
                        }
                    } else if (roboAlvo != null) {
                        System.err.println("ERRO: Robô '" + nomeRoboAlvo + "' não pode receber missões.");
                    } else {
                        System.err.println("ERRO: Robô '" + nomeRoboAlvo + "' não encontrado.");
                    }
                    break;

                case "EXECUTAR":
                    if (infos.length < 2) {
                        System.err.println("ERRO: Comando EXECUTAR incompleto. Use: EXECUTAR NOME_ROBO");
                        continue;
                    }
                    String nomeRoboExec = infos[1];
                    Robo roboParaExecutar = ambiente.getRoboPorNome(nomeRoboExec);
                    if (roboParaExecutar instanceof AgenteInteligente) { // Verifica se o robô pode executar missões
                        AgenteInteligente agenteParaExecutar = (AgenteInteligente) roboParaExecutar; // Faz o cast
                        agenteParaExecutar.executarMissao(ambiente); // Chama o método do AgenteInteligente
                    } else if (roboParaExecutar != null) {
                        System.err.println("ERRO: Robô '" + nomeRoboExec + "' não pode executar missões.");
                    } else {
                        System.err.println("ERRO: Robô '" + nomeRoboExec + "' não encontrado.");
                    }
                    break;

                default:
                    System.err.println("ERRO: Comando '" + comando + "' desconhecido.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Simulador encerrado.");
    }
}
