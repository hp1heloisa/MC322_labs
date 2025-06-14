package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.missao.LogadorMissao;
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

    @Override public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            // Teletransporta ou move de uma vez o robô para o ponto de monitoramento
            
            for (int x=0; x<5; x++) 
                for (int y=0; y<5; y++) 
                    for (int z=0; z<5; z++) {
                        try {
                            ambiente.moverEntidade(robo, centro.getx()+x, centro.gety()+y, centro.getz()+z, robo);
                        } catch (Exception e) { 
                            log.log("Falha ao chegar no ponto: " + e.getMessage()); 
                        }
                        // Loop de varredura
                        for (int i = 1; i <= ciclos && robo.getEstado() == EstadoRobo.ligado; i++) {
                            try {
                                robo.acionarSensores();  // já escreve no console
                                log.log("Ciclo " + i + " sensores OK em " + robo.get_Coordenada());
                            } catch (Exception e) { 
                                log.log(e.getMessage()); 
                                break; 
                            }
                        }
                    }
            
            
            log.log("Monitoramento encerrado.");
        } catch (Exception ignored) {
            
        }
    }

    public String getDescricao() {
        return "Explorar por " + ciclos + " passos.";
    }

}

