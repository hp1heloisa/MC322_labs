
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Ambiente ambiente = new Ambiente("arq.txt");
        
        TiposRobos tiposRobos = new TiposRobos();
        Robo robo;
        char estado = ' ';

        while (estado != 'x') {
            robo = tiposRobos.definir_robo(ambiente);
            ambiente.adicionarRobo(robo);
            System.out.printf("Você agora está no mundo do robô %s!", robo.getNome());
            robo.explicar_movimentacao();
            estado = robo.movimentacao();
        }
    }
}
