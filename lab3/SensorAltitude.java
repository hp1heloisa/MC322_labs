public class SensorAltitude extends Sensor{
    public SensorAltitude(int r, Ambiente ambiente){
        super(r, ambiente);
    }
    public void identificarArea(Coordenada coordenada) {
        int min =  coordenada.getz() - super.raio;
        int max = coordenada.getz() + super.raio;
        if (min<0) min=0;
        if (max > ambiente.get_altura()) max = ambiente.get_altura();
        System.out.printf("Obstáculos identificados nas altitudes de %dm a %dm: \n", min, max);
        for (int z = super.raio; z > -super.raio; z--) {
            if (ambiente.dentroDosLimites(coordenada.getx(), coordenada.gety(), coordenada.getz() + z)) {
                ambiente.print_coordenada(coordenada.getx(), coordenada.gety() , coordenada.getz() + z);
                System.out.println("");
            } 
        }
    }
}

