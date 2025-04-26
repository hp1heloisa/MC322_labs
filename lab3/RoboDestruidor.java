
class RoboDestruidor extends RoboAereo {

    public RoboDestruidor(Ambiente ambiente) {
        super(ambiente);
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
    public char movimentacao() {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'k' && movimento_robo != 'c') {
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
                    subir(1);
                    break;
                case 'j':
                    descer(-1);
                    break;
                case 'k':
                    destruir();
                    break;
                case 'p':
                    //TODO: sensor
                    identificarObstaculo();
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
            //TODO: sensor
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                identificarArea(coordenada.getz());
            }
        }
        return movimento_robo;
    }

}
