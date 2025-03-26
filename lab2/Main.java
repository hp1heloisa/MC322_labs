import java.util.Scanner;
public class Main {
  public static void main(String[] args){
    RoboTerrestre robo_terr = new RoboTerrestre();
    RoboAereo robo_ar = new RoboAereo();
    
    Scanner scanner = new Scanner(System.in);
    TiposRobos tiposRobos = new TiposRobos();
    int tipoRobo = scanner.nextInt();
    
    if (tipoRobo == 1){
      robo_terr = tiposRobos.terrestres();
      System.out.println(robo_terr.posicaoX());
    }

    else {
      robo_ar= tiposRobos.aereos();
      System.out.println(robo_ar.posicaoX());
      robo_ar.descer();
    }
    
    


  }
}
