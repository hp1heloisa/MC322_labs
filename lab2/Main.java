public class Main {
  public static void main(String[] args){
    Ambiente ambiente = new Ambiente(30, 40, 100);
    TiposRobos tiposRobos = new TiposRobos();
    Robo robo;
    char estado = ' ';

    while (estado != 'x'){
      robo = tiposRobos.definir_robo(ambiente);
      ambiente.adicionarRobo(robo);
      System.out.printf("Você agora está no mundo do robô %s!", robo.exibirNome());
      robo.explicar_movimentacao();
      estado = robo.movimentacao();
    }
  }
}
