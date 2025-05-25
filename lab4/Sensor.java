public class Sensor {
    protected int raio;
    protected Ambiente ambiente;
    /**Função construtora do sensor que identifica os itens próximos ao robô */
    public Sensor(int r, Ambiente ambiente) {
        raio = r;
        this.ambiente = ambiente;
    }
    /**Método que altera o raio do sensor */
    public void setRaio(int r) {
        raio = r;
    }
    /**Método que retorna se há algum robô na nova posição do robô*/
    public boolean tem_robo(Coordenada coordenada) {
        char element = ambiente.getElemento(coordenada);
        return element == 'r' ? true : false;
    }
/**Método que retorna se há algum obstáculo na nova posição do robô*/
    public boolean tem_obstaculo(Coordenada coordenada) {
        char element = ambiente.getElemento(coordenada);
        return (element == '*' || element=='F' || element=='L') ? false : true;
    }
    /**Método que retorna qual é o obstáculo na nova posição do robô, se há*/
    public String mostrar_obstaculo(Coordenada coordenada){
        for(TipoObstaculo obs : TipoObstaculo.values()){
            if(ambiente.getElemento(coordenada) == obs.get_inicial()){
                return obs.getDescricao();
            }
            
        }

        return "X";
    }
}

