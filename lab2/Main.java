import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Ambiente ambiente;
    Print print;
    System.out.println("Quantos robos você quer?");
    ambiente = new Ambiente(10, 10);
    RoboTerrestre robTer1 = new RoboTerrestre("Robo1", 0, 0, 2);
    RoboDesenrolado Test = new RoboDesenrolado("teste", 0, 0, 3, "pulo");
    RoboTransportador oi = new RoboTransportador("oi", 0, 0, 3, "Rural");
    Test.mover(2,2,5);
    robTer1.mover(0, 0, 4);

    RoboAereo robAr1 = new RoboAereo("Robo Aereo 1", 0,0,3, 20);
    robAr1.mover(5,4);
    robAr1.subir(10);
    robAr1.descer(30);

    oi.mover(0, 0, 4, "Rural");

  }
}
