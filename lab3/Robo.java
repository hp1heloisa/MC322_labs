
import java.util.Scanner;

public abstract class Robo {

    protected String nome;
    protected String tipo;
    // protected int coordenada.getx();
    // protected int coordenada.gety();
    protected Coordenada coordenada;
    protected String direcao;
    protected Scanner scanner = new Scanner(System.in);
    protected Ambiente ambiente;
    protected SensorPlano sensorPlano;
    protected SensorTemperatura sensorTemperatura;

    /**
     * Função construtora que define inicialmente o robô já na posição X = Y = 0
     * e pergunta ao qual a direção ele está
     */
    public Robo(Ambiente ambiente) {
        sensorPlano = new SensorPlano(5, ambiente);
        sensorTemperatura = new SensorTemperatura(0, ambiente);
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
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());
        Coordenada c = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz());

        if (ambiente.dentroDosLimites(c.getx() + deltaX, c.gety() + deltaY, c.getz())) {
            int passo = 1;
            if (deltaX < 0) {
                deltaX *= -1; //Sempre vamos trabalhar com o módulo do numero]
                passo = -1;//negativo, pois o robô irá descer em altitude
            }
            while (deltaX > 0) {
                if (sensorPlano.tem_obstaculo(c.getx() + passo, c.gety(), c.getz())) {
                    System.out.printf("Há um obstáculo do tipo %s na posição: (%d,%d,%d)\n", sensorPlano.mostrar_obstaculo(c.getx()+ passo, c.gety() + passo, c.getz()),c.getx() + passo, c.gety(), c.getz());
                    return;
                } else {
                    if (sensorPlano.tem_robo(c.getx() + passo, c.gety(), c.getz())) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", c.getx() + passo, c.gety(), c.getz());
                        return;
                    } else {
                        c.setx(c.getx()+ passo);
                        deltaX--;
                    }
                }
            } // separamos em x e y, pois o enunciado diz para criar um método mover com parâmetros x e y. Se não
            passo = 1; // faríamos um mover_x e mover_y, pois o nosso robô não move na diagonal.
            if (deltaY < 0) {
                deltaY *= -1;
                passo = -1;
            }
            //Sensor aqui
            while (deltaY > 0) {
                if (sensorPlano.tem_obstaculo(c.getx(), c.gety() + passo, c.getz())) {
                    System.out.printf("Há um obstáculo do tipo %s na posição: (%d,%d,%d)\n", sensorPlano.mostrar_obstaculo(c.getx(), c.gety() + passo, c.getz()), c.getx(), c.gety() + passo, c.getz());
                    return;
                } else {
                    if (sensorPlano.tem_robo(c.getx(), c.gety() + passo, c.getz())) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", c.getx(), c.gety() + passo, c.getz());
                        return;
                    } else {
                        c.sety(c.gety() + passo);
                        deltaY--;
                    }
                }
            }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        }
        atualizarAmbiente(c_0, c);
    }
    public void identificarObstaculo() {
        sensorTemperatura.calcula_temperatura(coordenada);
        sensorPlano.identificarArea(coordenada);
    }

    public void getPosicao() {
        System.out.printf("%s se encontra na posição: (%d,%d)\n", nome, coordenada.getx(), coordenada.gety());
    }

    public String getNome() {
        System.out.printf("O nome do seu robo é: %s\n", nome);
        return nome;
    }

    //@Override 
    public String toString(){
        return nome + " - " + tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setNome(String n){
        nome = n;
    }


   

    /**
     * Método que identifica a área em um um raio de 5m, os obstáculos, robôs e
     * espaços livres
     */
    

    /**
     * Método que atualiza o ambiente de acordo com a movimentação do robô
     */
    public void atualizarAmbiente(Coordenada c_0, Coordenada c) {
        char obs = ambiente.getElemento(c.getx(),c.gety(),c.getz());
        if (obs == 'A' || obs == 'F') {
            String text = obs == 'A'  ? "Sentimos muito, mas %s morreu afogado!\n" :  "Sentimos muito, mas %s morreu queimado!\n";
            System.out.printf(text, nome);
            ambiente.removerRobo(ambiente.getIndexOfRobo(toString()));
            setNome(null);
        } else{
            ambiente.atualizar(c_0, '*');
            ambiente.atualizar(c, 'r');
            coordenada.setx(c.getx());
            coordenada.sety(c.gety());
            coordenada.setz(c.getz());
        }
        
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
