class Robo {
  private String nome;
  private int posicaoX;
  private int posicaoY;
  /**Função construtora que define já o nome do robô, posx e posy */
  public Robo(String nome, int posicaoX, int posicaoY){
    this.nome = nome;
    this.posicaoX = posicaoX;
    this.posicaoY = posicaoY;
  }
  /**Método que move o robô */
  public void mover(int deltaX, int deltaY) {
    posicaoX = deltaX;
    posicaoY = deltaY;
  }
  /**Método get que exibe a posição do robô, em forma de coordenada, */
  public void exibirPosicao(){
    System.out.println("Posição: (" + posicaoX + ", " + posicaoY + ")");
  }
  /**Método get que exibe o nome do robô */
  public void exibirNome(){
    System.out.println("O nome do seu robo é: " + nome);
  }
  /**Método get que exibe apenas a pos x*/
  public int getposicaoX(){
    return posicaoX;
  }
  /**Método get que exibe apenas a pos y */
  public int getposicaoY(){
    return posicaoY;
  }
}
