package simulador.interfaces;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;

public interface InterfaceRobo extends Entidade{
    String getNome(); 
    void getPosicao(); 
    void print_sensores(); 
    char movimentacao() throws ColisaoException, ForadosLimitesException;
    void explicar_movimentacao();
}