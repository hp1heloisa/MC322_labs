public class RoboDesenrolado extends RoboTerrestre{
    private String modo_desvio;
    public RoboDesenrolado(){
        super();
        System.out.printf("A velocidade máxima do robô Desenrolado é de 100 km/h\n");
        velocidadeMax = 100;
        System.out.println("Diga a forma de como o seu robô irá desviar dos obstáculos. Seja criativo, por exemplo, pulando de estrelinha");
        String modo_desvio = scanner.nextLine();
        this.modo_desvio = modo_desvio;
    }
    @Override
    protected void mover(int deltaX, int deltaY, int velocidade){
        super.mover(deltaX, deltaY, velocidade);
    }
    @Override
    public void explicar_movimentacao(){
        super.explicar_movimentacao();
    }   
    @Override
    public void movimentacao(){
        super.movimentacao();
    }
}
    