package simulador.robo;

import java.io.IOException;
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
    public void poder(Coordenada desejada, Coordenada atual) {
        System.out.println("Robô Explorador não consegue utilizar poderes");
    }

    @Override
    public void executarMissao(Ambiente a) {
        if (temMissao()) {
            try {
                System.out.println("Executando missão exploratória...");
                super.executarMissao(a);
            } catch (ColisaoException | IOException | ForadosLimitesException e) {
                System.out.println("Erro ao executar missão: " + e.getMessage());
            }
        }
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
