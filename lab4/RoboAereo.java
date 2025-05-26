import java.util.Scanner;

class RoboAereo extends Robo {

    protected SensorAltitude sensorAltitude = new SensorAltitude(5, super.ambiente);
    protected int altitudeMaxima;

    /**
     * Função construtura que herda do ambiente, definimos como padrão a
     * altitude como 0 e perguntamos qual será a altitude máxima
     */
    public RoboAereo(Ambiente ambiente, Scanner scanner) {
        super(ambiente, scanner, ambiente.getlistRobos());
        sensorTemperatura.setAltitude(5);
        sensorHumidade.setAltitude(5);
        System.out.println("Qual altidude máxima que o seu robô pode alcançar?");
        altitudeMaxima = scanner.nextInt();
        scanner.nextLine();
    }

    @Override
    public String getDescricao(){
        return "Olá! Eu sou o R0bô Aéreo e eu posso me mover nas coordenadas X, Y e Z!";
    }

    /**
     * Função que explica a movimentação do robô aéreo
     */
    @Override
    public void explicar_movimentacao() {
        System.out.println("Você pode movimentar seu robô usando os seguintes comandos: ");
        System.out.println("w -> ir para frente; s -> ir para trás");
        System.out.println("d -> ir para direita; a -> ir para a esqueda");
        System.out.println("u -> para subir; j -> para descer");
        System.out.println("p -> para scanear a área; n -> criar um novo robô");
        System.out.println("c -> remover ou trocar de robô; x -> para sair");
    }
   
    /**
     * Método que identifica os obstaculos em um raio de 5m e no caso do robô
     * aéreo, ele identifica os obstaculos no raio de 5 altitudes também
     */
    @Override
    public void print_sensores() {
        super.print_sensores();
        sensorAltitude.identificarArea(coordenada);
    }

    /**
     * Método que altera a altitude de um robô aéreo, podendo subir ou descer.
     */
    public void alterar_altitude(int deltah) {
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        Coordenada nova_c = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz()+ deltah);
        boolean dentroDosLimites = ambiente.dentroDosLimites(nova_c);
        if (dentroDosLimites) {
                if (!sensorPlano.tem_obstaculo(nova_c)) {
                    if (sensorPlano.tem_robo(nova_c)) {
                        System.out.printf("Há um robô na posição: %s\n", nova_c);
                        return;
                    }
                } else {
                    System.out.printf("Há um obstáculo do tipo %s na posição: %s\n",  sensorPlano.mostrar_obstaculo(nova_c), nova_c);
                }
            } else {
                System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
                return;
            }
    
        System.out.printf("Altitude atual: %d\n", nova_c.getz());
        atualizarAmbiente(c_0, nova_c);
    }

    /**
     * Implementação da movimentação do robô aéreo
     */
    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        System.out.printf("Aperte uma tecla de movimentação para começar\n");
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) System.out.println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                explicar_movimentacao();
            }
            switch (movimento_robo) {
                case 'a':
                    this.mover(-1, 0);
                    break;
                case 'd':
                    this.mover(1, 0);
                    break;
                case 'w':
                    this.mover(0, 1);
                    break;
                case 's':
                    this.mover(0, -1);
                    break;
                case 'u':
                    alterar_altitude(1);
                    break;
                case 'j':
                    alterar_altitude(-1);
                    break;
                case 'p':
                    print_sensores();
                    break;
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                case 'c':
                    break;
                default:
                    System.out.println("Comando inválido! Use w, s, a, d, u, j ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                this.print_sensores();
            }
        }
        return movimento_robo;
    }

}
 