package simulador.interfaces;

import simulador.exceptions.RoboDesligadoException;

public interface Emissor {
    String enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;
    String getMensagemPadrao() throws RoboDesligadoException;
    void setMensagemPadrao(String msg) throws RoboDesligadoException;
}
