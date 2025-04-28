import java.util.Scanner;

public class RoboLimitado extends RoboTerrestre {

    public RoboLimitado(Ambiente ambiente, Scanner scanner) {
        super(ambiente, scanner);
        System.out.printf("A velocidade máxima do robô Limitado é de 5 km/h\n");
        velocidadeMax = 5;
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
    public char movimentacao() {
        return super.movimentacao();
    }
}
