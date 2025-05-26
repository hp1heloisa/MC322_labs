import java.util.Scanner;

public class RoboLimitado extends RoboTerrestre {

    /**Função construtora do robô limitado */
    public RoboLimitado(Ambiente ambiente, Scanner scanner) {
        super(ambiente, scanner);
        System.out.printf("A velocidade máxima do robô Limitado é de 5 km/h\n");
        velocidadeMax = 5;
    }

    @Override
    public TipoEntidade getTipo(){
        return TipoEntidade.ROBO_LIMITADO;
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
    public char movimentacao() {
        return super.movimentacao();
    }
}
