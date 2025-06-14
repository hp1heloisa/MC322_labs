package simulador;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.InterfaceRobo;
import simulador.missao.MissaoExplorar;
import simulador.robo.Robo;
import simulador.robo.RoboExplorador;
import simulador.robo.RoboPatrulheiro;
import simulador.robo.TiposRobos;

public class Main {

    public static void main(String[] args) throws IOException, ColisaoException {
        // Ambiente ambiente = new Ambiente(30,40,100);
        // ambiente.salvar_o_ambiente("ambiente.txt");
        Ambiente ambiente = new Ambiente("ambiente.txt");

        Scanner scanner = new Scanner(System.in);
        TiposRobos tiposRobos = new TiposRobos(scanner);
        InterfaceRobo robo;
        char estado = ' ';
        int indexRobo;

        while (estado != 'x') {
            System.out.println("Criar robô explorador autônomo? (s/n)");
            if ("s".equalsIgnoreCase(scanner.nextLine().trim())) {
                
                Random rnd = new Random();
                boolean escolheExplorador = rnd.nextBoolean();   // true => explorador, false => patrulheiro

                if (escolheExplorador) {
                    RoboExplorador explorador = new RoboExplorador(ambiente, 30, new Coordenada(8,3,0));
                    ambiente.adicionarEntidade(explorador);
                    explorador.executarMissao(ambiente);
                    System.out.println("Log salvo em missao_" + explorador.getNome() + ".txt");
                } else {
                    List<Coordenada> wp = List.of(new Coordenada(1,1,0), new Coordenada(5,1,0), new Coordenada(5,5,0));
                    RoboPatrulheiro pat = new RoboPatrulheiro(ambiente, wp, 10);
                    ambiente.adicionarEntidade(pat);
                    pat.executarMissao(ambiente);
                    System.out.println("Patrulheiro criado → log em missao_" + pat.getNome() + ".txt");
                }
            } else {
                switch (estado) {
                    case 'c':
                        System.out.println("Você gostaria de trocar de robô ou remover algum robo? (m para mudar e r para remover)");
                        estado = scanner.next().charAt(0);
                        break;
                    case 'm':
                        ambiente.getRobos();
                        System.out.println("Qual deles você escolhe?");
                        indexRobo = scanner.nextInt();
                        indexRobo--;
                        robo = ambiente.getRobo(indexRobo);
                        System.out.printf("Você agora está no mundo do robô %s!\n", robo.getNome());
                        robo.getPosicao();
                        robo.print_sensores();
                        try {
                            estado = robo.movimentacao();
                        } catch (ForadosLimitesException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    case 'r':
                        ambiente.getRobos();
                        System.out.println("Qual deles você gostaria de remover?");
                        indexRobo = scanner.nextInt();
                        Robo roboParaRemover = ambiente.get_tipo_Robo(indexRobo);
                        indexRobo--;
                        String removRob = ambiente.removerEntidade(roboParaRemover);
                        System.out.printf("O robô %s foi removido com sucesso!", removRob);
                        System.out.println("Para remover outro robô digite r, escolher um robô digite m e para criar um novo digite n:");
                        estado = scanner.next().charAt(0);
                        break;
                    default:
                        robo = tiposRobos.definir_robo(ambiente);
                        ambiente.adicionarEntidade(robo);
                        System.out.printf("Você agora está no mundo do robô %s!", robo.getNome());
                        robo.explicar_movimentacao();
                        try {
                            estado = robo.movimentacao();
                        } catch (ForadosLimitesException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                }
            }
        }

        scanner.close();
    }
}
