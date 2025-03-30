public class RoboTerrestre extends Robo{
    protected int velocidadeMax;
    protected int velocidadeatual;
    
    public RoboTerrestre(Ambiente ambiente){
        super(ambiente);   
    }
    @Override
    public void explicar_movimentacao(){
        System.out.println("Você pode movimentar seu robô usando os seguintes comandos: ");
        System.out.println("w -> ir para frente; s -> ir para trás");
        System.out.println("d -> ir para direita; a -> ir para a esqueda");
        System.out.println("p -> para scanear a área; q -> para aumentar velocidade");
        System.out.println("n -> criar um novo robô; x -> para sair");
    }
    protected void mover(int deltaX, int deltaY, int velocidade){ 
        if (velocidade <= velocidadeMax){
            super.mover(deltaX, deltaY);
      }

    }
    @Override
    public char movimentacao(){
        char movimento_robo = ' ';
        System.out.printf("Aperte uma tecla de movimentação para começar\n");
        while(movimento_robo != 'x' && movimento_robo != 'n'){
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo !='n')
                explicar_movimentacao();
            switch(movimento_robo) {
                case 'a':
                this.mover(-1, 0, this.velocidadeatual);
                break;   
            case 'd':
                this.mover(1, 0, this.velocidadeatual);
                break;
            case 'w':
                this.mover(0, 1, this.velocidadeatual);
                break;
            case 's':
                this.mover(0, -1, this.velocidadeatual);
                break;
            case 'q':
                this.velocidadeatual += 1;
                System.out.println("Velocidade aumentada para: " + this.velocidadeatual);
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
                System.out.println("Comando inválido! Use a, d, q ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo !='n')
                identificarArea(0);
        }
        return movimento_robo;
    }
}