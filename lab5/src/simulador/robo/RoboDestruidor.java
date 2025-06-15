package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.EnergiaInsuficienteException;
import simulador.exceptions.ForadosLimitesException;
import simulador.exceptions.RoboDesligadoException;

public class RoboDestruidor extends RoboAereo {

    /**Função construtora do robô destruidor */
    public RoboDestruidor(Ambiente ambiente, Scanner scanner, EstadoRobo estado, String nome, Coordenada coordenada) {
        super(ambiente, nome, coordenada);
        try {
            super.setMensagemPadrao(FrasesRobos.getFraseDestruidor());
        } catch (RoboDesligadoException e) {
           System.out.println(e.getMessage());
        }
    }
    @Override
    public void poder(){
        destruir();
    }

    @Override
    public String getDescricao(){
        return "Olá! Eu sou um Robô Destruidor e eu sou uma subclasse do Robô Aéreo. Além de fazer tudo que ele faz, eu também posso destruir obstáculos que estiverem na minha frente, com exceção de outros robôs!";
    }

    @Override
    public void explicar_movimentacao() {
        super.explicar_movimentacao();
        System.out.println("k -> para destruir o obstáculo"); //adiciona os detalhes da movimentação especial do robô destruidor
    }

    /**
     * Método especial do robô destruidor, o de destruir obstáculos
     */
    public void destruir() {
        System.out.println("Indique a direção do obstáculo: a, d, w, s, u ou j?");
        char direcao = scanner.next().charAt(0);
        switch (direcao) {
            case 'a':
                ambiente.eliminaObstaculo(coordenada.getx() - 1, coordenada.gety(), coordenada.getz());
                break;
            case 'd':
                ambiente.eliminaObstaculo(coordenada.getx() + 1, coordenada.gety(), coordenada.getz());
                break;
            case 'w':
                ambiente.eliminaObstaculo(coordenada.getx(), coordenada.gety() + 1, coordenada.getz());
                break;
            case 's':
                ambiente.eliminaObstaculo(coordenada.getx(), coordenada.gety() - 1, coordenada.getz());
                break;
            case 'u':
                ambiente.eliminaObstaculo(coordenada.getx(), coordenada.gety(), coordenada.getz() + 1);
                break;
            case 'j':
                ambiente.eliminaObstaculo(coordenada.getx(), coordenada.gety(), coordenada.getz() - 1);
                break;
            default:
                System.out.println("Direção inválida! Use w, s, a, d, u ou j");
                break;
        }

    }

    @Override
    public char movimentacao() throws ColisaoException, ForadosLimitesException {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) System.out.println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'k' && movimento_robo != 'c' && movimento_robo != '?' && movimento_robo != '!') {
                explicar_movimentacao();
            }
            try { 
                switch (movimento_robo){
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
                        destruir();
                        break;
                    case '?': 
                        receberMensagensDoAmbiente();
                        break;
                    case '!': 
                        System.out.println("Para qual robô você gostaria de enviar uma mensagem? Digite o index dele:");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("O que gostaria de dizer para ele?");
                        String msg = scanner.nextLine();  
                        enviarMensagem(ambiente.getRobo(index), msg);
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
                        System.out.println("Comando inválido! Use w, s, a, d, u, j, k, ?, ! ou x");
                }
            } catch (ColisaoException | ForadosLimitesException | RoboDesligadoException | EnergiaInsuficienteException exception) {
                System.out.println(exception.getMessage());
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c'  && movimento_robo != '?' && movimento_robo != '!') {
                print_sensores();
            }
        }
        return movimento_robo;
    }

}