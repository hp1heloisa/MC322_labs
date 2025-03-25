class RoboTerrestre extends Robo{
    protected int velocidadeMax;
    
    public RoboTerrestre(String nome, int posicaoX, int posicaoY, int velocidadeMax){
        super();   
        this.velocidadeMax = velocidadeMax; 
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