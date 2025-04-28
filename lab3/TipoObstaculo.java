
public enum TipoObstaculo {
    MURALHA("Longa muralha bloqueando a passagem", 'M', 3, 5, 0, 0),
    LAGO("Área de água", 'L', 2, 3, -100, 100),
    FOGO("Área incendiada", 'F', 2, 2, 100, -100),
    VEGETACAO("Vegetação densa", 'V', 1, 6, 0, 50),
    ROBO("Robô", 'r', 1, 1, 0, 0);

    private final String descricao;
    private final char inicial;
    private final int altura;
    private final int comprimento;
    private final int temperatura;
    private final int umidade;


    TipoObstaculo(String descricao, char inicial, int altura, int comprimento, int temperatura, int umidade) {
        this.descricao = descricao;
        this.inicial = inicial;
        this.altura = altura;
        this.comprimento = comprimento;
        this.temperatura = temperatura;
        this.umidade = umidade;
    }
    /**Método que retorna o nome do obstáculo por extenso */
    public String getDescricao() {
        return descricao;
    }
    /**Método que retorna a inicial da descrição*/ 
    public char get_inicial() {
        return inicial;
    }
    /**Método que diz a altura do obstáculo */
    public int get_altura() {
        return altura;
    }
    /**Método que dizo comprimento do obstáculo */
    public int get_comprimento() {
        return comprimento;
    }
    /**Método que retorna a temperatura do obstáculo */
    public int get_temperatura() {
        return temperatura;
    }
    /**Método que retorna a umidade do obstáculo */
    public int get_humidade() {
        return umidade;
    }

}
