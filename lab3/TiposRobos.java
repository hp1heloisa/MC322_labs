
import java.util.Scanner;

/**
 * Função construtora de TiposRobos
 */
public class TiposRobos {

    Scanner scanner = new Scanner(System.in);
    private static String[] listAereos = {"Robô Destruidor", "Robô Teletransportador"};
    private static String[] listTerrestre = {"Robô Limitado", "Robô Guindaste"};
    int robo_escolhido;

    /**
     * Método responsável por retornar qual é a classe do nosso robô, se é aéreo
     * ou se é terrestre
     */
    public Robo definir_robo(Ambiente ambiente) {
        System.out.println("Qual tipo de robô você quer criar?");
        System.out.println("1. terrestre");
        System.out.println("2. aéreo");
        int robo_escolhido = scanner.nextInt();
        this.robo_escolhido = robo_escolhido;
        if (robo_escolhido == 1) {
            return this.terrestres(ambiente);
        }
        return this.aereos(ambiente);

    }

    /**
     * Método responsável por selecionar qual é o tipo de robô aéreo selecionado
     */
    private RoboAereo aereos(Ambiente ambiente) {
        System.out.println("Digite o número do robô escolhido:");
        for (int i = 0; i < listAereos.length; i++) {
            System.out.println((i + 1) + ". " + listAereos[i]);
        }
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1) {
            RoboAereo robo = new RoboDestruidor(ambiente); 
            robo.setTipo("RoboDestruidor");
            return robo;
        }else {
            RoboAereo robo = new RoboTeletransportador(ambiente);
            robo.setTipo("RoboTeletransportador");
            return robo;
        }

    }

    /**
     * Método responsável por selecionar qual é o tipo de robô terrestre selecionado
     */
    private RoboTerrestre terrestres(Ambiente ambiente) {
        System.out.println("Digite o número do robô escolhido:");
        for (int i = 0; i < listTerrestre.length; i++) {
            System.out.println((i + 1) + ". " + listTerrestre[i]);
        }
        robo_escolhido = scanner.nextInt();
        if (robo_escolhido == 1) {
            RoboTerrestre robo = new RoboLimitado(ambiente); 
            robo.setTipo("Robô Limitado");
            return robo;
        }else {
            RoboTerrestre robo = new RoboGuindaste(ambiente);
            robo.setTipo("Robo Guindaste");
            return robo;
        }
    }
}
