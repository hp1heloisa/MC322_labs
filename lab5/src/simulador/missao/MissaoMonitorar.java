package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;

/**
 * O robô permanece em uma região e realiza varreduras acionando sensores,
 * registrando mudanças ao longo de múltiplos ciclos.
 */
public class MissaoMonitorar implements Missao {

    private final Coordenada centro;
    private final int ciclos;

    public MissaoMonitorar(Coordenada centro, int ciclos) {
        this.centro = centro;
        this.ciclos = ciclos;
    }

    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            log.log("Iniciando missão de monitoramento no ponto: " + centro);

            // Tenta posicionar o robô em uma célula próxima ao centro
            try {
                ambiente.moverEntidade(robo, centro.getx(), centro.gety(), centro.getz(), robo);
                log.log("Robô posicionado em " + centro);
            } catch (Exception e) {
                log.log("Falha ao mover robô para o ponto de monitoramento: " + e.getMessage());
                return;
            }

            // Executa os ciclos de varredura
            for (int i = 1; i <= ciclos && robo.getEstado() == EstadoRobo.ligado; i++) {
                try {
                    robo.acionarSensores();  // Sensores ativados
                    log.log("Ciclo " + i + ": sensores acionados com sucesso em " + robo.get_Coordenada());
                } catch (Exception e) {
                    log.log("Erro ao acionar sensores: " + e.getMessage());
                    break;
                }
            }

            log.log("Missão de monitoramento concluída.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar missão de monitoramento: " + e.getMessage());
        }
    }

    @Override
    public String getDescricao() {
        return "Missão de monitoramento em " + centro + " por " + ciclos + " ciclos.";
    }
}
