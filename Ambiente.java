class Ambiente{
  private int largura;
  private int altura;

  public Ambiente(int altura, int largura){
    this.altura = altura;
    this.largura = largura;
  }
  public boolean  dentroDosLimites(int x, int y){
    if(x <= largura && y <= altura){
      return true;
    }
    return false;
  }
}