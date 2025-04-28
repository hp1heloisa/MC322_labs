
import java.util.Scanner;

public abstract class Robo {

    protected String nome;
    protected String tipo;
    protected Coordenada coordenada;
    protected String direcao;
    protected Scanner scanner;
    protected Ambiente ambiente;
    protected SensorPlano sensorPlano;
    protected SensorTemperatura sensorTemperatura;
    protected SensorUmidade sensorHumidade;

    /**
     * Função construtora que define inicialmente o robô já na posição X = Y = 0
     * e pergunta ao qual a direção ele está
     */
    public Robo(Ambiente ambiente, Scanner scanner) {
        this.scanner = scanner;
        sensorPlano = new SensorPlano(5, ambiente);
        sensorTemperatura = new SensorTemperatura(5, 0, ambiente);
        sensorHumidade = new SensorUmidade(5, 0, ambiente);
        this.ambiente = ambiente;
        System.out.printf("Diga qual é o nome do seu robô\n");
        nome = scanner.nextLine();
        System.out.printf("Em que direção %s se encontra? Norte, Leste, Sul ou Oeste? \n", nome);
        direcao = scanner.nextLine();
        System.out.printf("Aviso: Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = Z = 0)\n");
        this.coordenada = new Coordenada(0, 0, 0);
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
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz()); // Coordenada inicial do robô
        Coordenada nova_pos = new Coordenada(coordenada.getx() + deltaX, coordenada.gety() + deltaY, coordenada.getz()); // Nova coordenada do robô

        if (ambiente.dentroDosLimites(nova_pos)) {
                if (sensorPlano.tem_obstaculo(nova_pos)) {

                    System.out.printf("Há um obstáculo do tipo %s na posição: %s\n", sensorPlano.mostrar_obstaculo(nova_pos), nova_pos);
                    return;
                } else if (sensorPlano.tem_robo(nova_pos)) {
                        System.out.printf("Há um robô na posição: (%s)\n", nova_pos);
                        return;
                }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
            return;
        }
        atualizarAmbiente(c_0, nova_pos);
        System.out.printf("\n");
    }

    /**Método que printa as informações dos sensores */
    public void print_sensores(){
        sensorTemperatura.calcula_temperatura(coordenada);
        sensorHumidade.calcula_umidade(coordenada);
        sensorPlano.identificarArea(coordenada);
    }

    /**Método que retorna a posição do robô */
    public void getPosicao() {
        System.out.printf("%s se encontra na posição: %s\n", nome, coordenada);
    }

    /**Método que retorna o nome do robô */
    public String getNome() {
        System.out.printf("O nome do seu robo é: %s\n", nome);
        return nome;
    }

    public String toString(){
        return nome + " - " + tipo;
    }

    /**Método que define o tipo do robô. EX: robô destruidor, robô limitado, etc*/
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    /**Método que define o nome do robô */
    public void setNome(String n){
        nome = n;
    }


    /**
     * Método que atualiza o ambiente de acordo com a movimentação do robô
     */
    public void atualizarAmbiente(Coordenada c_0, Coordenada c) {
        char obs = ambiente.getElemento(c);
        if (obs == 'L' || obs == 'F') {
            String text = obs == 'L'  ? "Sentimos muito, mas %s morreu afogado!\n" :  "Sentimos muito, mas %s morreu queimado!\n";
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
    /**Método que retorna a posiçãoX do robô */
    public int getPosicaoX(){
        return coordenada.getx();
    }
    /**Método que retorna a posição Y do robô */
    public int getPosicaoY(){
        return coordenada.gety();
    }
    /**Método que retorna a posição Z do robô */
    public int getposicaoZ(){
        return coordenada.getz();
    }
}
