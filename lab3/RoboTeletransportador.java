
class RoboTeletransportador extends RoboAereo {

    public RoboTeletransportador(Ambiente ambiente) {
        super(ambiente);
    }

    @Override
    public void explicar_movimentacao() {
        super.explicar_movimentacao();
        System.out.printf("k - > para teletransportar de altitude\n");
    }

    @Override
    public void subir(int deltah) {
        super.subir(deltah);
    }

    @Override
    public void descer(int deltah) {
        super.descer(deltah);
    }

    /**
     * Método especial do robô teletransportador, em que le pode ir para
     * qualquer altitude que ele quiser
     */
    private void teletransportar() {
        System.out.println("Indique a posição z para qual o robô irá se teletransportar?");
        int novaaltitude = scanner.nextInt();
        int deltah = novaaltitude - altitude;
        if (deltah > 0) {
            this.subir(deltah);
        } else if (deltah < 0) {
            this.descer(deltah);
        } else {
            System.out.printf("Mesma posição que o robô se encontrava anteriormente.\n");
        }

    }

    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        while (movimento_robo != 'x' && movimento_robo != 'n') {
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n') {
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
                    teletransportar();
                    break;
                case 'p':
                    identificarObstaculo();
                    break;
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                default:
                    System.out.println("Comando inválido! Use w, s, a, d, u, j, k ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n') {
                identificarArea(altitude);
            }
        }
        return movimento_robo;
    }
}
