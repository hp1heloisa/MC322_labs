
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

class Ambiente {

    private int comprimentoX, comprimentoY, altura;
    private Entidade[][][] mapa;
    private ArrayList<Robo> listaRobos;

    /**
     * Função construtura que define os comprimentos iniciais do ambiente,
     * definindo onde temos obstáculos e não, de forma aleatória 
     */
    public Ambiente(int x, int y, int z) {
        comprimentoX = x;
        comprimentoY = y;
        altura = z;
        mapa = new Entidade[x][y][z];
        listaRobos = new ArrayList<>();

        Random aleatorio = new Random();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    if(mapa[i][j][k] != null){ // Se há algum elemento, pule
                        continue;
                    }
                    else if (aleatorio.nextDouble() < 0.1) { //Se a chance for menor do que 10 %, vamos colocar um obstáculo
                        TipoObstaculo tipoObstaculo = TipoObstaculo.values()[aleatorio.nextInt(TipoObstaculo.values().length)]; // Vamos selecionar um tipo de obstáculo
                        mapa[i][j][k] = new Obstaculo(i, j, k, tipoObstaculo);                                            //aleatoriamente
                    } else {
                        mapa[i][j][k] = new EspacoVazio(i, j, k);
                    }
                }
            }
        }
    }
    /**Método construtor utilizado quando queremos usar um ambiente pré-definido*/ 
    public Ambiente(String arq) throws IOException {
        listaRobos = new ArrayList<>();
        carregar_o_ambiente(arq);
    }
    /**Método que escreve o ambiente em um arquivo txt, para uso futuro. */
    public void salvar_o_ambiente(String ambiente_padrao) throws IOException {
        BufferedWriter arq = new BufferedWriter(new FileWriter("ambiente.txt"));
        arq.write(comprimentoX + " " + comprimentoY + " " + altura + "\n");
        for (int k = 0; k < altura; k++) {
            for (int j = 0; j < comprimentoY; j++) {
                for (int i = 0; i < comprimentoX; i++) {
                    arq.write(mapa[i][j][k].getRepresentacao());
                }
                arq.write("\n");
            }
            arq.write("---\n");
        }
        arq.close();
    }
    /**Método que carrega o ambiente a partir do arquivo txt */
    private final void carregar_o_ambiente(String arquivo) throws IOException {
        BufferedReader arq = new BufferedReader(new FileReader("ambiente.txt"));
        String[] dimensoes = arq.readLine().split(" ");

        this.comprimentoX = Integer.parseInt(dimensoes[0]);
        this.comprimentoY = Integer.parseInt(dimensoes[1]);
        this.altura = Integer.parseInt(dimensoes[2]);
        this.mapa = new Entidade[comprimentoX][comprimentoY][altura];

        for (int z = 0; z < altura; z++) {
            for (int y = 0; y < comprimentoY; y++) {
                String linha = arq.readLine();
                for (int x = 0; x < comprimentoX; x++) {
                    char caractere = linha.charAt(x);
                    switch (caractere){
                        case '*':
                            mapa[x][y][z] = new EspacoVazio(x, y, z);
                    default:
                        TipoObstaculo obst = TipoObstaculo.busca_inicial(caractere);
                        mapa[x][y][z] = new Obstaculo(x, y, z, obst);
                        break;
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
     * Método que adicionar os robôs no mapa
     */
    public void adicionarRobo(Robo r) {
        listaRobos.add(r);
        Class<?> classe = r.getClass(); // Obtém a classe
        boolean existe = false;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.getName().equals("posicaoZ")) {
                /**
                 * Se a classe do nosso robô possui posição z, então a pos z
                 * existe
                 */
                existe = true;
                break;
            }
        }
        mapa[r.getPosicaoX()][r.getPosicaoY()][(existe && r instanceof RoboAereo) ? ((RoboAereo) r).getposicaoZ() : 0] = new RoboInativo(r.getPosicaoX(), r.getPosicaoY(), r.getposicaoZ());
    }
    /**Método que remove um robô */
    public String removerRobo(int i) {
        Class<?> classe = listaRobos.get(i).getClass(); // Obtém a classe
        boolean existe = false;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.getName().equals("posicaoZ")) {
                /**
                 * Se a classe do nosso robô possui posição z, então a pos z
                 * existe
                 */
                existe = true;
                break;
            }
        }
        mapa[listaRobos.get(i).getPosicaoX()]
                [listaRobos.get(i).getPosicaoY()]
                [(existe && listaRobos.get(i) instanceof RoboAereo) ? ((RoboAereo) listaRobos.get(i)).getposicaoZ() : 0] = new EspacoVazio(listaRobos.get(i).getPosicaoX(), listaRobos.get(i).getPosicaoY() , (existe && listaRobos.get(i) instanceof RoboAereo) ? ((RoboAereo) listaRobos.get(i)).getposicaoZ() : 0);
        String removRob = listaRobos.get(i).toString(); 
        listaRobos.remove(i);
        return removRob;
    }
    /**Método que imprime os tipos de robôs que possuímos */
    public void getRobos() {
        System.out.println("Você tem os seguintes robôs: ");
        for (int i=0; i<listaRobos.size(); i++)
            System.out.printf("%d. %s\n",i+1, listaRobos.get(i));
    }
    /**Método que seleciona um robô específico da nossa lista de robôs */
    public Robo getRobo(int i) {
        return listaRobos.get(i);
    }
    /**Método que retorna a posição do nosso robô dentro da lista de robôs */
    public int getIndexOfRobo(String robo){
        int index = -1;
        for (int i=0; i<listaRobos.size(); i++) {
            if (listaRobos.get(i).toString().equals(robo)){
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * Método que retorna um booleano se a nova posição do robô está dentro dos
     * limites possíveis usando como parâmetro a classe coordenada do robô
     */
    public boolean dentroDosLimites(Coordenada coordenada) {
        if (coordenada.getx() < 0 || coordenada.gety() < 0 || coordenada.getz() < 0 || coordenada.getx() > comprimentoX || coordenada.gety() > comprimentoY || coordenada.getz() > altura) {
            return false;
        } else {
            return true;
        }
    }

    /**Método que retorna um booleano se a nova posição do robô está dentro dos
     * limites possíveis limites possíveis usando como parâmetro os inteiros de coordenada do robô */
    public boolean dentroDosLimites(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x > comprimentoX || y > comprimentoY || z > altura) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Método que retorna um boolean se há um obstáculo
     */

    public char getElemento(Coordenada coordenada) {
        return mapa[coordenada.getx()][coordenada.gety()][coordenada.getz()].getRepresentacao();
    }

    /**
     * Método que retorna o comprimento X do ambiente
     */
    public int get_comprimentoX(){
        return comprimentoX;
    }
    /**Método que retorna o comprimento Y do ambiente */
    public int get_comprimentoY(){
        return comprimentoY;
    }
    /**Método que retorna a altura do ambiente */
    public int get_altura(){
        return altura;
    }


    /**
     * Método que elimina um obstáculo do mapa
     */
    public void eliminaObstaculo(int x, int y, int z) {
        if (dentroDosLimites(x, y, z)) {
            if (mapa[x][y][z].getRepresentacao() == 'r') {
                System.out.println("É proibido matar outros robôs");
            } else {
                mapa[x][y][z] = new EspacoVazio(x,y,z);
            }
        } else {
            System.out.println("Essa posiçao encontra-se fora dos limites!");
        }
    }

    /**
     * Método que atualiza a coordenada do robô
     */
    public void atualizar_espaco_vazio(Coordenada c) {
        mapa[c.getx()][c.gety()][c.getz()] = new EspacoVazio(c.getx(), c.gety(), c.gety());
    }
    public void atualizar_robo(Coordenada c, Robo robo){
         mapa[c.getx()][c.gety()][c.getz()] = robo;
    }

    /**
     * Método que retorna um boolenao se o robô Guidandaste que é responsável
     * por trocar a sua posição com um obstáculo, pode fazer tal operação
     */
    public boolean trocarObstaculo(Robo robo, Coordenada obstaculo) {
        if (dentroDosLimites(obstaculo)) {
               if (mapa[obstaculo.getx()][obstaculo.gety()][obstaculo.getz()].getRepresentacao() == 'r'){
                System.out.println("Não é possível mover outro robô!");
                return false;
               }
               for(TipoObstaculo obs : TipoObstaculo.values()){
                    if(obs.get_inicial() == this.getElemento(obstaculo)){
                        this.mapa[robo.coordenada.getx()][robo.coordenada.gety()][robo.coordenada.getz()] = this.mapa[obstaculo.getx()][obstaculo.gety()][obstaculo.getz()];
                        atualizar_robo(obstaculo, robo);
                        System.out.printf("%s movido(a) com sucesso!\n", obs.getDescricao());
                        return true;
                    }
               }

               System.out.println("Ops! O guindaste acabou não encontrando nenhum obstáculo.");
               return false;
               
        }
        
        System.out.println("Essa posição encontra-se fora dos limites!");
        return false;
        }

    /**Método que printa uma coordenada */
    public void print_coordenada(int x,int y,int z){
        System.out.printf("%c", this.mapa[x][y][z].getRepresentacao());
    }

}
