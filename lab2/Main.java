import java.util.Scanner;
public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    TiposRobos tiposRobos = new TiposRobos();
    int tipoRobo = scanner.nextInt();
    RoboTerrestre robo_terr;
    RoboAereo robo_ar;
    
    if (tipoRobo == 1)
      tiposRobos.terrestres();
    else 
      tiposRobos.aereos();
    int robo_escolhido = scanner.nextInt();
    
  
    


  }
}
