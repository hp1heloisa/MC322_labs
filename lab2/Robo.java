class Robo {
  private String nome;
  protected int posicaoX;
  protected int posicaoY;
  public Robo(String nome, int posicaoX, int posicaoY){
    this.nome = nome;
    this.posicaoX = posicaoX;
    this.posicaoY = posicaoY;
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
