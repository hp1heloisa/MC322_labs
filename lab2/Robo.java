import java.util.Scanner;
class Robo {
  private String nome;
  protected int posicaoX;
  protected int posicaoY;
  protected Scanner scanner = new Scanner(System.in);
  public Robo(){
    System.out.printf("Diga qual é o nome do seu robô\n");
    nome = scanner.nextLine();
    System.out.printf("Aviso : Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = 0)\n");
    posicaoX = 0;
    posicaoY = 0;
  }
  public void mover(int deltaX, int deltaY) {
    posicaoX += deltaX;
    posicaoY += deltaY; 
    exibirPosicao();
  }
  public void exibirPosicao(){
    System.out.println("Posição: (" + posicaoX + ", " + posicaoY + ")");
  }
  public String exibirNome(){
    System.out.println("O nome do seu robo é: " + nome);
    return nome;
  }
  public int posicaoX(){
    return posicaoX;
  }
  public int posicaoY(){
    return posicaoY;
  }
}
