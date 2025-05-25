public class RoboInativo implements Entidade{
    protected Coordenada coordenada;

    public RoboInativo(int x, int y, int z) {
        this.coordenada = new Coordenada(x, y, z);
    }
     @Override
    public TipoEntidade getTipo(){
        return TipoEntidade.ROBO;

    }
    @Override
    public char getRepresentacao(){
        return 'r';
    }
    @Override
    public String getDescricao(){
        return "Há um robô inativo nesta posição";
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
    
