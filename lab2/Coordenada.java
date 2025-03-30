public class Coordenada {
    private int x, y, z;
    
    //Função construtora para definirmos as coordenadas iniciais do robô.
    public Coordenada(int x, int y, int z) { 
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {  // Método para printar as coordenadas x, y, z; em uma espécie de "tupla"
        return String.format("(%d, %d, %d)", x,y,z);
    }

    public int x() {  // Método get para retornar a pos x
        return x;
    }
    public int y() {    // Método get para retornar a pos y
        return y;
    }
    public int z() {    // Método get para retornar a pos z
        return z;
    }
}
