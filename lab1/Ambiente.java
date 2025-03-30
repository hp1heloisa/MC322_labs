class Ambiente{
  private int largura;
  private int altura;
  /**Função construtora que define a altura e largura do ambiente */
  public Ambiente(int altura, int largura){
    this.altura = altura;
    this.largura = largura;
  }
  /**Método que retorna um booleano para ver se um robô está dentro dos limites possíveis do campo */
  public boolean  dentroDosLimites(int x, int y){
    if(x <= largura && y <= altura){
      return true;
    }
    return false;
  }
}