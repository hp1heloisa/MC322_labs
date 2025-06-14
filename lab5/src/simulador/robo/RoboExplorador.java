package simulador.robo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.MoverParaOutroPonto;

// RoboExplorador agora é um AgenteInteligente que "assina o contrato" de que é CapazDeMoverAleatoriamente.
public class RoboExplorador extends AgenteInteligente implements MoverParaOutroPonto {

    public RoboExplorador(Ambiente ambiente, Scanner scanner, ArrayList<Robo> listaRobos, EstadoRobo estado, String nome, Coordenada pos_inicial) {
        super(ambiente,scanner, listaRobos, estado, nome, pos_inicial);
    }

    @Override
    public String getDescricao() {
        return "Robô Explorador";
    }
    
    
    @Override 
    public void explicar_movimentacao() {

    }

    @Override 
    public char movimentacao(){ 
        return 'x'; 
    }

    /** Executa pipeline, se existir; caso contrário usa definirMissao individualmente. */

    @Override
    public boolean tentarMover(Ambiente ambiente) {
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) { // Tenta até 5 vezes
            int dx = rnd.nextInt(3) - 1;
            int dy = rnd.nextInt(3) - 1;
            if (dx == 0 && dy == 0) continue;
            try {
                super.mover(dx, dy); // Usa o método de mover da classe Robo
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }
}