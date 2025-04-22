public enum TipoObstaculo {
    PAREDE("Parede sólida",'P', 3, 5),
    AGUA("Área de água", 'A', 2, 3),
    FOGO("Chamas", 'F', 2, 2),
    ARBUSTO("Vegetação densa",'V', 1, 6);

    private final String descricao;
    private final char inicial;
    private final int altura;
    private final int comprimento;
    

TipoObstaculo(String descricao, char inicial, int altura, int comprimento) {
    this.descricao = descricao;
    this.inicial = inicial;
    this.altura = altura;
    this.comprimento = comprimento;
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


}