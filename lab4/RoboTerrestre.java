import java.util.Scanner;

public class RoboTerrestre extends Robo {

    protected int velocidadeMax = 100;
    protected int velocidadeatual = 0;

    /**
     * Função construtora do robô terrestre
     */
    public RoboTerrestre(Ambiente ambiente, Scanner scanner) {
        super(ambiente, scanner, ambiente.getlistRobos());
    }

    @Override
    public String getDescricao(){
        return "Olá! Eu sou um Robô Terrestre e eu posso me mover nas coordenadas X e Y.";
    }

    @Override
    public void explicar_movimentacao() {
        System.out.println("Você pode movimentar seu robô usando os seguintes comandos: ");
        System.out.println("w -> ir para frente; s -> ir para trás");
        System.out.println("d -> ir para direita; a -> ir para a esqueda");
        System.out.println("p -> para scanear a área; q -> para aumentar velocidade");
        System.out.println("n -> criar um novo robô; x -> para sair");
        System.out.println("c -> remover ou trocar de robô");
    }

    protected void mover(int deltaX, int deltaY, int velocidade) {
        if (velocidade <= velocidadeMax) {
            if (velocidadeatual == 0) {
                System.out.println("Seu robô se encontra com velocidade 0, digite q para aumentar sua velocidade e começar a movê-lo"); 
            }else {
                super.mover(deltaX, deltaY);
            }
        }

    }

    /**Função que atualiza a velocidade, não permitindo que a velocidade atual ultrapasse a velocidade máxima */
    protected void setVelocidade(int vel) {
        if (vel <= velocidadeMax) {
            velocidadeatual = vel;
            System.out.println("Velocidade atualizada para: " + velocidadeatual);
        } else {
            System.out.printf("Não foi possível atualizar sua velocidade, pois a velocidade máxima é de %d! Sua atual velocidade é %d\n", velocidadeMax, velocidadeatual);
        }
    }

    @Override
    public char movimentacao() {
        char movimento_robo = ' ';
        System.out.printf("Aperte uma tecla de movimentação para começar\n");
        while (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
            if (nome == null) System.out.println("Seu robô morreu! Digite c ou n, para ir para outro robô ou para criar um novo robô:");
            movimento_robo = scanner.next().charAt(0);
            if (movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                explicar_movimentacao();
            }
            switch (movimento_robo) {
                case 'a':
                    this.mover(-1, 0, this.velocidadeatual);
                    break;
                case 'd':
                    this.mover(1, 0, this.velocidadeatual);
                    break;
                case 'w':
                    this.mover(0, 1, this.velocidadeatual);
                    break;
                case 's':
                    this.mover(0, -1, this.velocidadeatual);
                    break;
                case 'q':
                    setVelocidade(velocidadeatual + 1);
                    break;
                case 'p':
                    print_sensores();
                    break;
                case 'x':
                    System.out.println("Encerrando movimentação...");
                    break;
                case 'n':
                    break;
                case 'c':
                    break;
                default:
                    System.out.println("Comando inválido! Use a, d, q ou x");
            }
            if (movimento_robo != 'p' && movimento_robo != 'x' && movimento_robo != 'n' && movimento_robo != 'c') {
                this.print_sensores();            
            }
        }
        return movimento_robo;
    }
}
