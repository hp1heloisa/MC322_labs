public class Coordenada {
    private int x, y, z;

    public Coordenada(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", x,y,z);
    }

    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
    public int z() {
        return z;
    }
}
