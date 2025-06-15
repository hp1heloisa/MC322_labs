package simulador.robo;

import java.util.Arrays;
import java.util.List;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;
import simulador.missao.MissaoMonitorar;
import simulador.missao.MissaoPatrulhar;

public class RoboPatrulheiro extends AgenteInteligente {

    private final List<Missao> pipeline;

    public RoboPatrulheiro(Ambiente ambiente, List<Coordenada> waypoints, int ciclosMonitoramento, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
        setNome(nome);
        this.pipeline = Arrays.asList(
            new MissaoPatrulhar(waypoints),
            new MissaoMonitorar(waypoints.get(0), ciclosMonitoramento));
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
