public class SensorPlano extends Sensor{
    public SensorPlano(int r, Ambiente ambiente){
        super(r, ambiente);
    }
    public void identificarArea(Coordenada coordenada) {
        System.out.printf("Obstáculos identificados em um raio de %dm: \n", super.raio);
        for (int y = super.raio; y > -super.raio; y--) {
            if (coordenada.gety() + y < 0) {
                continue;
            }
            for (int x = -super.raio; x < super.raio; x++) {
                if (coordenada.getx() + x < 0) {
                    continue;
                }
                if (ambiente.dentroDosLimites(coordenada.getx() + x, coordenada.gety() + y, coordenada.getz())) {
                    ambiente.print_coordenada(coordenada.getx()+x, coordenada.gety() + y, coordenada.getz());
                } 
            }
            System.out.println("");
        }
    }
}
