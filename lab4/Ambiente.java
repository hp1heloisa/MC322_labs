
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

class Ambiente {

    private int comprimentoX, comprimentoY, altura;
    private TipoEntidade[][][] mapa;
    private ArrayList<Entidade> entidades; //TODO: no lugar de listaRobos

    /**
     * Função construtura que define os comprimentos iniciais do ambiente,
     * definindo onde temos obstáculos e não, de forma aleatória
     */
    public Ambiente(int x, int y, int z) {
        this.comprimentoX = x;
        this.comprimentoY = y;
        this.altura = z;
        this.mapa = new TipoEntidade[x][y][z];

        inicializarMapa();
    }

    public void inicializarMapa() {
        int x = this.comprimentoX;
        int y = this.comprimentoY;
        int z = this.altura; 
        Random aleatorio = new Random();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    if (mapa[i][j][k] != null) { // Se há algum elemento, pule
                        continue;
                    } else if (aleatorio.nextDouble() < 0.1) { //Se a chance for menor do que 10 %, vamos colocar um obstáculo
                        TipoObstaculo tipoObstaculo = TipoObstaculo.values()[aleatorio.nextInt(TipoObstaculo.values().length)]; // Vamos selecionar um tipo de obstáculo
                        Entidade obstaculo = new Obstaculo(i, j, k, tipoObstaculo); //adicionar um obstáculo aleatorio ao ambiente
                        entidades.add(obstaculo);
                        // atualizar mapa
                        mapa[i][j][k] = TipoEntidade.OBSTACULO;                                         
                    } else {
                        mapa[i][j][k] = TipoEntidade.VAZIO;
                    }
                }
            }
        }
    }

    /**
     * Método construtor utilizado quando queremos usar um ambiente pré-definido
     */
    public Ambiente(String arq) throws IOException {
        entidades = new ArrayList<>();
        carregar_o_ambiente(arq);
    }

    private char getRepresentacaoEntidade(TipoEntidade tipo, int x, int y, int z) {
        if (tipo == TipoEntidade.OBSTACULO){
            for (Entidade e : entidades) {
                if (e.getTipo() == TipoEntidade.OBSTACULO && e.getX()==x && e.getY()==y && e.getZ()==z)
                    return e.getRepresentacao();
            }
            return '?';
        }
        switch (tipo) {
            case ROBO:
                return 'r';
            case VAZIO:
                return '*';
            default:
                return '?';
        }
    }

    /**
     * Método que escreve o ambiente em um arquivo txt, para uso futuro.
     */
    public void salvar_o_ambiente(String ambiente_padrao) throws IOException {
        BufferedWriter arq = new BufferedWriter(new FileWriter("ambiente.txt"));
        arq.write(comprimentoX + " " + comprimentoY + " " + altura + "\n");
        for (int k = 0; k < altura; k++) {
            for (int j = 0; j < comprimentoY; j++) {
                for (int i = 0; i < comprimentoX; i++) {
                    arq.write(getRepresentacaoEntidade(mapa[i][j][k], i, j, k));
                }
                arq.write("\n");
            }
            arq.write("---\n");
        }
        arq.close();
    }

    /**
     * Método que carrega o ambiente a partir do arquivo txt
     */
    private final void carregar_o_ambiente(String arquivo) throws IOException {
        BufferedReader arq = new BufferedReader(new FileReader("ambiente.txt"));
        String[] dimensoes = arq.readLine().split(" ");

        this.comprimentoX = Integer.parseInt(dimensoes[0]);
        this.comprimentoY = Integer.parseInt(dimensoes[1]);
        this.altura = Integer.parseInt(dimensoes[2]);
        this.mapa = new TipoEntidade[comprimentoX][comprimentoY][altura];

        for (int z = 0; z < altura; z++) {
            for (int y = 0; y < comprimentoY; y++) {
                String linha = arq.readLine();
                for (int x = 0; x < comprimentoX; x++) {
                    char caractere = linha.charAt(x);
                    if (caractere == '*'){
                        mapa[x][y][z] = TipoEntidade.VAZIO;
                    }
                    else{
                        TipoObstaculo obst = TipoObstaculo.busca_inicial(caractere);
                        Entidade obstaculo = new Obstaculo(x, y, z, obst); //adicionar um obstáculo aleatorio ao ambiente
                        entidades.add(obstaculo);
                        // atualizar mapa
                        mapa[x][y][z] = TipoEntidade.OBSTACULO;       
                    
                    }
                }
            }
            arq.readLine();
            /**
             * Para ler a separação (---)
             */
        }

        arq.close();
    }

    /**
     * Método que adiciona entidades ao mapa
     */
    public void adicionarEntidade(Entidade e) { //TODO: no lugar de adicionaRobo
        if (!dentroDosLimites(new Coordenada(e.getX(), e.getY(), e.getZ()))){ //verifica se a entidade está fora dos limites 
            throw new IllegalArgumentException("Fora dos limites!");
        }
        entidades.add(e); //caso esteja nos limites adicionamos a lista e ao ambiente
        mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
    }

    /**
     * Método que remove uma entidade
     */
    public String removerEntidade(Entidade e) { //TODO: no lugar de removerRobo       
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
        String removEnt = e.getDescricao();
        entidades.remove(e);
        return removEnt;
    }

    /**
     * Método que imprime os tipos de robôs que possuímos
     */
    public void getRobos() {
        System.out.println("Você tem os seguintes robôs: ");
        int quant = 1;
        for (Entidade ent : entidades){
            if (ent instanceof Robo)
                System.out.printf("%d. %s\n", quant++, ent);

        }
    }

    /**
     * Método que seleciona um robô específico da nossa lista de robôs
     */
    public Entidade getRobo(int i) {
        int quant = 0;
        for (Entidade ent : entidades) {
            if (ent instanceof Robo) {
                if (quant == i) {
                    return ent;
                }
                quant++;
            }
        }
        throw new IndexOutOfBoundsException("Não existe robô na posição " + i);
    }

    /**
     * Método que retorna a posição do nosso robô dentro da lista de robôs
     */
    public int getIndexOfRobo(String robo) {
        int index = -1;
        int quant = 0;
        for (Entidade ent : entidades){
            if (ent instanceof Robo)
                if (ent.toString().equals(robo)) {
                    index = quant;
                    break;
                }
                quant++;
        }
        return index;
    }

    /**
     * Método que retorna um booleano se a coordenada, passada como parâmetro,
     * está dentro dos limites possíveis.
     */
    public boolean dentroDosLimites(Coordenada coordenada) {
        if (coordenada.getx() < 0 || coordenada.gety() < 0 || coordenada.getz() < 0 || coordenada.getx() > comprimentoX || coordenada.gety() > comprimentoY || coordenada.getz() > altura) {
            return false;
        } else {
            return true;
        }
    }

     /**
     * Método que retorna um booleano em relação se a coordenada está
     * ocupada ou não.
     */
    public boolean estaOcupado(Coordenada coordenada) {
        return mapa[coordenada.getx()][coordenada.gety()][coordenada.getz()] != TipoEntidade.VAZIO;
    }

    /**
     * Método que que verifica se a nova posicao de entidade está dentro dos limites,
     * se estiver move para a nova posição
     */
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws ColisaoException{
        if (!dentroDosLimites(new Coordenada(novoX, novoY, novoZ))){
            throw new ColisaoException("Coordenada fora dos limites!");
        }
        if (estaOcupado(new Coordenada(novoX, novoY, novoZ))){
            throw new ColisaoException("Espaço já ocupado, você tem que procurar outro lugar!");
        }
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
        e.setX(novoX);
        e.setY(novoY);
        e.setZ(novoZ);
        mapa[novoX][novoY][novoZ] = e.getTipo();
    }


    /**
     * Método que retorna a entidade na coordenada
     */
    public char getElemento(Coordenada coordenada) {
        TipoEntidade tipo = mapa[coordenada.getx()][coordenada.gety()][coordenada.getz()];
        return getRepresentacaoEntidade(tipo, coordenada.getx(), coordenada.gety(), coordenada.getz());
    }

    public Entidade getEntidade(Coordenada coordenada) {
        for (Entidade ent : entidades) {
            if (ent.getX() == coordenada.getx() && ent.getY() == coordenada.gety() && ent.getZ() == coordenada.getz()) {
                return ent;
            }
        }
        return null;
    }

    /**
     * Método que retorna o comprimento X do ambiente
     */
    public int get_comprimentoX() {
        return comprimentoX;
    }

    /**
     * Método que retorna o comprimento Y do ambiente
     */
    public int get_comprimentoY() {
        return comprimentoY;
    }

    /**
     * Método que retorna a altura do ambiente
     */
    public int get_altura() {
        return altura;
    }

    /**
     * Método que elimina um obstáculo do mapa
     */
    public void eliminaObstaculo(int x, int y, int z) {
        if (dentroDosLimites(new Coordenada(x, y, z))) {
            if (mapa[x][y][z] == TipoEntidade.ROBO) {
                System.out.println("É proibido matar outros robôs");
            } else {
                mapa[x][y][z] = TipoEntidade.VAZIO;
            }
        } else {
            System.out.println("Essa posiçao encontra-se fora dos limites!");
        }
    }

    /**
     * Método que atualiza a coordenada do robô
     */
    public void atualizar_espaco_vazio(Coordenada c) {
        mapa[c.getx()][c.gety()][c.getz()] = TipoEntidade.VAZIO;
    }

    public void atualizar_robo(Coordenada c, Entidade e) {
        mapa[c.getx()][c.gety()][c.getz()] = TipoEntidade.ROBO;
        e.setX(c.getx());
        e.setY(c.gety());
        e.setZ(c.getz());
    }

    /**
     * Método que retorna um boolenao se o robô Guidandaste que é responsável
     * por trocar a sua posição com um obstáculo, pode fazer tal operação
     */
    public boolean trocarObstaculo(Entidade e, Entidade obstaculo) {
        int obs_x = obstaculo.getX();
        int obs_y = obstaculo.getY();
        int obs_z = obstaculo.getZ();

        int rob_x = e.getX();
        int rob_y = e.getY();
        int rob_z = e.getZ();

        if (dentroDosLimites(new Coordenada(obs_x, obs_y, obs_z))) {
            if (mapa[obs_x][obs_y][obs_z] == TipoEntidade.ROBO) {
                System.out.println("Não é possível mover outro robô!");
                return false;
            }

            Entidade entidadePos = null;
            for (Entidade ent : entidades) {
                if (ent.getX() == obs_x && ent.getY() == obs_y && ent.getZ() == obs_z) {
                    entidadePos = ent;
                    break;
                }
            }

            if (entidadePos != null && entidadePos.getTipo() == TipoEntidade.OBSTACULO) {
                // Atualizar mapa
                mapa[rob_x][rob_y][rob_z] = TipoEntidade.OBSTACULO;
                obstaculo.setX(rob_x);
                obstaculo.setY(rob_y);
                obstaculo.setZ(rob_z);

                // Mover robô
                mapa[obs_x][obs_y][obs_z] = TipoEntidade.ROBO;
                e.setX(obs_x);
                e.setY(obs_y);
                e.setZ(obs_z);

                System.out.println("Obstáculo e robô movidos com sucesso!");
                return true;
            }

            System.out.println("Ops! O guindaste acabou não encontrando nenhum obstáculo.");
            return false;

        }

        System.out.println("Essa posição encontra-se fora dos limites!");
        return false;
    }

    /**
     * Método que printa uma coordenada
     */
    public void print_coordenada(int x, int y, int z) {
        System.out.printf("%c", this.mapa[x][y][z]);
    }

}
