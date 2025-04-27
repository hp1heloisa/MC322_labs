public class Sensor {
    protected int raio;
    protected Ambiente ambiente;
    public Sensor(int r, Ambiente ambiente) {
        raio = r;
        this.ambiente = ambiente;
    }

    public void setRaio(int r) {
        raio = r;
    }

    public boolean tem_robo(Coordenada coordenada) {
        char element = ambiente.getElemento(coordenada);
        return element == 'r' ? true : false;
    }

    public boolean tem_obstaculo(Coordenada coordenada) {
        char element = ambiente.getElemento(coordenada);
        return (element == '*' || element=='F' || element=='A') ? false : true;
    }

    public String mostrar_obstaculo(Coordenada coordenada){
        for(TipoObstaculo obs : TipoObstaculo.values()){
            if(ambiente.getElemento(coordenada) == obs.get_inicial()){
                return obs.getDescricao();
            }
            
        }
        return "X";
    }
}

