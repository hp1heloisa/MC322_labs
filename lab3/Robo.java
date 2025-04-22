
import java.util.Scanner;

public abstract class Robo {

    protected String nome;
    // protected int coordenada.getx();
    // protected int coordenada.gety();
    protected Coordenada coordenada;
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
        this.coordenada = new Coordenada(0, 0, 0);
        // coordenada.getx() = 0;
        // coordenada.gety() = 0;
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
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), 0);
        if (ambiente.dentroDosLimites(coordenada.getx() + deltaX, coordenada.gety() + deltaY, coordenada.getz())) {
            int passo = 1;
            if (deltaX < 0) {
                deltaX *= -1; //Sempre vamos trabalhar com o módulo do numero]
                passo = -1;//negativo, pois o robô irá descer em altitude
            }
            while (deltaX > 0) {
                if (ambiente.tem_obstaculo(coordenada.getx() + passo, coordenada.gety(), 0)) {
                    System.out.printf("Há um obstáculo do tipo %s na posição: (%d,%d,%d)\n", ambiente.mostrar_obstaculo(coordenada.getx()+ passo, coordenada.gety() + passo, coordenada.getz()),coordenada.getx() + passo, coordenada.gety(), coordenada.getz());
                    return;
                } else {
                    if (ambiente.tem_robo(coordenada.getx() + passo, coordenada.gety(), coordenada.getz())) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", coordenada.getx() + passo, coordenada.gety(), coordenada.getz());
                        return;
                    } else {
                        coordenada.setx(coordenada.getx()+ passo);
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
                if (ambiente.tem_obstaculo(coordenada.getx(), coordenada.gety() + passo, coordenada.getz())) {
                    System.out.printf("Há um obstáculo do tipo %s na posição: (%d,%d,%d)\n", ambiente.mostrar_obstaculo(coordenada.getx(), coordenada.gety() + passo, coordenada.getz()), coordenada.getx(), coordenada.gety() + passo, coordenada.getz());
                    return;
                } else {
                    if (ambiente.tem_robo(coordenada.getx(), coordenada.gety() + passo, coordenada.getz())) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", coordenada.getx(), coordenada.gety() + passo, 0);
                        return;
                    } else {
                        coordenada.sety(coordenada.gety() + passo);
                        deltaY--;
                    }
                }
            }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        }
        Coordenada c = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        atualizarAmbiente(c_0, c);
    }

    public void identificarObstaculo() {
        System.out.println("Obstáculos identificados em um raio de 5m: ");
        identificarArea(0);
    }

    public void getPosicao() {
        System.out.printf("%s se encontra na posição: (%d,%d)\n", nome, coordenada.getx(), coordenada.gety());
    }

    public String getNome() {
        System.out.printf("O nome do seu robo é: %s\n", nome);
        return nome;
    }



   

    /**
     * Método que identifica a área em um um raio de 5m, os obstáculos, robôs e
     * espaços livres
     */
    public void identificarArea(int alt) {
        for (int y = 5; y > -5; y--) {
            if (coordenada.gety() + y < 0) {
                continue;
            }
            for (int x = -5; x < 5; x++) {
                if (coordenada.getx() + x < 0) {
                    continue;
                }
                if (ambiente.dentroDosLimites(coordenada.getx() + x, coordenada.gety() + y, alt)) {
                    ambiente.print_coordenada(coordenada.getx()+x, coordenada.gety() + y, alt);
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
    public int getPosicaoX(){
        return coordenada.getx();
    }
    public int getPosicaoY(){
        return coordenada.gety();
    }
    public int getposicaoZ(){
        return coordenada.getz();
    }
}
