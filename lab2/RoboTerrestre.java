public class RoboTerrestre extends Robo{
    protected int velocidadeMax;
    
    public RoboTerrestre(){
        super();    
    }
    @Override
    public void explicar_movimentacao(){
        System.out.println(" Digite a para ir para esquerda, d para ir para direita, n para criar um novo robô e x para sair");
    }
    public void mover(int deltaX, int deltaY, int velocidade){ {
        
        if (velocidade <= velocidadeMax){
            posicaoX = deltaX;
            posicaoY = deltaY;
            System.out.println("Movimento realizado");
        } else {
            System.out.println("Velocidade excedida");
        }
      }

}
}