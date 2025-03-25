

class RoboDesenrolado extends RoboTerrestre{
    private String modo_desvio;
    public RoboDesenrolado(String nome, int posicaoX, int posicaoY, int velocidadeMax, String modo_desvio){
        super(nome, posicaoX, posicaoY, velocidadeMax);
        this.modo_desvio = modo_desvio;
    }
    public void mover(int deltaX, int deltaY, int velocidade){
        posicaoX = deltaX;
        posicaoY = deltaY;
        if(velocidade <= velocidadeMax)
            System.out.printf("Movimentação do robô desenrolado feita sem problemas.\n");
        else{
            System.out.printf("Velocidade extendida, mas movimentação do robô desenrolado feita com sucesso por meio de %s\n", modo_desvio);
            }
    }
}
    