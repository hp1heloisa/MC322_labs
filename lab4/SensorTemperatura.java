

public class SensorTemperatura extends Sensor {
    protected int altitude;
    public SensorTemperatura(int r, int h, Ambiente ambiente){
        super(r, ambiente);
        altitude = h;
    }

    public void setAltitude(int h) {
        altitude = h;
    }

    /**Método que calcula a temperatura de um determinado ponto usando a média das distancias eucliadianas */
    public double calcula_temperatura(Coordenada coordenada) {
       double temp = 0;
       int z = altitude;
       double elem_temp = 0;
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
                    Coordenada nova_c = new Coordenada(coordenada.getx() + x, coordenada.gety() + y, coordenada.getz() + z); //talvez somar com altitude??? Não entendi
                    if (ambiente.dentroDosLimites(nova_c)) {
                        char elemento = ambiente.getElemento(nova_c);
                        for(TipoObstaculo obs: TipoObstaculo.values()){
                            if(obs.get_inicial() == elemento ){
                                elem_temp = obs.get_temperatura();
                                quant++;
                                break;
                            }
                        }
                    }
                        if (Math.sqrt(x*x + y*y + z*z) > 0) temp += elem_temp/(Math.sqrt(x*x + y*y + z*z));
                        else temp += elem_temp;
                    } 
                }
            
    
            z--;
        } while(z >= -altitude);
        if (quant > 0) temp /= quant;
        System.out.printf("Temperatura atual: %.2fºC. Preste atenção nos F ao longo do caminho.\n", temp );
        return temp;
    }
}
