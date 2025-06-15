package simulador.robo;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;

/**
 * Robô que executa missões de forma autônoma.
 */
public abstract class AgenteInteligente extends Robo {

    protected Missao missao;

    public AgenteInteligente(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, null, ambiente.getlistRobos(), EstadoRobo.ligado, nome, pos_inicial);
    }

    /** Método para atribuir missões ao robô. */
    public void definirMissao(Missao missao) { 
        this.missao = missao; 
    }

    /** Método para indicar se o robô tem uma missão. */
    public boolean temMissao() {
        return missao != null; 
    }
<<<<<<< HEAD
=======

    /** Método para executar missão */
    public void executarMissao(Ambiente a) {
        if (temMissao()) missao.executar(this, a);
        else System.out.println(getNome() + " não tem nenhuma missão.");
    }
>>>>>>> parent of 23012e5 (Atualizações)
}
