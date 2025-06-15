package simulador.robo;
import java.io.IOException;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.exceptions.RoboDesligadoException;

public class RoboLimitado extends RoboTerrestre {

    /**Função construtora do robô limitado */
    public RoboLimitado(Ambiente ambiente, Scanner scanner, EstadoRobo estado, String nome, Coordenada coordenada) {
        super(ambiente, scanner, estado, nome, coordenada);
        System.out.printf("A velocidade máxima do robô Limitado é de 5 km/h\n");
        velocidadeMax = 5;
        try {
            super.setMensagemPadrao(FrasesRobos.getFraseLimitado());
        } catch (RoboDesligadoException e) {
           System.out.println(e.getMessage());
        }
    }

    @Override
    public String getDescricao(){
        return "Olá! Eu sou o Robô Limitado, sou uma subclasse do Robô Terrestre! Além de fazer tudo que ele faz, eu posso... ahh espera, é só isso! Por isso me chamam de Limitado!";
    }

    @Override
    protected void mover(int deltaX, int deltaY, int velocidade) {
        super.mover(deltaX, deltaY, velocidade);
    }

    @Override
    public void explicar_movimentacao() {
        super.explicar_movimentacao();
    }

    @Override
    public char movimentacao() throws ColisaoException, ForadosLimitesException, IOException {
        return super.movimentacao();
    }
}