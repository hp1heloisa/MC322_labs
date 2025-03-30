class RoboGuindaste extends RoboTerrestre{
    public RoboGuindaste(Ambiente ambiente){
        super(ambiente);
    }
    
    @Override
    public void explicar_movimentacao(){
        super.explicar_movimentacao();
        System.out.println("k -> trocar de posição com o obstáculo");
    }

    public void guindastiando() {
        System.out.println("Indique a direção do obstáculo: a, d, w ou s?");
        char direcao = scanner.next().charAt(0);
        Coordenada rc = new Coordenada(posicaoX, posicaoY, 0), ro = new Coordenada(0, 0, 0);
        boolean mov = false;
        switch (direcao) {
            case 'a':
                ro = new Coordenada(posicaoX-1, posicaoY, 0);
                mov = ambiente.trocarObstaculo(rc, ro);
                break;
            case 'd':
                ro = new Coordenada(posicaoX+1, posicaoY, 0);
                mov = ambiente.trocarObstaculo(rc, ro);
                break;
            case 'w':
                ro = new Coordenada(posicaoX, posicaoY+1, 0);
                mov = ambiente.trocarObstaculo(rc, ro);
                break;
            case 's':
                ro = new Coordenada(posicaoX, posicaoY-1, 0);
                mov = ambiente.trocarObstaculo(rc, ro);
                break;
            default:
                System.out.println("Direção inválida! Use w, s, a ou d");
                break;
        }
        if (mov) {
            setPosicaoX(ro.x());
            setPosicaoY(ro.y());
        }

    }

    @Override
    public char movimentacao(){
        char movimento_robo = ' ';
        while(movimento_robo != 'x' && movimento_robo != 'n'){
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo !='n' && movimento_robo !='k')
                explicar_movimentacao();
            switch(movimento_robo) {
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
                setVelocidade(velocidadeatual+1);
            break;
            case 'x':
                System.out.println("Encerrando movimentação...");
                break;
            case 'n':
                break;
            default:
                System.out.println("Comando inválido! Use w, s, a, d, k ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo !='n')
                identificarArea(0);
        }
        return movimento_robo;
    }
}