import java.util.Scanner;
class Robo {
  private String nome;
  protected int posicaoX;
  protected int posicaoY;
  public Robo(){
    Scanner scanner = new Scanner(System.in);
    System.out.printf("Diga qual é o nome do seu robô\n");
    String nome_robo = scanner.nextLine();
    System.out.printf("Aviso : Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = 0)\n");
    nome = nome_robo;
    posicaoX = 0;
    posicaoY = 0;
  }
  public void mover(int deltaX, int deltaY) {
    posicaoX = deltaX;
    posicaoY = deltaY;
  }
  public void exibirPosicao(){
    System.out.println("Posição: (" + posicaoX + ", " + posicaoY + ")");
  }
  public void exibirNome(){
    System.out.println("O nome do seu robo é: " + nome);
  }
  public int posicaoX(){
    return posicaoX;
  }
  public int posicaoY(){
    return posicaoY;
  }
}
