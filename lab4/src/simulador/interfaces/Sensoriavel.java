package simulador.interfaces;


import simulador.robo.RoboDesligadoException;

public interface Sensoriavel{

    void acionarSensores() throws RoboDesligadoException;
}
