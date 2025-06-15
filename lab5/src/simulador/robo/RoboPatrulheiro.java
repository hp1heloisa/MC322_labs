package simulador.robo;

import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;

public class RoboPatrulheiro extends AgenteInteligente {

    // 1. O campo 'pipeline' foi REMOVIDO. Ele agora é herdado de AgenteInteligente.
    
    // 2. O construtor é simplificado. Ele apenas cria o robô base.
    // O construtor que criava uma pipeline fixa foi REMOVIDO.
    public RoboPatrulheiro(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
    }

    @Override 
    public String getDescricao() { 
        return "Robô Patrulheiro (patrulhar + monitorar)"; 
    }
    @Override 
    public void explicar_movimentacao() {
    }
    @Override 
    public char movimentacao() throws ColisaoException, ForadosLimitesException { 
        return 'x'; 
    }

    @Override 
    public void executarMissao(Ambiente ambiente) {
        for (Missao m : pipeline) {
            if (getEstado() == EstadoRobo.desligado) 
                break;
            definirMissao(m);
            super.executarMissao(ambiente);
        }
    }
}
