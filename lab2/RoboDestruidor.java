class RoboDestruidor extends RoboAereo {
    public RoboDestruidor(Ambiente ambiente){
        super(ambiente);
    }

    public void explicar_movimentacao(){
        super.explicar_movimentacao();
        System.out.println("k -> para destruir o obstáculo");
    }

    public void destruir() {
        System.out.println("Indique a direção do obstáculo: a, d, w, s, u ou j?");
        char direcao = scanner.next().charAt(0);
        switch (direcao) {
            case 'a':
                ambiente.eliminaObstaculo(posicaoX-1, posicaoY, altitude);
                break;
            case 'd':
                ambiente.eliminaObstaculo(posicaoX+1, posicaoY, altitude);
                break;
            case 'w':
                ambiente.eliminaObstaculo(posicaoX, posicaoY+1, altitude);
                break;
            case 's':
                ambiente.eliminaObstaculo(posicaoX, posicaoY-1, altitude);
                break;
            case 'u':
                ambiente.eliminaObstaculo(posicaoX, posicaoY, altitude+1);
                break;
            case 'j':
                ambiente.eliminaObstaculo(posicaoX, posicaoY, altitude-1);
                break;
            default:
                System.out.println("Direção inválida! Use w, s, a, d, u ou j");
                break;
        }

    }

    @Override
    public char movimentacao(){
        char movimento_robo = ' ';
        System.out.printf("Teste\n");
        while(movimento_robo != 'x' && movimento_robo != 'n'){
            movimento_robo = scanner.next().charAt(0);
            switch(movimento_robo) {
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
                subir();
                break;
            case 'j':
                descer();
                break;
            case 'k': 
                destruir();
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
            identificarArea(altitude);
        }
        return movimento_robo;
    }
    
}
