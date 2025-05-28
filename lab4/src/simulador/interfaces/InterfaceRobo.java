package simulador.interfaces;
import simulador.ambiente.ColisaoException;
import simulador.ambiente.ForadosLimitesException;

public interface InterfaceRobo extends Entidade{
    String getNome(); 
    void getPosicao(); 
    void print_sensores(); 
    char movimentacao() throws ColisaoException, ForadosLimitesException;
    void explicar_movimentacao();
}