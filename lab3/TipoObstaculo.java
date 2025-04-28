public enum TipoObstaculo {
    MURALHA("Longa muralha bloqueando a passagem",'M', 3, 5, 0, 0),
    LAGO("Área de água", 'L', 2, 3, -100, 100),
    FOGO("Área incendiada", 'F', 2, 2, 100, -100),
    VEGETACAO("Vegetação densa",'V', 1, 6,0, 50),
    ROBO("Robô", 'r', 1, 1, 0, 0);

    private final String descricao;
    private final char inicial;
    private final int altura;
    private final int comprimento;
    private final int temperatura;
    private final int humidade;
    

TipoObstaculo(String descricao, char inicial, int altura, int comprimento, int temperatura, int humidade) {
    this.descricao = descricao;
    this.inicial = inicial;
    this.altura = altura;
    this.comprimento = comprimento;
    this.temperatura = temperatura;
    this.humidade = humidade;
}

public String getDescricao() {
    return descricao;
}
public char get_inicial(){
    return inicial;
}
public int get_altura(){
    return altura;
}
public int get_comprimento(){
    return comprimento;
}

public int get_temperatura(){
    return temperatura;
}

public int get_humidade() {
    return humidade;
}

}