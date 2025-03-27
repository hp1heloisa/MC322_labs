import java.util.Scanner;
public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    TiposRobos tiposRobos = new TiposRobos();
    Robo robo;
    robo = tiposRobos.definir_robo();
    char movimento_robo = ' ';
    System.out.printf("Você agora está no mundo do robô %s!", robo.exibirNome());
    robo.explicar_movimentacao();
    
      
    

    
    /*while (movimento_robo != 'x'){
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
    }*/
  
    


  }
}
