package simulador.interfaces;


import simulador.exceptions.RoboDesligadoException;

public interface Sensoriavel{

    void acionarSensores() throws RoboDesligadoException;
}
