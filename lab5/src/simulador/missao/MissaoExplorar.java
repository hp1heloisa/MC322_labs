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
<<<<<<< HEAD
<<<<<<< HEAD
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

=======
=======
>>>>>>> parent of 14cbc99 (Falta debug com o arquivo)
        for (int passo = 1; passo <= passosMax && robo.getEstado() == EstadoRobo.ligado; passo++) {

            int dx = rnd.nextInt(3) - 1;   // -1,0,1
            int dy = rnd.nextInt(3) - 1;

            Coordenada antes = robo.get_Coordenada();
            try {
                if (robo instanceof RoboExplorador explorador) 
                    explorador.tentarMoverAleatorio(ambiente);
                log.log(robo.getNome() + " -> " + robo.get_Coordenada());
            } catch (Exception e) {
                log.log("Falha de movimento (" + e.getMessage() + ")");
            }
        }
        log.log("Missão concluída.");
    }

    /* Interface antiga sem logger */
    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            executar(robo, ambiente, log);
        } catch (Exception ignored) {}
    }
    @Override
    public String getDescricao() {
        return "Explorar por " + passosMax + " passos.";
    }
}