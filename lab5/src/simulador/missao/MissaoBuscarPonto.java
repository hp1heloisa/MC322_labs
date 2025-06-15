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

public class MissaoBuscarPonto implements Missao {

    private final Coordenada destino;
    private final int passosMax;
    private boolean sucesso = false;

    public MissaoBuscarPonto(Coordenada destino, int passosMax) {
        this.destino = destino;
        this.passosMax = passosMax;
    }

    @Override
    public String getDescricao() {
        if (sucesso) {
            return "Robô chegou com sucesso ao ponto " + destino;
        }
        return "Buscar o ponto " + destino.toString();
    }
    
    @Override
    public void executar(Robo robo, Ambiente ambiente) throws IOException {
        try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
            log.log("Iniciando missão '" + getDescricao() + "'.");
            
            // Verificação inicial para robôs terrestres
            if (robo instanceof RoboTerrestre && robo.getZ() != destino.getz()) {
                log.log("ERRO: Missão impossível. " + robo.getNome() + " é um robô terrestre e não pode se mover no eixo Z.");
                return;
            }

            int passo = 0;
            // Loop principal: um passo de cada vez
            while (passo < passosMax && robo.getEstado() == EstadoRobo.ligado) {
                
                Coordenada posAtual = robo.get_Coordenada();

                // Verifica se chegou ao destino
                if (posAtual.getx() == destino.getx() && posAtual.gety() == destino.gety() && posAtual.getz() == destino.getz()) {
                    log.log("SUCESSO: Alvo alcançado em " + passo + " passos na posição " + posAtual);
                    this.sucesso = true;
                    break; 
                }
                
                passo++;

                int dx = destino.getx() - posAtual.getx();
                int dy = destino.gety() - posAtual.gety();
                int dz = destino.getz() - posAtual.getz();

                int proximoX = posAtual.getx();
                int proximoY = posAtual.gety();
                int proximoZ = posAtual.getz();
                
                boolean moveu = false;

                if (Math.abs(dx) >= Math.abs(dy) && Math.abs(dx) >= Math.abs(dz)) {
                    if (dx != 0) {
                        proximoX += Integer.signum(dx);
                        moveu = true;
                    }
    
                } else if (Math.abs(dy) > Math.abs(dx) && Math.abs(dy) >= Math.abs(dz)) {
                    if (dy != 0) {
                        proximoY += Integer.signum(dy);
                        moveu = true;
                    }
            
                } else {
                    if (dz != 0) {
                        proximoZ += Integer.signum(dz);
                        moveu = true;
                    }
                }
                
                if (moveu) {
                    try {
                        ambiente.moverEntidade(robo, proximoX, proximoY, proximoZ, robo);
                        log.log(String.format("Passo %d: Moveu para %s", passo, robo.get_Coordenada()));
                    
                    } catch (ColisaoException e) {
                        Coordenada coordOndeColidiu = new Coordenada(proximoX, proximoY, proximoZ);
                        log.log(String.format("Passo %d: Colisão em %s. Acionando poder.", passo, coordOndeColidiu));
                        try {
                            robo.poder(coordOndeColidiu, posAtual);
                        } catch (Exception poderException) {
                            log.log("AVISO: Falha ao usar o poder. " + poderException.getMessage());
                        }
                    
                    } catch (ForadosLimitesException e) {
                        log.log(String.format("Passo %d: Movimento para (%d, %d, %d) está fora dos limites.", passo, proximoX, proximoY, proximoZ));
                    }
                }
            }
            
            if (!sucesso) {
                log.log("FALHA: Missão não concluída. Passos executados: " + passo);
            }
        }
        // O bloco catch genérico foi removido pois escondia os erros.
    }
}