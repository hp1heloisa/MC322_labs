public class RoboTerrestre extends Robo{
    protected int velocidadeMax;
    protected int velocidadeatual;
    
    public RoboTerrestre(){
        super();   
    }
    @Override
    public void explicar_movimentacao(){
        System.out.println("Digite a para ir para esquerda, d para ir para direita, n para criar um novo robô, x para sair e q para aumentar velocidade");
    }
    protected void mover(int deltaX, int deltaY, int velocidade){ 
        
        if (velocidade <= velocidadeMax){
            posicaoX = deltaX;
            posicaoY = deltaY;
      }


}
    @Override
    public void movimentacao(){
        char movimento_robo = ' ';
        System.out.printf("Teste\n");
        while(movimento_robo != 'x'){
            movimento_robo = scanner.next().charAt(0);
            switch(movimento_robo) {
            case 'a':
                this.mover(-1, 0, this.velocidadeatual);
                break;    /*Falta adicionar o metodo que eu criei no ambiente que verifica o obstaculo e melhorar essa parte */
            case 'd':
                this.mover(1, 0, this.velocidadeatual);
                break;
            case 'q':
                this.velocidadeatual += 1;
                System.out.println("Velocidade aumentada para: " + this.velocidadeatual);
                break;
            case 'x':
                System.out.println("Encerrando movimentação...");
                break;
            default:
                System.out.println("Comando inválido! Use a, d, q ou x");
        }
    }
}
}