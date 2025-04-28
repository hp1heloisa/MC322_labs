import java.util.Scanner;

class RoboGuindaste extends RoboTerrestre {

    public RoboGuindaste(Ambiente ambiente, Scanner scanner) {
        super(ambiente, scanner);
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
        Coordenada pos_robo = new Coordenada(coordenada.getx(), coordenada.gety(), 0), pos_obstaculo = new Coordenada(0, 0, 0);
        boolean pode_mover = false;
        switch (direcao) {
            case 'a':
                pos_obstaculo = new Coordenada(coordenada.getx() - 1, coordenada.gety(), 0);
                pode_mover = ambiente.trocarObstaculo(this, pos_obstaculo);
                break;
            case 'd':
                pos_obstaculo = new Coordenada(coordenada.getx() + 1, coordenada.gety(), 0);
                pode_mover = ambiente.trocarObstaculo(this, pos_obstaculo);
                break;
            case 'w':
                pos_obstaculo = new Coordenada(coordenada.getx(), coordenada.gety() + 1, 0);
                pode_mover = ambiente.trocarObstaculo(this, pos_obstaculo);
                break;
            case 's':
                pos_obstaculo = new Coordenada(coordenada.getx(), coordenada.gety() - 1, 0);
                pode_mover = ambiente.trocarObstaculo(this, pos_obstaculo);
                break;
            default:
                System.out.println("Direção inválida! Use w, s, a ou d");
                break;
        }
        if (pode_mover) {
            coordenada.setx(pos_obstaculo.getx());// vamos definir a posição do robô onde estava o obstáculo
            coordenada.sety(pos_obstaculo.gety());
        }

    }

    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) System.out.println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'k' && movimento_robo != 'c') {
                explicar_movimentacao();
            }
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
                    identificarObstaculo();
                    break;
                case 'q':
                    setVelocidade(velocidadeatual + 1);
                    break;
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                case 'c':
                    break;
                default:
                    System.out.println("Comando inválido! Use w, s, a, d, k ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                this.print_sensores();
            }
        }
        return movimento_robo;
    }
}
