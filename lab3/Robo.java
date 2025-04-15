
import java.util.Scanner;

public abstract class Robo {

    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;
    protected Scanner scanner = new Scanner(System.in);
    protected Ambiente ambiente;

    /**
     * Função construtora que define inicialmente o robô já na posição X = Y = 0
     * e pergunta ao qual a direção ele está
     */
    public Robo(Ambiente ambiente) {
        this.ambiente = ambiente;
        System.out.printf("Diga qual é o nome do seu robô\n");
        nome = scanner.nextLine();
        System.out.printf("Em que direção %s se encontra? Norte, Leste, Sul ou Oeste? \n", nome);
        direcao = scanner.nextLine();
        System.out.printf("Aviso: Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = Z = 0)\n");
        posicaoX = 0;
        posicaoY = 0;
    }

    /**
     * Abstrata, pois os robôs têm movimentações distintas
     */
    public abstract void explicar_movimentacao();

    /**
     * Abstrata, pois os robôs têm movimentações distintas
     */
    public abstract char movimentacao();

    /**
     * Método que o robô se move no campo sempre para um lugar sem nenhum
     * obstáculo
     */
    protected void mover(int deltaX, int deltaY) {
        Coordenada c_0 = new Coordenada(posicaoX, posicaoY, 0);
        if (ambiente.dentroDosLimites(posicaoX + deltaX, posicaoY + deltaY, 0)) {
            int passo = 1;
            if (deltaX < 0) {
                deltaX *= -1; //Sempre vamos trabalhar com o módulo do numero]
                passo = -1;//negativo, pois o robô irá descer em altitude
            }
            while (deltaX > 0) {
                if (ambiente.tem_obstaculo(posicaoX + passo, posicaoY, 0)) {
                    System.out.printf("Há um obstáculo na posição: (%d,%d,%d)\n", posicaoX + passo, posicaoY, 0);
                    return;
                } else {
                    if (ambiente.tem_robo(posicaoX + passo, posicaoY, 0)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX + passo, posicaoY, 0);
                        return;
                    } else {
                        posicaoX += passo;
                        deltaX--;
                    }
                }
            } // separamos em x e y, pois o enunciado diz para criar um método mover com parâmetros x e y. Se não
            passo = 1; // faríamos um mover_x e mover_y, pois o nosso robô não move na diagonal.
            if (deltaY < 0) {
                deltaY *= -1;
                passo = -1;
            }
            while (deltaY > 0) {
                if (ambiente.tem_obstaculo(posicaoX, posicaoY + passo, 0)) {
                    System.out.printf("Há um obstáculo na posição: (%d,%d,%d)", posicaoX, posicaoY + passo, 0);
                    return;
                } else {
                    if (ambiente.tem_robo(posicaoX, posicaoY + passo, 0)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX, posicaoY + passo, 0);
                        return;
                    } else {
                        posicaoY += passo;
                        deltaY--;
                    }
                }
            }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        }
        Coordenada c = new Coordenada(posicaoX, posicaoY, 0);
        atualizarAmbiente(c_0, c);
    }

    public void identificarObstaculo() {
        System.out.println("Obstáculos identificados em um raio de 5m: ");
        identificarArea(0);
    }

    public void getPosicao() {
        System.out.printf("%s se encontra na posição: (%d,%d)\n", nome, posicaoX, posicaoY);
    }

    public String getNome() {
        System.out.printf("O nome do seu robo é: %s\n", nome);
        return nome;
    }

    public int getposicaoX() {
        return posicaoX;
    }

    public int getposicaoY() {
        return posicaoY;
    }

    protected void setPosicaoX(int x) {
        posicaoX = x;
    }

    protected void setPosicaoY(int y) {
        posicaoY = y;
    }

    /**
     * Método que identifica a área em um um raio de 5m, os obstáculos, robôs e
     * espaços livres
     */
    public void identificarArea(int alt) {
        for (int y = 5; y > -5; y--) {
            if (posicaoY + y < 0) {
                continue;
            }
            for (int x = -5; x < 5; x++) {
                if (posicaoX + x < 0) {
                    continue;
                }
                if (ambiente.dentroDosLimites(posicaoX + x, posicaoY + y, alt) && ambiente.tem_obstaculo(posicaoX + x, posicaoY + y, alt)) {
                    System.out.printf("X");
                } else {
                    if (x == 0 && y == 0) {
                        System.out.printf("R"); 
                    }else if (ambiente.tem_robo(posicaoX + x, posicaoY + y, alt)) {
                        System.out.printf("r"); 
                    }else {
                        System.out.printf("*");
                    }

                }
            }
            System.out.println("");
        }
    }

    /**
     * Método que atualiza o ambiente de acordo com a movimentação do robô
     */
    public void atualizarAmbiente(Coordenada c_0, Coordenada c) {
        ambiente.atualizar(c_0, '*');
        ambiente.atualizar(c, 'r');
    }
}
