package simulador;

import java.io.IOException;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.exceptions.ColisaoException;
import simulador.interfaces.Missao;
import simulador.missao.MissaoFactory;
import simulador.robo.Robo;
import simulador.robo.TiposRobos;

public class Main {
    public static void main(String[] args) throws IOException, ColisaoException {
        Ambiente ambiente = new Ambiente("ambiente.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simulador de Robôs Iniciado. Comandos: ROBO, MISSAO, EXECUTAR, SAIR.");

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine();
            if (linha.isEmpty()) continue;

            String[] infos = linha.split(" ");
            String comando = infos[0].toUpperCase();

            if ("SAIR".equalsIgnoreCase(comando)) break;

            switch (comando) {
                case "ROBO":
                    Robo novoRobo = TiposRobos.criarRobo(ambiente, infos); // Use a fábrica de robôs estática
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

                    if (roboAlvo != null) {
                        Missao novaMissao = MissaoFactory.criarMissao(infos);
                        roboAlvo.adicionarMissao(novaMissao);
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
                    if (roboParaExecutar != null) {
                        roboParaExecutar.executarMissao(ambiente);
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