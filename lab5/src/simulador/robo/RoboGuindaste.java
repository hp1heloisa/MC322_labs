package simulador.robo;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.RoboDesligadoException;

public class RoboGuindaste extends RoboTerrestre {

    /**Função construtora do robô guindaste */
    public RoboGuindaste(Ambiente ambiente, Scanner scanner, EstadoRobo estado, String nome, Coordenada coordenada) {
        super(ambiente, scanner, estado, nome, coordenada);
        try {
            super.setMensagemPadrao(FrasesRobos.getFraseGuindaste());
        } catch (RoboDesligadoException e) {
           System.out.println(e.getMessage());
        }
    }
    
    @Override
    public String getDescricao(){
        return "Olá! Eu sou o Robô Guindaste, sou uma subclasse do Robô Terrestre! Além de fazer tudo que ele faz, eu posso mover obstáculos de lugar.";
    }

    @Override
    public void explicar_movimentacao() {
        super.explicar_movimentacao();
        System.out.println("k -> trocar de posição com o obstáculo");
    }

    /**
     * Método especial do robô guindaste, o poder de trocar a posição do próprio
     * com um obstáculo adjacente
     */
    public void guindastiando() {
        System.out.println("Indique a direção do obstáculo: a, d, w ou s?");
        char direcao = scanner.next().charAt(0);
        Coordenada pos_robo = new Coordenada(pos_atual.getx(), pos_atual.gety(), 0), pos_obstaculo = new Coordenada(0, 0, 0);
        boolean pode_mover = false;
        switch (direcao) {
            case 'a':
                pos_obstaculo = new Coordenada(pos_atual.getx() - 1, pos_atual.gety(), pos_atual.getz());
                pode_mover = ambiente.trocarObstaculo(this, ambiente.getEntidade(pos_obstaculo));
                break;
            case 'd':
                pos_obstaculo = new Coordenada(pos_atual.getx() + 1, pos_atual.gety(), pos_atual.getz());
                pode_mover = ambiente.trocarObstaculo(this, ambiente.getEntidade(pos_obstaculo));
                break;
            case 'w':
                pos_obstaculo = new Coordenada(pos_atual.getx(), pos_atual.gety() + 1, pos_atual.getz());
                pode_mover = ambiente.trocarObstaculo(this, ambiente.getEntidade(pos_obstaculo));
                break;
            case 's':
                pos_obstaculo = new Coordenada(pos_atual.getx(), pos_atual.gety() - 1, pos_atual.getz());
                pode_mover = ambiente.trocarObstaculo(this, ambiente.getEntidade(pos_obstaculo));
                break;
            default:
                System.out.println("Direção inválida! Use w, s, a ou d");
                break;
        }
        if (pode_mover) {
            pos_atual.setx(pos_obstaculo.getx());// vamos definir a posição do robô onde estava o obstáculo
            pos_atual.sety(pos_obstaculo.gety());
        }

    }
    @Override
    public void poder(Coordenada desejada, Coordenada atual){
        guindastiando();
    }

    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) System.out.println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'k' && movimento_robo != 'c' && movimento_robo != '?' && movimento_robo != '!') {
                explicar_movimentacao();
            }
            try {
                switch (movimento_robo) {
                case 'a':
                    super.mover(-1, 0, velocidadeatual);
                    break;
                case 'd':
                    super.mover(1, 0, velocidadeatual);
                    break;
                case 'w':
                    super.mover(0, 1, velocidadeatual);
                    break;
                case 's':
                    super.mover(0, -1, velocidadeatual);
                    break;
                case 'k':
                    guindastiando();
                    break;
                case 'p':
                    print_sensores();
                    break;
                case 'q':
                    setVelocidade(velocidadeatual + 1);
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
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                case 'c':
                    break;
                default:
                    System.out.println("Comando inválido! Use w, s, a, d, k, ?, ! ou x");
            }
        } catch ( RoboDesligadoException exception) {
                System.out.println(exception.getMessage());
        } 
        if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c'  && movimento_robo != '?' && movimento_robo != '!') {
            this.print_sensores();
        }
        }
        return movimento_robo;
    }
}
