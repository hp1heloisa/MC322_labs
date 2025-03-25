public class TiposRobos {
    private static String[] listAereos = {}; 
    private static String[] listTerrestre = {"Robô Desenrolado", "Robô Topeira", "Robô Transportador"}; 

    public TiposRobos () {
        System.out.println("Qual tipo de robô você quer criar?");
        System.out.println("1. terrestre");
        System.out.println("2. aéreo");
    }
    public void aereos() {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listAereos.length; i++)
            System.out.println((i+1) + ". " + listAereos[i]);
    }
    public void terrestres() {
        System.out.println("Digite o número do robô escolhido:");
        for (int i=0; i < listTerrestre.length; i++)
            System.out.println((i+1) + ". " + listTerrestre[i]);
    }
}
