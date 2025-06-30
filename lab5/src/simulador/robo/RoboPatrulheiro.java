package simulador.robo;

import java.io.IOException;
import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;


public class RoboPatrulheiro extends AgenteInteligente {


    public RoboPatrulheiro(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
    }

    @Override
    public void executarMissao(Ambiente a) {
        if (temMissao()) {
            try {
                System.out.println("Executando missão patrulhar...");
                super.executarMissao(a);
            } catch (ColisaoException | IOException | ForadosLimitesException e) {
                System.out.println("Erro ao executar missão: " + e.getMessage());
            }
        }
    }

    @Override
    public String getDescricao() {
        return "Robô Explorador";
    }

    @Override
    public void explicar_movimentacao() {
        System.out.println("Este robô executa missões de exploração e outras que lhe forem atribuídas.");
    }

    @Override
    public char movimentacao() {
        System.out.println("Use o comando EXECUTAR para iniciar a pipeline de missões.");
        return ' ';
    }

    @Override
    public void poder(Coordenada cord_desejada, Coordenada cord_atual) {
        System.out.println("Robô Explorador não consegue utilizar poderes");
    }

    public boolean tentarMoverAleatorio(Ambiente ambiente) {
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) { // Tenta até 5 vezes encontrar uma posição livre
            int dx = rnd.nextInt(3) - 1; // Gera -1, 0, ou 1
            int dy = rnd.nextInt(3) - 1;
            if (dx == 0 && dy == 0) {
                continue;
            }

            int nx = getPosicaoX() + dx;
            int ny = getPosicaoY() + dy;
            int nz = getposicaoZ();
            Coordenada nova = new Coordenada(nx, ny, nz);

            if (ambiente.estaOcupado(nova)) {
                continue;
            }

            try {
                ambiente.moverEntidade(this, nx, ny, nz, this);
                return true; // Movimento bem-sucedido
            } catch (Exception ignored) {
            }
        }
        return false; // Não conseguiu se mover
    }

}
