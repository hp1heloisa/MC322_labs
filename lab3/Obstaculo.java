
public class Obstaculo {

    int posicaoX, posicaoY1, posicaoZ1;
    private TipoObstaculo tipo;

    public Obstaculo(int x, int y1, int z1, TipoObstaculo tipo) {
        this.posicaoX = x;
        this.posicaoY1 = y1;
        this.posicaoZ1 = z1;
        this.tipo = tipo;
    }

    public void introduzir_obs_ambiente(char[][][] matriz_ambiente, Ambiente ambiente) {
        for (int i = this.posicaoZ1; i < this.posicaoZ1 + tipo.get_altura() && i < ambiente.get_altura(); i++) {
            for (int j = this.posicaoY1; j < this.posicaoY1 + tipo.get_comprimento() && j < ambiente.get_comprimentoY() ; j++) {
                if (matriz_ambiente[this.posicaoX][j][i] == '\u0000' || matriz_ambiente[this.posicaoX][j][i] == '*') {
                    matriz_ambiente[this.posicaoX][j][i] = this.tipo.get_inicial();
                    System.out.printf("Caractere: %c\n", this.tipo.get_inicial());
                }
            }
        }
    }

}
