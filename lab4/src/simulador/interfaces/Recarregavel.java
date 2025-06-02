package simulador.interfaces;

import simulador.exceptions.EnergiaInsuficienteException;

public interface Recarregavel {
    void recarregar();
    int getNivelBateria();
    void consumirEnergia(int quantidade) throws EnergiaInsuficienteException;
}

