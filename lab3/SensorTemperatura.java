public class SensorTemperatura extends Sensor {
    public SensorTemperatura(int r, Ambiente ambiente){
        super(r, ambiente);
    }
    public int calcula_temperatura(Coordenada coordenada) {
       int temp = 0;
        for (int z = super.raio; z > -super.raio; z--) {
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
                        if (elemento=='F'){
                            temp += TipoObstaculo.FOGO.get_temperatura()/(Math.sqrt(x*x + y*y + z*z));}
                    } 
                }
            }
        }
        System.out.printf("Temperatura atual: %dºC. Preste atenção nos F ao longo do caminho. ", temp );
        return temp;
    }
}
