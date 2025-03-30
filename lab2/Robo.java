import java.util.Scanner;
public abstract class Robo {
  protected String nome;
  protected String direcao;
  protected int posicaoX;
  protected int posicaoY;
  protected Scanner scanner = new Scanner(System.in);
  protected Ambiente ambiente;

  public Robo(Ambiente ambiente){
    this.ambiente = ambiente;
    System.out.printf("Diga qual é o nome do seu robô\n");
    nome = scanner.nextLine();
    System.out.printf("Em que direção %s se encontra? Norte, Leste, Sul ou Oeste? \n", nome);
    direcao = scanner.nextLine();
    System.out.printf("Aviso: Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = 0)\n");
    posicaoX = 0;
    posicaoY = 0;
  }
  public abstract void explicar_movimentacao();

  public abstract char movimentacao();

  protected void mover(int deltaX, int deltaY) {
    Coordenada c_0 = new Coordenada(posicaoX, posicaoY, 0);
    if (ambiente.dentroDosLimites(posicaoX + deltaX,  posicaoY + deltaY, 0) ){
      int passo = 1;
      if (deltaX < 0) {
          deltaX *= -1;
          passo = -1;
      } 
      while (deltaX > 0){
          if (ambiente.tem_obstaculo(posicaoX + passo,  posicaoY, 0)){
              System.out.printf("Há um obstáculo na posição: (%d,%d,%d)\n", posicaoX+passo, posicaoY, 0);
              return;
          }else {
            if (ambiente.tem_robo(posicaoX + passo,  posicaoY, 0)) {
              System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX+passo, posicaoY, 0);
              return;
            }
            else{
              posicaoX+=passo;
              deltaX--;
            }
          }
      }
      passo = 1;
      if (deltaY < 0) {
          deltaY *= -1;
          passo = -1;
      } 
      while (deltaY > 0){
          if (ambiente.tem_obstaculo(posicaoX, posicaoY+passo, 0)){
              System.out.printf("Há um obstáculo na posição: (%d,%d,%d)", posicaoX, posicaoY+passo, 0);
              return;
          }else {
            if (ambiente.tem_robo(posicaoX,  posicaoY + passo, 0)) {
              System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX, posicaoY+passo, 0);
              return;
            }
            else{
              posicaoY+=passo;
              deltaY--;
            }
          }
      }
  } else
    System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
  Coordenada c = new Coordenada(posicaoX, posicaoY, 0);
  atualizarAmbiente(c_0, c);
  }
  public void identificarObstaculo() {
    System.out.println("Obstáculos identificados em um raio de 5m: ");
    identificarArea(0);
  }
  public void exibirPosicao(){
    System.out.printf("%s se encontra na posição: (%d,%d)\n", nome, posicaoX, posicaoY);
  }
  public String exibirNome(){
    System.out.printf("O nome do seu robo é: %s\n", nome);
    return nome;
  }
  public int posicaoX(){
    return posicaoX;
  }
  public int posicaoY(){
    return posicaoY;
  }

  public void identificarArea(int alt) {
    for (int y=5; y>-5; y--){
        if (posicaoY+y < 0) continue;
        for (int x=-5; x<5; x++){
            if (posicaoX+x < 0) continue;
            if (ambiente.dentroDosLimites(posicaoX + x,  posicaoY + y, alt) && ambiente.tem_obstaculo(posicaoX + x,  posicaoY + y, alt)){
                System.out.printf("X");
            }else {
              if (x==0 && y==0)
                System.out.printf("R");
              else 
                if (ambiente.tem_robo(posicaoX + x,  posicaoY + y, alt))
                  System.out.printf("r");
                else System.out.printf("*");
                
            }
        }
        System.out.println("");
      }
  }

  public void atualizarAmbiente(Coordenada c_0 , Coordenada c) {
    ambiente.atualizar(c_0, '*');
    ambiente.atualizar(c, 'r');
  }
}
