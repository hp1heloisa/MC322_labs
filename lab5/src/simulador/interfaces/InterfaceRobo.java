package simulador.interfaces;
import java.io.IOException;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;

public interface InterfaceRobo extends Entidade, Sensoriavel, Comunicavel, Recarregavel{
    int getId();
    String getNome(); 
    void getPosicao(); 
    void print_sensores(); 
    char movimentacao() throws ColisaoException, ForadosLimitesException, IOException;
    void explicar_movimentacao();
}