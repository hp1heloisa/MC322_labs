package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.ColisaoException;
import simulador.ambiente.ForadosLimitesException;

public class RoboTeletransportador extends RoboAereo {

    public RoboTeletransportador(Ambiente ambiente, Scanner scanner, EstadoRobo estado) {
        super(ambiente, scanner, estado);
    }

    @Override
    public String getDescricao(){
        return "Olá! Eu sou um Robô Teletransportador e eu sou uma subclasse do Robô Aéreo. Além de fazer tudo o que ele faz, eu posso me teletransportar para diferentes alturas, muito maiores ou menores do que a que eu estou!";
    }

    @Override
    public void explicar_movimentacao(){
        super.explicar_movimentacao();
        System.out.printf("k - > para teletransportar de altitude\n");
    }
    public void alterar_altitude(int deltah) throws ColisaoException{
        super.alterar_altitude(deltah);
    }
    // @Override
    // public void subir(int deltah) {
    //     super.subir(deltah);
    // }

    // @Override
    // public void descer(int deltah) {
    //     super.descer(deltah);
    // }

    /**
     * Método especial do robô teletransportador, em que le pode ir para
     * qualquer altitude que ele quiser
     */
    private void teletransportar() throws ColisaoException {
        System.out.println("Indique a posição z para qual o robô irá se teletransportar?");
        int novaaltitude = scanner.nextInt();
        scanner.nextLine();
        int deltah = novaaltitude - coordenada.getz();
        if(deltah == 0) {
            System.out.printf("Mesma posição que o robô se encontrava anteriormente.\n");
        }
        else{
            alterar_altitude(deltah);
        }

    }

    @Override
    public char movimentacao() throws ColisaoException, ForadosLimitesException{
        char movimento_robo = ' ';
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
                case 'k':
                    teletransportar();
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
                    System.out.println("Comando inválido! Use w, s, a, d, u, j, k ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                this.print_sensores();
            }
        }
        return movimento_robo;
    }
}
