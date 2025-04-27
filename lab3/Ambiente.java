
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

class Ambiente {

    private int comprimentoX, comprimentoY, altura;
    private char[][][] ambiente;
    private ArrayList<Robo> listaRobos;

    /**
     * Função construtura que define os comprimentos iniciais do ambiente,
     * definindo onde temos obstáculos e não, de forma aleatória
     */
    public Ambiente(int x, int y, int z) {
        comprimentoX = x;
        comprimentoY = y;
        altura = z;
        ambiente = new char[x][y][z];
        listaRobos = new ArrayList<>();
        /**
         * aleatorio, pois a chance de ter um obstáculo ou é de 10%
         */
        Random aleatorio = new Random();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    if(ambiente[i][j][k] != '\u0000'){
                        continue;
                    }
                    else if (aleatorio.nextDouble() < 0.1) {
                        TipoObstaculo tipoObstaculo = TipoObstaculo.values()[aleatorio.nextInt(TipoObstaculo.values().length)];
                        Obstaculo obstaculo = new Obstaculo(i, j, k, tipoObstaculo);
                        obstaculo.introduzir_obs_ambiente(this.ambiente, this);
                    } else {
                        ambiente[i][j][k] = '*';
                    }
                }
            }
        }
        /**
         * Chance de 10 % de ter Obstáculo = X, campo livre = *
         */
    }

    public Ambiente(String arq) throws IOException {
        listaRobos = new ArrayList<>();
        carregar_o_ambiente(arq);
    }

    public void salvar_o_ambiente(String ambiente_padrao) throws IOException {
        BufferedWriter arq = new BufferedWriter(new FileWriter("ambiente.txt"));
        arq.write(comprimentoX + " " + comprimentoY + " " + altura + "\n");
        for (int k = 0; k < altura; k++) {
            for (int j = 0; j < comprimentoY; j++) {
                for (int i = 0; i < comprimentoX; i++) {
                    arq.write(ambiente[i][j][k]);
                }
                arq.write("\n");
            }
            arq.write("---\n");
        }
        arq.close();
    }

    private final void carregar_o_ambiente(String arquivo) throws IOException {
        BufferedReader arq = new BufferedReader(new FileReader("ambiente.txt"));
        String[] dimensoes = arq.readLine().split(" ");

        this.comprimentoX = Integer.parseInt(dimensoes[0]);
        this.comprimentoY = Integer.parseInt(dimensoes[1]);
        this.altura = Integer.parseInt(dimensoes[2]);
        this.ambiente = new char[comprimentoX][comprimentoY][altura];

        for (int z = 0; z < altura; z++) {
            for (int y = 0; y < comprimentoY; y++) {
                String linha = arq.readLine();
                for (int x = 0; x < comprimentoX; x++) {
                    this.ambiente[x][y][z] = linha.charAt(x);
                }
            }
            arq.readLine();
            /**
             * Para ler a separação (---)
             */
        }

        arq.close();
    }
    //TODO: implementar essa função
    private void adicionarobstaculo(){

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
        ambiente[r.getPosicaoX()][r.getPosicaoY()][(existe && r instanceof RoboAereo) ? ((RoboAereo) r).getposicaoZ() : 0] = 'r';
    }
    
    public String removerRobo(int i) {
        System.out.println(i);
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
        ambiente[listaRobos.get(i).getPosicaoX()]
                [listaRobos.get(i).getPosicaoY()]
                [(existe && listaRobos.get(i) instanceof RoboAereo) ? ((RoboAereo) listaRobos.get(i)).getposicaoZ() : 0] = '*';
        String removRob = listaRobos.get(i).toString(); 
        listaRobos.remove(i);
        return removRob;
    }

    public void getRobos() {
        System.out.println("Você tem os seguintes robôs: ");
        for (int i=0; i<listaRobos.size(); i++)
            System.out.printf("%d. %s\n",i+1, listaRobos.get(i));
    }

    public Robo getRobo(int i) {
        return listaRobos.get(i);
    }

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
     * limites possíveis
     */
    public boolean dentroDosLimites(Coordenada coordenada) {
        if (coordenada.getx() < 0 || coordenada.gety() < 0 || coordenada.getz() < 0 || coordenada.getx() > comprimentoX || coordenada.gety() > comprimentoY || coordenada.getz() > altura) {
            return false;
        } else {
            return true;
        }
    }
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
        return ambiente[coordenada.getx()][coordenada.gety()][coordenada.getz()];
    }

    /**
     * Método que retorna um boolean se há um robô
     */
    public int get_comprimentoX(){
        return comprimentoX;
    }
    public int get_comprimentoY(){
        return comprimentoY;
    }
    public int get_altura(){
        return altura;
    }


    /**
     * Método que elim ina um obstáculo do mapa
     */
    public void eliminaObstaculo(int x, int y, int z) {
        if (dentroDosLimites(x, y, z)) {
            if (ambiente[x][y][z] == 'r') {
                System.out.println("É proibibido matar outros robôs");
            } else {
                ambiente[x][y][z] = '*';
            }
        } else {
            System.out.println("Essa posiçao encontra-se fora dos limites!");
        }
    }

    /**
     * Método que atualiza a coordenada do robô
     */
    public void atualizar(Coordenada c, char s) {
        ambiente[c.getx()][c.gety()][c.getz()] = s;
    }

    /**
     * Método que retorna um boolenao se o robô Guidandaste que é responsável
     * por trocar a sua posição com um obstáculo, pode fazer tal operação
     */
    public boolean trocarObstaculo(Robo robo, Coordenada obstaculo) {
        if (dentroDosLimites(obstaculo)) {
                this.ambiente[robo.coordenada.getx()][robo.coordenada.gety()][robo.coordenada.getz()] = this.ambiente[obstaculo.getx()][obstaculo.gety()][obstaculo.getz()];
                ambiente[obstaculo.getx()][obstaculo.gety()][obstaculo.getz()] = 'r';
                return true;
            }
        
        System.out.println("Essa posiçao encontra-se fora dos limites!");
        return false;
        }


    public void print_coordenada(int x,int y,int z){
        System.out.printf("%c", this.ambiente[x][y][z]);
    }

}
