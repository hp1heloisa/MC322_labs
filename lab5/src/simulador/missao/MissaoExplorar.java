package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;
import simulador.robo.RoboExplorador;

public class MissaoExplorar implements Missao {

    private final int passosMax;

    public MissaoExplorar(int passosMax) {
        this.passosMax = passosMax;
    }

    // CORREÇÃO: Implementa o método único da interface.
    @Override
    public void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        if (!(robo instanceof RoboExplorador)) {
            log.log("ERRO: O robô " + robo.getNome() + " não é um RoboExplorador e não pode executar a MissaoExplorar.");
            return;
        }

        RoboExplorador explorador = (RoboExplorador) robo;
        log.log("Iniciando exploração com " + explorador.getNome());

        for (int i = 0; i < passosMax && explorador.getEstado() == EstadoRobo.ligado; i++) {
            // A lógica de mover aleatoriamente precisa estar no robô ou aqui.
            // Para este exemplo, vamos simular a movimentação.
            try {
                // Tentativa de mover para uma direção aleatória
                int dx = (int) (Math.random() * 3) - 1; // -1, 0, or 1
                int dy = (int) (Math.random() * 3) - 1; // -1, 0, or 1
                if (dx == 0 && dy == 0) continue; // Não mover se for a mesma posição

                ambiente.moverEntidade(explorador, explorador.getPosicaoX() + dx, explorador.getPosicaoY() + dy, explorador.getposicaoZ(), explorador);
                log.log("Passo " + (i + 1) + ": Moveu para " + explorador.get_Coordenada());
            } catch (Exception e) {
                log.log("Passo " + (i + 1) + ": Tentativa de movimento falhou. Causa: " + e.getMessage());
            }
        }
        log.log("Exploração concluída.");
    }

    @Override
    public String getDescricao() {
        return "Explorar por " + passosMax + " passos.";
    }
}