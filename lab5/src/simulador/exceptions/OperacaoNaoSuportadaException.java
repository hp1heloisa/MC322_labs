package simulador.exceptions;

public class OperacaoNaoSuportadaException extends Exception{

    public OperacaoNaoSuportadaException(String text){
        super(text);
    }
}