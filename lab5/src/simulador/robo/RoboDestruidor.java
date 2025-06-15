package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.EnergiaInsuficienteException;
import simulador.exceptions.ForadosLimitesException;
import simulador.exceptions.RoboDesligadoException;

public class RoboDestruidor extends RoboAereo {

    /**
     * Função construtora do robô destruidor
     */
    public RoboDestruidor(Ambiente ambiente, Scanner scanner, EstadoRobo estado, String nome, Coordenada coordenada) {
        super(ambiente, nome, coordenada);
        try {
            super.setMensagemPadrao(FrasesRobos.getFraseDestruidor());
        } catch (RoboDesligadoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void poder(Coordenada alvo, Coordenada pos_atual) throws ForadosLimitesException, ColisaoException {
        destruir(alvo, pos_atual);
    }

    @Override
    public String getDescricao() {
        return "Olá! Eu sou um Robô Destruidor e eu sou uma subclasse do Robô Aéreo. Além de fazer tudo que ele faz, eu também posso destruir obstáculos que estiverem na minha frente, com exceção de outros robôs!";
    }

    @Override
    public void explicar_movimentacao() {
        super.explicar_movimentacao();
        System.out.println("k -> para destruir o obstáculo"); // adiciona os detalhes da movimentação especial do robô
        // destruidor
    }

    /**
     * Método especial do robô destruidor, o de destruir obstáculos
     */
    public void destruir(Coordenada alvo, Coordenada pos_atual) throws ForadosLimitesException, ColisaoException {
        Coordenada diferenca = new Coordenada(alvo.getx() - pos_atual.getx(), alvo.gety() - pos_atual.gety(), alvo.getz() - pos_atual.getz());
        if (diferenca.getx() != 0) {
            if (diferenca.getx() < 0) {
                ambiente.eliminaObstaculo(pos_atual.getx() - 1, pos_atual.gety(), pos_atual.getz());
                this.ambiente.moverEntidade(this, pos_atual.getx() - 1, pos_atual.gety(), pos_atual.getz(), this);
                return;
            } else {
                ambiente.eliminaObstaculo(pos_atual.getx() + 1, pos_atual.gety(), pos_atual.getz());
                this.ambiente.moverEntidade(this, pos_atual.getx() + 1, pos_atual.gety(), pos_atual.getz(), this);
                return;
            }
        }
        if (diferenca.gety() != 0) {
            if (diferenca.gety() < 0) {
                ambiente.eliminaObstaculo(pos_atual.getx(), pos_atual.gety() - 1, pos_atual.getz());
                this.ambiente.moverEntidade(this, pos_atual.getx(), pos_atual.gety() - 1, pos_atual.getz(), this);
                return;
            } else {
    
                ambiente.eliminaObstaculo(pos_atual.getx(), pos_atual.gety() + 1, pos_atual.getz());
                this.ambiente.moverEntidade(this, pos_atual.getx(), pos_atual.gety() + 1, pos_atual.getz(), this);
                return;

            }
        }
        if (diferenca.getz() != 0) {
            if (diferenca.getz() > 0) {
                ambiente.eliminaObstaculo(pos_atual.getx(), pos_atual.gety(), pos_atual.getz() + 1);
                this.ambiente.moverEntidade(this, pos_atual.getx(), pos_atual.gety(), pos_atual.getz()+ 1, this);
                return;
            } else {
                ambiente.eliminaObstaculo(pos_atual.getx(), pos_atual.gety(), pos_atual.getz() - 1);
                this.ambiente.moverEntidade(this, pos_atual.getx() - 1, pos_atual.gety(), pos_atual.getz() - 1, this);
                return;
            }
        }

    }

    @Override
    public char movimentacao() throws ColisaoException, ForadosLimitesException {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) {
                System.out
                        .println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            }
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'k' && movimento_robo != 'c'
                    && movimento_robo != '?' && movimento_robo != '!') {
                explicar_movimentacao();
            }
            try {
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
                        destruir(null, null);
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
            } catch (ColisaoException | ForadosLimitesException | RoboDesligadoException
                    | EnergiaInsuficienteException exception) {
                System.out.println(exception.getMessage());
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c'
                    && movimento_robo != '?' && movimento_robo != '!') {
                print_sensores();
            }
        }
        return movimento_robo;
    }

}
