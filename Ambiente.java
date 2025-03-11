class Ambiente{
  private int largura;
  private int altura;

  public Ambiente(int altura, int largura){
    this.altura = altura;
    this.largura = largura;
  }
  public int dentroDosLimites(int x, int y){
    if(x <= largura && y <= altura){
      return 1;
    }
    return 0;
  }
}