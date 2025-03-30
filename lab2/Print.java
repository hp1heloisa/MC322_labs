class Print{
    private Robo[] lista_Robos;
    private int num_robos;
    private Ambiente ambiente;

    public Print(Robo[] lista, int num_robos, Ambiente ambiente){
        System.out.println("");
        lista_Robos = lista;
        this.num_robos = num_robos; 
        this.ambiente = ambiente;
    } 
    public void print_tela(){
        for(int i = 0; i < num_robos; i++){
            lista_Robos[i].exibirNome();
            lista_Robos[i].exibirPosicao();
            if(ambiente.dentroDosLimites(lista_Robos[i].posicaoX(), lista_Robos[i].posicaoY(),0))
                System.out.println("O robô está dentro dos limites\n");
            else
                System.out.println("O robô não está dentro dos limites\n");

        }
    }

}
