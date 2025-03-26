class RoboTerrestre extends Robo{
    protected int velocidadeMax;
    
    public RoboTerrestre(){
        super();    
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