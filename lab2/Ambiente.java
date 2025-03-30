import java.util.Random;
import java.lang.reflect.Method;
import java.util.ArrayList;

class Ambiente{
  private int comprimentoX,comprimentoY, altura ;
  private char[][][] ambiente;
  private ArrayList<Robo> listaRobos;

  public Ambiente(int x, int y, int z){
    comprimentoX = x;
    comprimentoY = y;
    altura = z;
    ambiente = new char[x][y][z];
    listaRobos = new ArrayList<>();

    Random aleatorio = new Random();
    for (int i = 0; i<x; i++) 
      for (int j=0; j<y; j++)
        for (int k=0; k<z; k++)
            ambiente[i][j][k] = aleatorio.nextBoolean() ? 'X' : '*';
  }

  public void adicionarRobo(Robo r) {
    listaRobos.add(r);
    Class<?> classe = r.getClass(); // Obtém a classe
    boolean existe = false;
    for (Method metodo : classe.getDeclaredMethods()) {
      if (metodo.getName().equals("posicaoZ")) {
        existe = true;
        break;
      }
    }
    ambiente[r.posicaoX][r.posicaoY][(existe && r instanceof RoboAereo) ? ((RoboAereo) r).posicaoZ() : 0] = 'r';
  }
  public boolean dentroDosLimites(int x, int y, int z){
    if (x<0 || y<0 || z<0 || x>comprimentoX || y>comprimentoY || z>altura)
      return false;
    else 
      return true;
  }

  public boolean tem_obstaculo(int x, int y, int z){
    return ambiente[x][y][z] == 'X' ? true : false;
  }

  public boolean tem_robo(int x, int y, int z){
    return ambiente[x][y][z] == 'r' ? true : false;
  }

  public void eliminaObstaculo(int x, int y, int z) {
    if (dentroDosLimites(x,y,z))
      if (tem_robo(x, y, z))
        System.out.println("É proibibido matar outros robôs");
      else ambiente[x][y][z] = '*';
    else 
      System.out.println("Essa posiçao encontra-se fora dos limites!");
  }

  public void atualizar(Coordenada c, char s){
    ambiente[c.x()][c.y()][c.z()] = s;
  }
}