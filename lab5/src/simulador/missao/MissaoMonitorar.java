package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;

/**
 * O robô fica em uma coordenada fixa
 * e aciona sensores, registrando mudanças.
 */
public class MissaoMonitorar implements Missao {

    private final Coordenada centro;
    private final int ciclos;

    public MissaoMonitorar(Coordenada centro, int ciclos) {
        this.centro = centro;   // coordenada fixa
        this.ciclos = ciclos;   // quantas varreduras fazer
    }

    /**
     * CORREÇÃO: A assinatura do método foi alterada para corresponder à interface Missao,
     * recebendo o LogadorMissao como parâmetro.
     */
    @Override
    public void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        // CORREÇÃO: Lógica ajustada para mover o robô para o ponto central UMA VEZ.
        log.log("Iniciando movimentação para o ponto de monitoramento " + centro);
        try {
            ambiente.moverEntidade(robo, centro.getx(), centro.gety(), centro.getz(), robo);
            log.log("Robô posicionado em " + robo.get_Coordenada() + ". Iniciando varredura.");

            // Loop de varredura na posição fixa
            for (int i = 1; i <= ciclos && robo.getEstado() == EstadoRobo.ligado; i++) {
                try {
                    robo.acionarSensores(); // já escreve no console
                    log.log("Ciclo " + i + ": Sensores acionados com sucesso em " + robo.get_Coordenada());
                } catch (Exception e) {
                    log.log("Falha ao acionar sensores no ciclo " + i + ": " + e.getMessage());
                    break; // Interrompe a missão se os sensores falharem
                }
            }
        } catch (Exception e) {
            log.log("Falha crítica ao tentar se mover para o ponto de monitoramento: " + e.getMessage());
        }

        log.log("Monitoramento encerrado.");
    }
}
