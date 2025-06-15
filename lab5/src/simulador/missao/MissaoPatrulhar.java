package simulador.missao;

import java.util.List;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;

/**
 * Patrulha waypoints em loop. Se o robô for desligado ou
 * atingir o número máximo de tentativas, a missão termina.
 */
public class MissaoPatrulhar implements Missao {

    private final List<Coordenada> waypoints;
    private final int maxTentativas;

    public MissaoPatrulhar(List<Coordenada> waypoints) {
        this(waypoints, 50);
    }

    public MissaoPatrulhar(List<Coordenada> waypoints, int maxTentativas) {
        this.waypoints = waypoints;
        this.maxTentativas = maxTentativas;
    }

    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            int idx = 0;
            while (robo.getEstado() == EstadoRobo.ligado && !waypoints.isEmpty()) {
                Coordenada alvo = waypoints.get(idx);
                if (!moverAte(robo, ambiente, alvo, log)) break; // falhou ou robô desligado
                idx = (idx + 1) % waypoints.size();
            }
            log.log("Patrulha encerrada.");
        } catch (Exception ignored) {}
    }

    private boolean moverAte(Robo robo, Ambiente ambiente, Coordenada alvo, LogadorMissao log) {
        // Se o destino é uma oficina, considera alcançado e pula
        if (ambiente.getElemento(alvo) == 'O') {
            log.log("Waypoint é uma oficina: " + alvo + " — pulando.");
            return true;
        }

        int tent = 0;
        while (robo.getEstado() == EstadoRobo.ligado && !robo.get_Coordenada().equals(alvo) && tent < maxTentativas) {

            tent++;
            int dx = Integer.compare(alvo.getx(), robo.getPosicaoX());
            int dy = Integer.compare(alvo.gety(), robo.getPosicaoY());

            int nx = robo.getPosicaoX() + dx;
            int ny = robo.getPosicaoY() + dy;
            int nz = robo.getposicaoZ();
            Coordenada prox = new Coordenada(nx, ny, nz);

            if (ambiente.getElemento(prox) != '*') {
                log.log("Célula ocupada em " + prox + " — pulando waypoint");
                return false;
            }

            try {
                ambiente.moverEntidade(robo, nx, ny, nz, robo);
                log.log("Patrulha -> " + robo.get_Coordenada());
            } catch (Exception e) {
                log.log("Falha: " + e.getMessage());
                if (robo.getEstado() == EstadoRobo.desligado) return false;
            }
        }
        return robo.getEstado() == EstadoRobo.ligado;
    }
    @Override
    public String getDescricao() {
        return "Explorar por " + maxTentativas + " passos.";
    }
}