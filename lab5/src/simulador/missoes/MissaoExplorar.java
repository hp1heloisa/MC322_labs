package simulador.missoes;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.ambiente.TipoObstaculo;
import simulador.interfaces.Missao;
import simulador.robo.Robo;

public class MissaoExplorar implements Missao {

    public void executar(Robo r, Ambiente ambiente) {

    }

    public void MissaoExplorar(TipoObstaculo tipoObstaculo, Robo robo) {
        Coordenada coordenada_desejada = robo.sensorPlano.busca_coord_obstaculo(tipoObstaculo, robo.get_Coordenada());
    }
}
