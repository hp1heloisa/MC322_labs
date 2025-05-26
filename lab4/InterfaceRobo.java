public interface InterfaceRobo extends Entidade{
    String getNome(); 
    void getPosicao(); 
    void print_sensores(); 
    char movimentacao();
    void explicar_movimentacao();
}