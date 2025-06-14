package simulador.missao;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
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

    @Override 
    public void executar(Robo robo, Ambiente ambiente) {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            int passo = 1;
            while (!robo.get_Coordenada().toString().equals(destino.toString()) && robo.getEstado() == EstadoRobo.ligado && passo <= passosMax) {
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
