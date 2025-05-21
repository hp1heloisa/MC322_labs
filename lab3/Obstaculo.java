
public class Obstaculo implements Entidade{

    int posicaoX, posicaoY1, posicaoZ1;
    private TipoObstaculo tipo;

    /**Função construtora que define as características do obstáculo */
    public Obstaculo(int x, int y1, int z1, TipoObstaculo tipo) {
        this.posicaoX = x;
        this.posicaoY1 = y1;
        this.posicaoZ1 = z1;
        this.tipo = tipo;
    }
    
    /**Método que é responsável por colocar o obstáculo no ambiente */
    public void introduzir_obs_ambiente(char[][][] matriz_ambiente, Ambiente ambiente) {
        for (int i = this.posicaoZ1; i < this.posicaoZ1 + tipo.get_altura() && i < ambiente.get_altura(); i++) {
            for (int j = this.posicaoY1; j < this.posicaoY1 + tipo.get_comprimento() && j < ambiente.get_comprimentoY() ; j++) {
                if (matriz_ambiente[this.posicaoX][j][i] == '\u0000' || matriz_ambiente[this.posicaoX][j][i] == '*') {
                    matriz_ambiente[this.posicaoX][j][i] = this.tipo.get_inicial();
                }
            }
        }
    }
    public TipoObstaculo getTipoObstaculo(){
        return tipo;
    }
    @Override
    public TipoEntidade getTipo(){
        return TipoEntidade.OBSTACULO;
    }



}

//TODO: se o robor não tiver sensor de temperatura vai tentar passar pelo fogo e morrer queimado
//TODO: se não tiver sensor de umidade vai passar pela agua e morrer de curto
//TODO: pra passar pela mata tem que cortar
//TODO: predio explodir 