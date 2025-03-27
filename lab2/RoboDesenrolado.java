import java.util.Scanner;
class RoboDesenrolado extends RoboTerrestre{
    Scanner scanner = new Scanner(System.in);
    private String modo_desvio;
    public RoboDesenrolado(){
        super();
        System.out.printf("A velocidade máxima do robô Desenrolado é de 100 km/h\n");
        velocidadeMax = 100;
        System.out.println("Diga a forma de como o seu robô irá desviar dos obstáculos. Seja criativo, por exemplo, pulando de estrelinha");
        String modo_desvio = scanner.nextLine();
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
    @Override
    public void explicar_movimentacao(){
        super.explicar_movimentacao();
        
    }
}
    