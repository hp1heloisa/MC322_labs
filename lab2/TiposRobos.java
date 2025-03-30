import java.util.Scanner;

public class TiposRobos{
    Scanner scanner = new Scanner(System.in);
    private static String[] listAereos = {"Robô Destruidor", "Robô Teletransportador"}; 
    private static String[] listTerrestre = {"Robô Limitado", "Robô Guindaste"};
    int robo_escolhido; 
    public TiposRobos(){
    }
    
    public Robo definir_robo(Ambiente ambiente) {
        System.out.println("Qual tipo de robô você quer criar?");
        System.out.println("1. terrestre");
        System.out.println("2. aéreo");
        int robo_escolhido = scanner.nextInt();
        this.robo_escolhido = robo_escolhido;
        if(robo_escolhido == 1)
            return this.terrestres(ambiente);
        return this.aereos(ambiente);
        
    }
    private RoboAereo aereos(Ambiente ambiente) {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listAereos.length; i++)
            System.out.println((i+1) + ". " + listAereos[i]);
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1)
            return new RoboDestruidor(ambiente);
        else
            return new RoboTeletransportador(ambiente);

    }
    private RoboTerrestre terrestres(Ambiente ambiente) {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listTerrestre.length; i++)
            System.out.println((i+1) + ". " + listTerrestre[i]);
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1)
            return new RoboLimitado(ambiente);
        else 
            return new RoboGuindaste(ambiente);
    }
}
