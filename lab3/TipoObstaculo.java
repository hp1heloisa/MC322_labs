public enum TipoObstaculo {
    PAREDE("Parede sólida", false),
    AGUA("Área de água", true),
    FOGO("Chamas", false),
    ARBUSTO("Vegetação densa", true);

    private final String descricao;
    private final boolean atravessavel;

    TipoObstaculo(String descricao, boolean atravessavel) {
        this.descricao = descricao;
        this.atravessavel = atravessavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtravessavel() {
        return atravessavel;
    }
}