package simulador.robo;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.CentralComunicacao;
import simulador.ambiente.Coordenada;
import simulador.ambiente.TipoEntidade;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.exceptions.RoboDesligadoException;
import simulador.interfaces.Entidade;
import simulador.interfaces.InterfaceRobo;
import simulador.sensores.SensorPlano;
import simulador.sensores.SensorTemperatura;
import simulador.sensores.SensorUmidade;
import simulador.exceptions.EnergiaInsuficienteException;
import simulador.interfaces.Comunicavel;

public abstract class Robo implements InterfaceRobo {

    protected String nome;
    protected Coordenada coordenada;
    protected Scanner scanner;
    protected Ambiente ambiente;
    protected EstadoRobo estado;
    protected SensorPlano sensorPlano;
    protected SensorTemperatura sensorTemperatura;
    protected SensorUmidade sensorHumidade;
    private static int contadorId = 0;
    protected final int id;
    protected String mensagemPadrao;
    private int nivelBateria = 100;

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
        System.out.printf("O nome do seu robô é robo_%d ", listaRobos.size());
        nome = "robo" + listaRobos.size();
        System.out.printf("Aviso: Nós começaremos com o seu robô na origem do eixo de coordenadas(X = Y = Z = 0)\n");
        this.coordenada = new Coordenada(0, 0, 0);
        this.estado = estado;
        this.id = ++contadorId;

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
    public void setX(int x) {
        coordenada.setx(x);
    }

    @Override
    public void setY(int y) {
        coordenada.sety(y);
    }

    @Override
    public void setZ(int z) {
        coordenada.setz(z);
    }

    @Override
    public void acionarSensores() throws RoboDesligadoException {
        if (this.estado == EstadoRobo.desligado) {
            throw new RoboDesligadoException(this.getNome() + "está desligado, só pode usar o sensor de plano!");
        }
    }

    @Override
    public void recarregar() {
        this.nivelBateria = 100;
        System.out.println("Bateria recarregada!");
    }

    @Override
    public void consumirEnergia(int quantidade) throws EnergiaInsuficienteException {
        if (nivelBateria < quantidade) {
            throw new EnergiaInsuficienteException("Energia insuficiente! Nível atual: " + nivelBateria);
        }
        nivelBateria -= quantidade;
        System.out.printf("\n%d%% de bateria\n", nivelBateria);
        if (nivelBateria < 30)
            System.out.println("Sua bateria está baixa! Procure uma oficina para recarregar!");
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    /**
     * Método para quando um robô estiver pelas proximidades saber o que outros robôs estão pensando
     */
    @Override
    public String getMensagemPadrao() throws RoboDesligadoException {
        if (getEstado() == EstadoRobo.desligado)
            throw new RoboDesligadoException(getNome() + " está desligado e você não pode saber o que ele tem a dizer!");
        return mensagemPadrao;
    }

     /**
     * Método para setar uma mensagem de um robô como padrão
     */
    @Override
    public void setMensagemPadrao(String msg) throws RoboDesligadoException {
        if (getEstado() == EstadoRobo.desligado)
            throw new RoboDesligadoException(getNome() + " está desligado e não pode ter sua mensagem padrão atualizada!"); 
        this.mensagemPadrao = msg;
    }

    /**
     * Método para que o robô possa mandar mensagens para outros robôs
     */
    @Override
    public String enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        CentralComunicacao.getComunicacao().registrarMensagemEnviada(nome, destinatario.getNome(), mensagem);
        if (destinatario.getEstado() == EstadoRobo.desligado)
            throw new RoboDesligadoException(destinatario.getNome() + " está desligado e não pode receber sua mensagem!");
        else {
            CentralComunicacao.getComunicacao().registrarMensagemRecebida(nome, destinatario.getNome(), mensagem);
        }
        String text = String.format("%s para %s: %s\n", nome, destinatario.getNome(), mensagem);
        System.out.println(text);
        return text;
    }

    /**
     * Método para que o robô possa receber mensagens de outros robôs
     */
    @Override
    public void receberMensagem(Comunicavel remetente, String mensagem) throws RoboDesligadoException {
        String remetente_nome = remetente.getNome();

        CentralComunicacao.getComunicacao().registrarMensagemEnviada(remetente_nome, nome, mensagem);
        if (getEstado() == EstadoRobo.desligado)
            throw new RoboDesligadoException("Você está desligado e não pode receber sua mensagens!");
        
        CentralComunicacao.getComunicacao().registrarMensagemRecebida(remetente_nome, nome, mensagem);
        String text = String.format("%s para %s: %s\n", remetente_nome, nome, mensagem);
        System.out.println(text);
    }

    /**
     * Método para o robô escanear em raio de 5m e detectar algum sinal de outros robôs ou do ambiente
     */
    @Override 
    public void receberMensagensDoAmbiente() throws RoboDesligadoException {
        if (getEstado() == EstadoRobo.desligado)
            throw new RoboDesligadoException(getNome() + " está desligado e não pode receber mensagens!");
        
        int raio = 5;
        System.out.println("Procurando por sinal de vida...");
        List<String> mensagens = new ArrayList<>(); // Lista criada para armezenar as mensagens captadas pelo robô
        
        PrintStream originalOut = System.out;

        try{
            System.setOut(new PrintStream(OutputStream.nullOutputStream())); //Silenciando os prints ao pegar informações dos robôs
            for (int dx = -raio; dx <= raio; dx++) {
                for (int dy = -raio; dy <= raio; dy++) {
                    for (int dz = -raio; dz <= raio; dz++) {
                        int nx = getX() + dx;
                        int ny = getY() + dy;
                        int nz = getZ() + dz;

                        if (!ambiente.dentroDosLimites(new Coordenada(nx, ny, nz))) continue;

                        Entidade e = ambiente.getEntidade(new Coordenada(nx, ny, nz));

                        if (e != null && e instanceof Comunicavel && e != this) {
                            Comunicavel outroRobo = (Comunicavel) e;

                            if (outroRobo.getEstado() == EstadoRobo.desligado) continue;

                            CentralComunicacao.getComunicacao().registrarMensagemRecebida(outroRobo.getNome(), this.getNome(), outroRobo.getMensagemPadrao());

                            mensagens.add(String.format("Ouvindo mensagem do %s: \"%s\"", outroRobo.getNome(), outroRobo.getMensagemPadrao()));
                        }
                    }
                }
            }
        } finally {
            System.setOut(originalOut);
        }
       
        for (String msg : mensagens){
            System.out.println(msg);
        }
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
    protected void mover(int deltaX, int deltaY) throws ForadosLimitesException, EnergiaInsuficienteException {
        Coordenada c_0 = new Coordenada(coordenada.getx(), coordenada.gety(), coordenada.getz()); // Coordenada inicial do robô
        Coordenada nova_pos = new Coordenada(coordenada.getx() + deltaX, coordenada.gety() + deltaY, coordenada.getz()); // Nova coordenada do robô

        consumirEnergia(2);

        if (ambiente.dentroDosLimites(nova_pos)) {
            if (sensorPlano.tem_obstaculo(nova_pos)) {
                System.out.printf("Há um obstáculo do tipo %s na posição: %s\n", sensorPlano.mostrar_obstaculo(nova_pos), nova_pos);
                if (sensorPlano.mostrar_obstaculo(nova_pos) == "Oficina")
                    recarregar();
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

    /**
     * Método que retorna o estado do robô
     */
    public EstadoRobo getEstado() {
        System.out.printf("O seu robo está %s.\n", estado);
        return estado;
    }

    /**
     * Método que retorna o id do robô
     */
    public int getId() {
        System.out.printf("O id do seu robo é: %d\n", id);
        return id;
    }

    @Override
    public String toString() {
        return nome + " - " + getClass().getSimpleName();
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

    public void ligar(Coordenada coordenada, Ambiente ambiente) {
        if (ambiente.mapa[coordenada.getx()][coordenada.gety()][coordenada.getz()] == TipoEntidade.ROBO) {
            Entidade ent = ambiente.getEntidade(coordenada);
            Robo Robo_ligado = (Robo) ent;
            Robo_ligado.estado = EstadoRobo.ligado;
        }
    }

    public void desligar(Coordenada coordenada, Ambiente ambiente) {
        if (ambiente.mapa[coordenada.getx()][coordenada.gety()][coordenada.getz()] == TipoEntidade.ROBO) {
            Entidade ent = ambiente.getEntidade(coordenada);
            Robo Robo_ligado = (Robo) ent;
            Robo_ligado.estado = EstadoRobo.desligado;
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
