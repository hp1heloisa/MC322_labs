public class Sensor {
    protected int raio;
    protected Ambiente ambiente;
    public Sensor(int r, Ambiente ambiente) {
        raio = r;
        this.ambiente = ambiente;
    }

    public boolean tem_robo(int x, int y, int z) {
        char element = ambiente.getElemento(x, y, z);
        return element == 'r' ? true : false;
    }

    public boolean tem_obstaculo(int x, int y, int z) {
        char element = ambiente.getElemento(x, y, z);
        return (element == '*' || element=='F' || element=='A') ? false : true;
    }

    public String mostrar_obstaculo(int x, int y, int z){
        for(TipoObstaculo obs : TipoObstaculo.values()){
            if(ambiente.getElemento(x, y, z) == obs.get_inicial()){
                return obs.getDescricao();
            }
            
        }
        return "X";
    }
}

