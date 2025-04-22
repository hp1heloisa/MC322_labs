
class RoboAereo extends Robo {

    protected int altitudeMaxima;

    /**
     * Função construtura que herda do ambiente, definimos como padrão a
     * altitude como 0 e perguntamos qual será a altitude máxima
     */
    public RoboAereo(Ambiente ambiente) {
        super(ambiente);
        System.out.println("Qual altidude máxima que o seu robô pode alcançar?");
        altitudeMaxima = scanner.nextInt();
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
        System.out.println("p -> para scanear a área");
        System.out.println("n -> criar um novo robô; x -> para sair");
    }


    
   
    /**
     * Método que identifica os obstaculos em um raio de 5m e no caso do robô
     * aéreo, ele identifica os obstaculos no raio de 5 altitudes também
     */
    @Override

    public void identificarObstaculo() {
        for (int z = -5; z < 5; z++) {
            if (coordenada.getz() + z < 0) {
                continue;
            }
            System.out.printf("Mapa dos obstáculos encontrados em um raio de 5m na altitude %d\n", coordenada.getz() + z);
            identificarArea(coordenada.getz() + z);
        }
    }


    /**
     * Método que sube a altitude de um robô aéreo
     */
    public void subir(int deltah) {
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        boolean dentroDosLimites = ambiente.dentroDosLimites(coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah);
        if (dentroDosLimites) {
            if (coordenada.getz() + deltah <= altitudeMaxima) {
                if (!ambiente.tem_obstaculo(coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah)) {
                    if (ambiente.tem_robo(coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah);
                        return;
                    } else {
                        coordenada.setz(coordenada.getz() + deltah);
                    }
                } else {
                    System.out.println("Há um obtáculo nessa posição!");
                }
            } else {
                System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
            }
        }

        Coordenada c = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        System.out.printf("Altitude atual: %d\n", coordenada.getz());
        atualizarAmbiente(c_0, c);
    }

    /**
     * Método que faz o movimento de diminuição de altitude do robô
     */
    public void descer(int deltah) {
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        if (coordenada.getz() + deltah >= 0) {
            if (!ambiente.tem_obstaculo(coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah)) {
                if (ambiente.tem_robo(coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah)) {
                    System.out.printf("Há um robô na posição: (%d,%d,%d)\n", coordenada.getx(), coordenada.gety(), coordenada.getz() + deltah);
                    return;
                } else {
                    coordenada.setz(deltah + coordenada.getz());
                }
            } else {
                System.out.println("Há um obtáculo nessa posição!");
            }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        }
        Coordenada c = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        System.out.printf("Altitude atual: %d\n", coordenada.getz());
        atualizarAmbiente(c_0, c);
    }

    /**
     * Implementação da movimentação do robô aéreo
     */
    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        System.out.printf("Aperte uma tecla de movimentação para começar\n");
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
                case 'p':
                    identificarObstaculo();
                    break;
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                default:
                    System.out.println("Comando inválido! Use w, s, a, d, u, j ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n') {
                identificarArea(coordenada.getz());
            }
        }
        return movimento_robo;
    }

}
 