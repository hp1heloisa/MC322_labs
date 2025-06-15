package simulador.robo;

import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.MoverParaOutroPonto;

// RoboExplorador agora é um AgenteInteligente que "assina o contrato" de que é CapazDeMoverAleatoriamente.
public class RoboExplorador extends AgenteInteligente implements MoverParaOutroPonto {

    public RoboExplorador(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
    }

    @Override
    public String getDescricao() {
        return "Robô Explorador";
    }

    @Override
    public void explicar_movimentacao() {

    }

    @Override
    public char movimentacao() throws ColisaoException, ForadosLimitesException {
        return 'x';
    }

    @Override
    public void poder() {
        System.out.println("Robô Explorador não consegue utilizar poderes");
    }

    /**
     * Executa pipeline, se existir; caso contrário usa definirMissao
     * individualmente.
     */
    @Override
    public boolean tentarMover(Ambiente ambiente) {
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) { // Tenta até 5 vezes
            int dx = rnd.nextInt(3) - 1;
            int dy = rnd.nextInt(3) - 1;
            if (dx == 0 && dy == 0) {
                continue;
            }
            try {
                super.mover(dx, dy); // Usa o método de mover da classe Robo
                return true;
            } catch (Exception ignored) {
            }
        }
        return false;
    }
}
