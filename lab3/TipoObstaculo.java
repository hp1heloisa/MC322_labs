public enum TipoObstaculo {
    PAREDE("Parede sólida",'P', 3, 5, 0),
    AGUA("Área de água", 'A', 2, 3, 0),
    FOGO("Chamas", 'F', 2, 2, 400),
    ARBUSTO("Vegetação densa",'V', 1, 6,0);

    private final String descricao;
    private final char inicial;
    private final int altura;
    private final int comprimento;
    private final int temperatura;
    

TipoObstaculo(String descricao, char inicial, int altura, int comprimento, int temperatura) {
    this.descricao = descricao;
    this.inicial = inicial;
    this.altura = altura;
    this.comprimento = comprimento;
    this.temperatura = temperatura;
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


}