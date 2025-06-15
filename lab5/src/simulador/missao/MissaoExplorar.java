package simulador.missao;

import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;
import simulador.robo.RoboExplorador;

public class MissaoExplorar implements Missao {

    private final int passosMax;
    private static final Random rnd = new Random();

    public MissaoExplorar(int passosMax) { 
        this.passosMax = passosMax; 
    }

    @Override
    public void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        for (int passo = 1; passo <= passosMax && robo.getEstado() == EstadoRobo.ligado; passo++) {

            int dx = rnd.nextInt(3) - 1;   // -1,0,1
            int dy = rnd.nextInt(3) - 1;

            Coordenada antes = robo.get_Coordenada();
            try {
                if (robo instanceof RoboExplorador explorador) 
                    explorador.tentarMover(ambiente);
                log.log(robo.getNome() + " -> " + robo.get_Coordenada());
            } catch (Exception e) {
                log.log("Falha de movimento (" + e.getMessage() + ")");
            }
        }
        log.log("Missão concluída.");
    }

    /* Interface antiga sem logger */
<<<<<<< HEAD
=======
    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            executar(robo, ambiente, log);
        } catch (Exception ignored) {}
    }
>>>>>>> parent of 14cbc99 (Falta debug com o arquivo)
    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            executar(robo, ambiente, log);
        } catch (Exception ignored) {}
    }
    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            executar(robo, ambiente, log);
        } catch (Exception ignored) {}
    }
}