package simulador.robo;

import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;


public class RoboPatrulheiro extends AgenteInteligente {


    public RoboPatrulheiro(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
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
    public void poder() {
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
