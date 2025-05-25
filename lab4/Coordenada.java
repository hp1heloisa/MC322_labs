
public class Coordenada {

    private int x, y, z;

    //*Função construtora para definirmos as coordenadas iniciais do robô.*/
    public Coordenada(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Método para printar as coordenadas x, y, z; em uma espécie de "tupla"
     */
    @Override
    public String toString() {
        return String.format("(%d, %d, %d)",    x, y, z);
    }

    /**
     * Método get para retornar a pos x
     */
    public int getx() {
        return x;
    }

    /**
     * Método get para retornar a pos y
     */
    public int gety() {
        return y;
    }

    /**
     * Método get para retornar a pos z
     */
    public int getz() {
        return z;
    }

    /**
     * Método set para retornar a pos x
     */
    public void setx(int x){
        this.x = x;
    }
    /**
     * Método set para retornar a pos y
     */
    public void sety(int y){
        this.y = y;
    }
    /**
     * Método set para retornar a pos z
     */
    public void setz(int z){
        this.z = z;
    }
}
