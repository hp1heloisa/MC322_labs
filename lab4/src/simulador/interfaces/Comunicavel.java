package simulador.interfaces;

import simulador.exceptions.RoboDesligadoException;
import simulador.robo.EstadoRobo;

public interface Comunicavel {
    String getNome();
    EstadoRobo getEstado();
    String enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;
    void receberMensagem(String mensagem) throws RoboDesligadoException;
}
