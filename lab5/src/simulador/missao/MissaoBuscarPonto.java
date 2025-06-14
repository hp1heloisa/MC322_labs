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
                log.log("Passo " + passo + ": Buscando ponto -> " + robo.get_Coordenada());
            } catch (Exception e) {
                log.log("Passo " + passo + ": Falha ao mover. Causa: " + e.getMessage());
                break; // Interrompe a missão se não puder se mover
            }
        }
        if (robo.get_Coordenada().equals(destino)) {
            log.log("Alvo alcançado em " + destino + "!");
        } else {
            log.log("Não foi possível alcançar o alvo. Parou em " + robo.get_Coordenada());
        }
    }

    @Override
<<<<<<< HEAD
    public String getDescricao() {
        return "Buscar o ponto " + this.destino;
=======
    public String getDescricao(){
        return "Ainda não implementado";
>>>>>>> parent of 14cbc99 (Falta debug com o arquivo)
    }
}