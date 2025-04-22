
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Ambiente ambiente = new Ambiente(30,40,100);
        // ambiente.salvar_o_ambiente("ambiente.txt");
        Ambiente ambiente = new Ambiente("ambiente.txt");
        
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
