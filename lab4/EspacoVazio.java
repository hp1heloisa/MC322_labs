public class EspacoVazio implements Entidade{
    protected  Coordenada coordenada;
    public EspacoVazio(int x, int y, int z){
        this.coordenada = new Coordenada(x, y, z);
    }
    @Override
    public TipoEntidade getTipo(){
        return TipoEntidade.VAZIO;

    }
    @Override
    public char getRepresentacao(){
        return '*';
    }
    @Override
    public String getDescricao(){
        return "Não há nenhum obstáculo nesta posição";
    }
    @Override
    public int getX(){
        return coordenada.getx();
    }
    @Override
    public int getY(){
        return coordenada.gety();
    }
    @Override
    public int getZ(){
        return coordenada.getz();
    }
}