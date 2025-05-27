package simulador.interfaces;
import simulador.robo.RoboDesligadoException;

public interface Sensoriavel{
    acionarSensores() throws RoboDesligadoException;
}