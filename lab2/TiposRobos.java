import java.util.Scanner;

public class TiposRobos {
    Scanner scanner = new Scanner(System.in);
    private static String[] listAereos = {"Robô Topeira"}; 
    private static String[] listTerrestre = {"Robô Desenrolado", "Robô Transportador"};
    int robo_escolhido; 

    public TiposRobos () {
        System.out.println("Qual tipo de robô você quer criar?");
        System.out.println("1. terrestre");
        System.out.println("2. aéreo");
    }
    public RoboAereo aereos() {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listAereos.length; i++)
            System.out.println((i+1) + ". " + listAereos[i]);
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1)
            return new RoboTopeira();
        else
            return new RoboTopeira();

    }
    public RoboTerrestre terrestres() {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listTerrestre.length; i++)
            System.out.println((i+1) + ". " + listTerrestre[i]);
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1)
            return new RoboDesenrolado();
        else 
            return new RoboTransportador();
    }
}
