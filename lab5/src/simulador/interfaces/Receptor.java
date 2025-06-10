package simulador.interfaces;

import simulador.exceptions.RoboDesligadoException;

public interface Receptor {
    void receberMensagem(Comunicavel remetente, String mensagem) throws RoboDesligadoException; 
    void receberMensagensDoAmbiente() throws RoboDesligadoException;
}
