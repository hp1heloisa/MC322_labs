package simulador.missao;

import java.io.IOException;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
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
    public void executar(Robo robo, Ambiente ambiente) throws ColisaoException, IOException, ForadosLimitesException{
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            int passo = 0;
            Coordenada pos_atual = new Coordenada(robo.getX(), robo.getY(), robo.getZ());
            Coordenada trajeto = new Coordenada(destino.getx() - robo.getX(), destino.gety() - robo.getY(), destino.getz() - robo.getZ());
            int num_passos_total = Math.abs(trajeto.getx()) + Math.abs(trajeto.gety()) + Math.abs(trajeto.getz());
            if (num_passos_total > passosMax) {
                log.log("Impossível fazer o percurso, pois o nosso objeto está mais longe do que o " + robo.getNome() + " consegue andar");
                return;
            }
            log.log("Iniciando missão. Destino " + destino.toString());
            while (robo.getEstado() == EstadoRobo.ligado && passo <= passosMax) {
                if(pos_atual.equals(destino)){
                    log.log("Alvo alcançado na posição " + pos_atual.toString() + " em " + passo + "passos");
                    break;
                }
                passo++;
                while (Math.abs(trajeto.getx()) > 0) {
                    int dx= robo.getX(), dy = robo.getY(), dz = robo.getZ();
                    if (trajeto.getx() > 0) {
                        dx = robo.getX() + 1;
                    } else {
                        dx = robo.getX() - 1;
                    }
                    try {
                        ambiente.moverEntidade(robo, dx, dy, dz, robo);
                        log.log("Buscando ponto -> " + robo.get_Coordenada());
                    } catch(ColisaoException e){
                        Coordenada nova_pos = new Coordenada(dx, dy, dz);
                        String mens = String.format("Passo " + passo + ": Colisão detectada em " + nova_pos.toString());
                        log.log(mens);
                    }

                }
            
            }
        }   catch (Exception ignored) {
                return;
        }
    }
        @Override
        public String getDescricao  (){
        return "Deu certo! Robo no ponto" + this.destino;
        }

    }
