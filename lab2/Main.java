import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Ambiente ambiente;
    Print print;
    System.out.println("Quantos robos você quer?");
    ambiente = new Ambiente(10, 10);
    RoboTerrestre robTer1 = new RoboTerrestre("Robo1", 0, 0, 2);
    
    robTer1.mover(0, 0, 4);


  }
}
