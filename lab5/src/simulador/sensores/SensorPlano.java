package simulador.sensores;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;

public class SensorPlano extends Sensor {

    public SensorPlano(int r, Ambiente ambiente) {
        super(r, ambiente);
    }

    public void identificarArea(Coordenada coordenada) {
        System.out.printf("Obstáculos identificados em um raio de %dm: \n", super.raio);
        for (int y = super.raio; y > -super.raio; y--) {
            if (coordenada.gety() + y < 0) {
                continue;
            }
            for (int x = -super.raio; x < super.raio; x++) {
                Coordenada nova_c = new Coordenada(coordenada.getx() + x, coordenada.gety(), coordenada.getz());
                if (nova_c.getx() < 0) {
                    continue;
                }
                if (ambiente.dentroDosLimites(nova_c)) {
                    if (x == 0 && y == 0) {
                        System.out.printf("R"); 
                    }else {
                        ambiente.print_coordenada(coordenada.getx() + x, coordenada.gety() + y, coordenada.getz());
                    }
                }
            }
            System.out.println("");
        }
    }
}
