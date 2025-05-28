package simulador.robo;

import java.util.ArrayList;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.ColisaoException;
import simulador.ambiente.Coordenada;
import simulador.ambiente.ForadosLimitesException;
import simulador.ambiente.TipoEntidade;
import simulador.interfaces.Entidade;
import simulador.interfaces.InterfaceRobo;
import simulador.sensores.SensorPlano;
import simulador.sensores.SensorTemperatura;
import simulador.sensores.SensorUmidade;

public abstract class Robo implements Entidade, InterfaceRobo {

    protected String nome;
    protected TipoEntidade tipo;
    protected Coordenada coordenada;
    protected Scanner scanner;
    protected Ambiente ambiente;
    protected EstadoRobo estado;
    protected SensorPlano sensorPlano;
    protected SensorTemperatura sensorTemperatura;
    protected SensorUmidade sensorHumidade;

    /**
     * Função construtora que define inicialmente o robô já na posição X = Y = 0
     * e pergunta ao qual a direção ele está
     */
    public Robo(Ambiente ambiente, Scanner scanner, ArrayList<Robo> listaRobos, EstadoRobo estado) {
        this.scanner = scanner;
        sensorPlano = new SensorPlano(5, ambiente);
        sensorTemperatura = new SensorTemperatura(5, 0, ambiente);
        sensorHumidade = new SensorUmidade(5, 0, ambiente);
        this.ambiente = ambiente;
        System.out.printf("O nome o seu robô é robo_%d ",listaRobos.size());
        nome = "robo" + listaRobos.size();
        System.out.printf("Aviso: Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = Z = 0)\n");
        this.coordenada = new Coordenada(0, 0, 0);
        this.estado = estado;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.ROBO;
    }

    @Override
    public char getRepresentacao() {
        return 'R';
    }

    @Override
    public String getDescricao() {
        return "O seu robô está nesta posição";
    }

    @Override
    public int getX() {
        return coordenada.getx();
    }

    @Override
    public int getY() {
        return coordenada.gety();
    }

    @Override
    public int getZ() {
        return coordenada.getz();
    }
        @Override
        public void setX(int x){
            coordenada.setx(x);
        }
        @Override
        public void setY(int y){
            coordenada.sety(y);
        }
        @Override
        public void setZ(int z){
            coordenada.setz(z);
        }

    /**
     * Abstrata, pois os robôs têm movimentações distintas
     */
    public abstract void explicar_movimentacao();

    /**
     * Abstrata, pois os robôs têm movimentações distintas
     */
    public abstract char movimentacao() throws ColisaoException, ForadosLimitesException;

    /**
     * Método que o robô se move no campo sempre para um lugar sem nenhum
     * obstáculo
     */
    protected void mover(int deltaX, int deltaY) throws ForadosLimitesException{
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
            throw new ForadosLimitesException("Essa posição encontra-se fora dos limites do ambiente");
        }
        atualizarAmbiente(c_0, nova_pos);
        System.out.printf("\n");
    }

    /**
     * Método que printa as informações dos sensores
     */
    public void print_sensores() {
        sensorTemperatura.calcula_temperatura(coordenada);
        sensorHumidade.calcula_umidade(coordenada);
        sensorPlano.identificarArea(coordenada);
    }

    /**
     * Método que retorna a posição do robô
     */
    public void getPosicao() {
        System.out.printf("%s se encontra na posição: %s\n", nome, coordenada);
    }

    /**
     * Método que retorna o nome do robô
     */
    public String getNome() {
        System.out.printf("O nome do seu robo é: %s\n", nome);
        return nome;
    }

    public String toString() {
        return nome + " - " + tipo;
    }

    /**
     * Método que define o tipo do robô. EX: robô destruidor, robô limitado, etc
     */

    /**
     * Método que define o nome do robô
     */
    public void setNome(String n) {
        nome = n;
    }

    /**
     * Método que atualiza o ambiente de acordo com a movimentação do robô
     */
    public void atualizarAmbiente(Coordenada c_0, Coordenada c) {
        char obs = ambiente.getElemento(c);
        if (obs == 'L' || obs == 'F') {
            String text = obs == 'L' ? "Sentimos muito, mas %s morreu afogado!\n" : "Sentimos muito, mas %s morreu queimado!\n";
            System.out.printf(text, nome);
            ambiente.removerEntidade(this);
            setNome(null);
        } else {
            ambiente.atualizar_espaco_vazio(c_0);
            ambiente.atualizar_robo(c, this);
            coordenada.setx(c.getx());
            coordenada.sety(c.gety());
            coordenada.setz(c.getz());
        }

    }

    /**
     * Método que retorna a posiçãoX do robô
     */
    public int getPosicaoX() {
        return coordenada.getx();
    }

    /**
     * Método que retorna a posição Y do robô
     */
    public int getPosicaoY() {
        return coordenada.gety();
    }

    /**
     * Método que retorna a posição Z do robô
     */
    public int getposicaoZ() {
        return coordenada.getz();
    }
}
