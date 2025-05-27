public interface InterfaceRobo extends Entidade{
    String getNome(); 
    void getPosicao(); 
    void print_sensores(); 
    char movimentacao() throws ColisaoException;
    void explicar_movimentacao();
}