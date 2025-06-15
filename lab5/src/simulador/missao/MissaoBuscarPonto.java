package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.missao.LogadorMissao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;

/**
 * Move o robô até um ponto e termina.
 */
public class MissaoBuscarPonto implements Missao {

    private final Coordenada destino;
    private final int passosMax;

    public MissaoBuscarPonto(Coordenada destino, int passosMax) {
        this.destino = destino;
        this.passosMax = passosMax;
    }

    // CORREÇÃO: Implementa o método único da interface.
    @Override
    public void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        int passo = 0;
        while (!robo.get_Coordenada().equals(destino) && robo.getEstado() == EstadoRobo.ligado && passo < passosMax) {
            passo++;

            int dx = Integer.compare(destino.getx(), robo.getPosicaoX());
            int dy = Integer.compare(destino.gety(), robo.getPosicaoY());

                try {
                    ambiente.moverEntidade(robo, robo.getPosicaoX() + dx, robo.getPosicaoY() + dy, robo.getposicaoZ(), robo);
                    log.log("Buscando ponto -> " + robo.get_Coordenada());
                } catch (Exception e) { 
                    log.log(e.getMessage()); 
                    break; 
                }
            }
            log.log("Alvo alcançado!");
        } catch (Exception ignored) {

        }
    }
    @Override
    public String getDescricao(){
        return "Ainda não implementado";
    }
}
