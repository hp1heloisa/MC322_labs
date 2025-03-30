
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
                    ambiente[i][j][k] = (aleatorio.nextDouble() < 0.1) ? 'X' : '*';

                }
            }
        }
        /**
         * Chance de 10 % de ter Obstáculo = X, campo livre = *
         */
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
        ambiente[r.posicaoX][r.posicaoY][(existe && r instanceof RoboAereo) ? ((RoboAereo) r).getposicaoZ() : 0] = 'r';
    }

    /**
     * Método que retorna um booleano se a nova posição do robô está dentro dos
     * limites possíveis
     */
    public boolean dentroDosLimites(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x > comprimentoX || y > comprimentoY || z > altura) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Método que retorna um boolean se há um obstáculo
     */
    public boolean tem_obstaculo(int x, int y, int z) {
        return ambiente[x][y][z] == 'X' ? true : false;
    }

    /**
     * Método que retorna um boolean se há um robô
     */
    public boolean tem_robo(int x, int y, int z) {
        return ambiente[x][y][z] == 'r' ? true : false;
    }

    /**
     * Método que elim ina um obstáculo do mapa
     */
    public void eliminaObstaculo(int x, int y, int z) {
        if (dentroDosLimites(x, y, z)) {
            if (tem_robo(x, y, z)) {
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
    public boolean trocarObstaculo(Coordenada robo, Coordenada obstaculo) {
        if (dentroDosLimites(obstaculo.getx(), obstaculo.gety(), obstaculo.getz())) {
            if (tem_robo(obstaculo.getx(), obstaculo.gety(), obstaculo.getz())) {
                System.out.println("Não é possível mover outros robôs, seja educado e peça licença!");
                return false;
            } else {
                ambiente[robo.getx()][robo.gety()][robo.getz()] = 'X';
                ambiente[obstaculo.getx()][obstaculo.gety()][obstaculo.getz()] = 'r';
                return true;
            }
        } else {
            System.out.println("Essa posiçao encontra-se fora dos limites!");
            return false;
        }
    }

}
