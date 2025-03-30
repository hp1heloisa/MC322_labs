class RoboGuindaste extends RoboTerrestre{
    public RoboGuindaste(Ambiente ambiente){
        super(ambiente);
    }
    public void mover(int delta_x, int delta_y, int velocidade, String area_da_entrega){
        if(velocidade > velocidadeMax){
            System.out.printf("Houve um erro no guindaste, devido a velocidade excedida");
        }
        else{
            posicaoX = delta_x;
            posicaoY = delta_y;
            System.out.println("Movimentação realizada com sucesso");
        }
    }
}