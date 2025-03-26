class RoboTransportador extends RoboTerrestre{
    private String area_atuacao;
    public RoboTransportador(){
        super();
        System.err.println("Qual será a area de atuação do robô?");
        area_atuacao = scanner.nextLine();
        scanner.nextLine();
    }
    public void mover(int delta_x, int delta_y, int velocidade, String area_da_entrega){
        if(velocidade > velocidadeMax | area_da_entrega != area_atuacao){
            System.out.printf("Houve um erro na entrega, devido a ");
            String erro = ((area_atuacao == area_da_entrega) ? "velocidade excedida" : 
            ("incompatibilidade de terrenos, pois " + area_atuacao +" diferente de "+ area_da_entrega));
            System.out.println(erro);
        }
        else{
            posicaoX = delta_x;
            posicaoY = delta_y;
            System.out.println("Movimentação realizada com sucesso");
        }
    }
}