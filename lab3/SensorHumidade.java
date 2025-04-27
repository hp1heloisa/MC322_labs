public class SensorHumidade extends Sensor {
    protected int altitude;
    public SensorHumidade(int r, int h, Ambiente ambiente){
        super(r, ambiente);
        altitude = h;
    }

    public void setAltitude(int h) {
        altitude = h;
    }

    public double calcula_humidade(Coordenada coordenada) {
       double humidade = 0;
       int z = altitude;
        do {
            for (int y = super.raio; y > -super.raio; y--) {
                if (coordenada.gety() + y < 0) {
                    continue;
                }
                for (int x = -super.raio; x < super.raio; x++) {
                    if (coordenada.getx() + x < 0) {
                        continue;
                    }
                    if (ambiente.dentroDosLimites(coordenada.getx() + x, coordenada.gety() + y, coordenada.getz() + z)) {
                        char elemento = ambiente.getElemento(coordenada.getx()+x, coordenada.gety() + y, coordenada.getz() + z);
                        double elem_hum = 0;
                        if (elemento=='A'){
                            elem_hum = TipoObstaculo.AGUA.get_humidade();
                        } else if (elemento=='F'){
                            elem_hum = TipoObstaculo.FOGO.get_humidade();
                        } else if (elemento=='P'){
                            elem_hum = TipoObstaculo.PAREDE.get_humidade();
                        }else if (elemento=='V'){
                            elem_hum = TipoObstaculo.ARBUSTO.get_humidade();
                        }
                        if (Math.sqrt(x*x + y*y + z*z) > 0) humidade += elem_hum/(Math.sqrt(x*x + y*y + z*z));
                        else humidade += elem_hum;
                    } 
                }
            }
            z--;
        } while (z >= -altitude);
        System.out.printf("Humidade atual: %.2f%%. Preste atenção nos A ao longo do caminho.\n", humidade );
        return humidade;
    }
}
