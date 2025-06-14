package simulador.robo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;

/**
 * Representa um robô do tipo Explorador.
 * Herda toda a lógica de pipeline e execução de AgenteInteligente.
 */
public class RoboPatrulheiro extends AgenteInteligente {

    // 1. O campo 'pipeline' foi REMOVIDO. Ele agora é herdado de AgenteInteligente.
    
    // 2. O construtor é simplificado. Ele apenas cria o robô base.
    // O construtor que criava uma pipeline fixa foi REMOVIDO.
    public RoboPatrulheiro(Ambiente ambiente, Scanner scanner, ArrayList<Robo> listaRobos, EstadoRobo estado, String nome, Coordenada pos_inicial) {
        super(ambiente, scanner, listaRobos, estado, nome, pos_inicial);
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

    // 3. O método 'executarMissao' foi REMOVIDO.
    // O robô agora usará o método 'executarMissao' da sua classe pai, AgenteInteligente,
    // que já contém a lógica correta de percorrer a pipeline.

    /**
     * Método específico deste tipo de robô.
     */
    public boolean tentarMoverAleatorio(Ambiente ambiente) {
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) { // Tenta até 5 vezes encontrar uma posição livre
            int dx = rnd.nextInt(3) - 1; // Gera -1, 0, ou 1
            int dy = rnd.nextInt(3) - 1;
            if (dx == 0 && dy == 0) continue;

            int nx = getPosicaoX() + dx;
            int ny = getPosicaoY() + dy;
            int nz = getposicaoZ();
            Coordenada nova = new Coordenada(nx, ny, nz);

            if (ambiente.estaOcupado(nova)) continue;

            try {
                ambiente.moverEntidade(this, nx, ny, nz, this);
                return true; // Movimento bem-sucedido
            } catch (Exception ignored) {}
        }
        return false; // Não conseguiu se mover
    }

}