package simulador.missao;

import java.io.IOException;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;
import simulador.robo.EstadoRobo;
import simulador.robo.Robo;
import simulador.robo.RoboTerrestre;

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
    public void executar(Robo robo, Ambiente ambiente) throws ColisaoException, IOException, ForadosLimitesException {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            int passo = 0;
            Coordenada pos_atual = new Coordenada(robo.getX(), robo.getY(), robo.getZ());
            Coordenada trajeto = new Coordenada(destino.getx() - robo.getX(), destino.gety() - robo.getY(),
                    destino.getz() - robo.getZ());
            int num_passos_total = Math.abs(trajeto.getx()) + Math.abs(trajeto.gety()) + Math.abs(trajeto.getz());
            if (num_passos_total > passosMax) {
                log.log("Impossível fazer o percurso, pois o nosso objetivo está mais longe do que o " + robo.getNome()
                        + " consegue andar");
                return;
            }
            if (trajeto.getz() != robo.getZ() && robo instanceof RoboTerrestre) {
                log.log("Impossível fazer o percurso, pois o nosso o nosso robô "
                        + " é terrestre e não pode mudar de altitude");
                System.out.println("Aqui");
                return;
            }
            log.log("Iniciando missão. Destino " + destino.toString());
            while (robo.getEstado() == EstadoRobo.ligado && passo <= passosMax) {
                if (pos_atual.equals(destino)) {
                    log.log("Alvo alcançado na posição " + pos_atual.toString() + " em " + passo + "passos");
                    break;
                }
                while (Math.abs(trajeto.getx()) > 0) {
                    int dx = robo.getX(), dy = robo.getY(), dz = robo.getZ();
                    if (trajeto.getx() > 0) {
                        dx = robo.getX() + 1;
                        trajeto.setx(trajeto.getx() - 1);
                    } else {
                        dx = robo.getX() - 1;
                        trajeto.setx(trajeto.getx() + 1);
                    }

                    tentarMoverPara(dx, dy, dz, robo, ambiente, log, num_passos_total);
                    passo++;
                }
                while (Math.abs(trajeto.gety()) > 0) {
                    int dx = robo.getX(), dy = robo.getY(), dz = robo.getZ();
                    if (trajeto.gety() > 0) {
                        dy = robo.getY() + 1;
                        trajeto.sety(trajeto.gety() - 1);
                    } else {
                        dy = robo.getY() - 1;
                        trajeto.sety(trajeto.gety() + 1);
                    }
                    tentarMoverPara(dx, dy, dz, robo, ambiente, log, num_passos_total);
                    passo++;
                }
                while (Math.abs(trajeto.getz()) > 0) {
                    int dx = robo.getX(), dy = robo.getY(), dz = robo.getZ();
                    if (trajeto.getz() > 0) {
                        dz = robo.getZ() + 1;
                        trajeto.setz(trajeto.getz() - 1);
                    } else {
                        dz = robo.getZ() - 1;
                        trajeto.setz(trajeto.getz() + 1);
                    }
                    tentarMoverPara(dx, dy, dz, robo, ambiente, log, num_passos_total);
                    passo++;
                }

            }
        } catch (Exception ignored) {
            return;
        }
    }

    @Override
    public String getDescricao() {
        return "Deu certo! Robo no ponto" + this.destino;
    }

    private void tentarMoverPara(int proximoX, int proximoY, int proximoZ, Robo robo, Ambiente ambiente,
            LogadorMissao log, int passo)
            throws ColisaoException, ForadosLimitesException {
        try {
            ambiente.moverEntidade(robo, proximoX, proximoY, proximoZ, robo);
            log.log("Passo " + passo + ": Moveu para " + robo.get_Coordenada());

        } catch (ColisaoException e) {

            String mensagem = String.format("Passo %d: Colisão detectada ao tentar mover para (%d, %d, %d).",
                    passo, proximoX, proximoY, proximoZ);
            log.log(mensagem);
            Coordenada nova_pos = new Coordenada(proximoX, proximoY, proximoZ);
            robo.poder(nova_pos, robo.get_Coordenada());
            throw e; 

        } catch (ForadosLimitesException e) {
            String mensagem = String.format("Passo %d: Tentativa de movimento para fora dos limites em (%d, %d, %d).",
                    passo, proximoX, proximoY, proximoZ);
            log.log(mensagem);
            throw e; // Relança a exceção
        }
    }

}
