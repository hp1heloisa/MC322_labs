

public class SensorUmidade extends Sensor {
    protected int altitude;
    public SensorUmidade(int r, int h, Ambiente ambiente){
        super(r, ambiente);
        altitude = h;
    }

    public void setAltitude(int h) {
        altitude = h;
    }

    public double calcula_umidade(Coordenada coordenada) {
       double umidade = 0;
       int z = altitude;
       int quant = 0;
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
                        Coordenada nova_c = new Coordenada(coordenada.getx() + x, coordenada.gety() + y, coordenada.getz() + z);
                        char elemento = ambiente.getElemento(nova_c);
                        double elem_hum = 0;
                        for(TipoObstaculo obs : TipoObstaculo.values()){
                            if(obs.get_inicial() == elemento){
                                elem_hum = obs.get_humidade();
                                quant++;
                                break;
                            }
                        }
                        if (Math.sqrt(x*x + y*y + z*z) > 0) umidade += elem_hum/(Math.sqrt(x*x + y*y + z*z));
                        else umidade += elem_hum;
                    } 
                }
            }
            z--;
        } while (z >= -altitude);
        if (quant > 0) umidade /= quant;
        System.out.printf("Umidade atual: %.2f%%. Preste atenção nos L ao longo do caminho.\n", umidade );
        return umidade;
    }
}
