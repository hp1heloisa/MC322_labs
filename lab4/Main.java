    import java.io.IOException;
    import java.util.Scanner;

    public class Main {

        public static void main(String[] args) throws IOException {
            // Ambiente ambiente = new Ambiente(30,40,100);
            // ambiente.salvar_o_ambiente("ambiente.txt");
            Ambiente ambiente = new Ambiente("ambiente.txt");

            Scanner scanner = new Scanner(System.in);
            TiposRobos tiposRobos = new TiposRobos(scanner);
            InterfaceRobo robo;
            char estado = ' ';
            int indexRobo;

                while (estado != 'x') {
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
                        estado = robo.movimentacao();
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
                        estado = robo.movimentacao();
                        break;
                }
            
            }

            scanner.close();
        }
    }
