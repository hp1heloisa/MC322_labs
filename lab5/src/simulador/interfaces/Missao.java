package simulador.interfaces;
import java.io.IOException;
import simulador.ambiente.Ambiente;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.robo.Robo;

public interface Missao {
    void executar(Robo robo, Ambiente ambiente) throws ColisaoException, IOException, ForadosLimitesException;
    String getDescricao();
}