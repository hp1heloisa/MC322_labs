package simulador.interfaces;

import simulador.robo.EstadoRobo;

public interface Comunicavel extends Emissor, Receptor{
    String getNome();
    EstadoRobo getEstado();
}
