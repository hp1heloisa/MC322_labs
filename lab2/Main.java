import java.util.Scanner;
public class Main {
  public static void main(String[] args){
    RoboTerrestre robo_terr = null;
    RoboAereo robo_ar = null;
    Robo robo = new Robo();
    Scanner scanner = new Scanner(System.in);
    TiposRobos tiposRobos = new TiposRobos();
    int tipoRobo = scanner.nextInt();
    char movimento_robo = ' ';
    
    if (tipoRobo == 1){
      robo = tiposRobos.terrestres();
      System.out.printf("Você agora está no mundo do robô %s!", robo_terr.exibirNome());
      System.out.println(" Digite a para ir para esquerda, d para ir para direita, n para criar um novo robô e x para sair");
    }

    else {
      robo_ar = tiposRobos.aereos();
      System.out.printf("Você agora está no mundo do robô %s!", robo_ar.exibirNome());
      System.out.println("Digite w para subir, s para descer, n para criar um novo robô e x para sair");
    }
    
    while (movimento_robo != 'x'){
      movimento_robo = scanner.next().charAt(0);
      if (movimento_robo == 'a' && tipoRobo == 1){ //TODO: pensar em um jeito melhor de fazer essa movimentação, frente tras, direita, esquerda, cima, baixo
        robo_terr.mover(-1, 0);
      }else if (movimento_robo == 'd' && tipoRobo == 1) {
        robo_terr.mover(1, 0);
      }else if (movimento_robo == 'w' && tipoRobo == 2) {
        robo_ar.subir();
      }else if (movimento_robo == 's' && tipoRobo == 2){
        robo_ar.descer();
      } else if (movimento_robo == 'n'){
        tiposRobos = new TiposRobos();
        tipoRobo = scanner.nextInt();
        if (tipoRobo == 1){
          robo_terr = tiposRobos.terrestres();
          robo_ar = null;
          System.out.printf("Você agora está no mundo do robô %s! Digite a ou d para movê-lo\n", robo_terr.exibirNome());
        }
        else {
          robo_ar = tiposRobos.aereos();
          robo_terr = null;
          System.out.printf("Você agora está no mundo do robô %s! Digite w ou s para movê-lo\n", robo_ar.exibirNome());
        }
      } else if (movimento_robo == 'x')
        break;
      else
        System.out.println("Movimento inválido para o seu robô");
    }
  
    


  }
}
