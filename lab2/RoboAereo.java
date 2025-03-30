class RoboAereo extends Robo{
    protected int altitude;
    protected int altitudeMaxima;
    public RoboAereo(Ambiente ambiente){
        super(ambiente);
        System.out.println("Em qual altidude o seu robô se encontra?");
        altitude = scanner.nextInt();
        System.out.println("Qual altidude máxima que o seu robô pode alcançar?");
        altitudeMaxima = scanner.nextInt();
    }
    @Override
    public void explicar_movimentacao(){
        System.out.println("Você pode movimentar seu robô usando os seguintes comandos: ");
        System.out.println("w -> ir para frente; s -> ir para trás");
        System.out.println("d -> ir para direita; a -> ir para a esqueda");
        System.out.println("u -> para subir; j -> para descer");
        System.out.println("p -> para scanear a área");
        System.out.println("n -> criar um novo robô; x -> para sair");
    }

    public int posicaoZ() {
        return altitude;
    }

    public void identificarArea(int alt) {
        for (int y=5; y>-5; y--){
            if (posicaoY+y < 0) continue;
            for (int x=-5; x<5; x++){
                if (posicaoX+x < 0) continue;
                if (ambiente.dentroDosLimites(posicaoX + x,  posicaoY + y, alt) && ambiente.tem_obstaculo(posicaoX + x,  posicaoY + y, alt)){
                    System.out.printf("X");
                }else {
                  if (x==0 && y==0 && alt == altitude)
                    System.out.printf("R");
                  else 
                    if (ambiente.tem_robo(posicaoX + x,  posicaoY + y, alt))
                        System.out.printf("r");
                    else System.out.printf("*");
                }
            }
            System.out.println("");
          }
      }

    public void identificarObstaculo() {
        for (int z=-5; z<5; z++){
            if (altitude+z < 0)
                continue;
            System.out.printf("Mapa dos obstáculos encontrados em um raio de 5m na altitude %d\n", altitude+z);
            identificarArea(altitude+z);
        }
      }

    public void mover(int deltaX, int deltaY) {
        Coordenada c_0 = new Coordenada(posicaoX, posicaoY, altitude);
        if (ambiente.dentroDosLimites(posicaoX + deltaX,  posicaoY + deltaY, altitude) ){
            int passo = 1;
            if (deltaX < 0) {
                deltaX *= -1;
                passo = -1;
            } 
            while (deltaX > 0){
                if (ambiente.tem_obstaculo(posicaoX + passo,  posicaoY, altitude)){
                    System.out.printf("Há um obstáculo na posição: (%d,%d,%d)\n", posicaoX+passo, posicaoY, altitude);
                    return;
                }else {
                    if (ambiente.tem_robo(posicaoX + passo,  posicaoY, altitude)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX+passo, posicaoY, altitude);
                        return;
                    }else{
                        posicaoX+=passo;
                        deltaX--;
                    }
                }
            }
            passo = 1;
            if (deltaY < 0) {
                deltaY *= -1;
                passo = -1;
            }
            while (deltaY > 0){
                if (ambiente.tem_obstaculo(posicaoX, posicaoY+passo, altitude)){
                    System.out.printf("Há um obstáculo na posição: (%d,%d,%d)\n", posicaoX, posicaoY+passo, altitude);
                    return;
                }else {
                    if (ambiente.tem_robo(posicaoX,  posicaoY+passo, altitude)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX, posicaoY+passo, altitude);
                        return;
                    }
                    else{
                        posicaoY+=passo;
                        deltaY--;
                    }
                    
                }
            }
        } else
          System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        Coordenada c = new Coordenada(posicaoX, posicaoY, altitude);
        atualizarAmbiente(c_0, c);

      }

    public void subir(){
        Coordenada c_0 = new Coordenada(posicaoX, posicaoY, altitude);
        boolean dentroDosLimites = ambiente.dentroDosLimites(posicaoX,  posicaoY, altitude+1);
        if (dentroDosLimites){
            if (altitude+1 <= altitudeMaxima){
                if (!ambiente.tem_obstaculo(posicaoX,  posicaoY, altitude+1)){
                    if (ambiente.tem_robo(posicaoX,  posicaoY, altitude+1)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX, posicaoY, altitude+1);
                        return;
                    }
                    else{
                        altitude++;
                    }
                }else 
                    System.out.println("Há um obtáculo nessa posição!");
            }
        } else {
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        }
        Coordenada c = new Coordenada(posicaoX, posicaoY, altitude);
        atualizarAmbiente(c_0, c);
    }
    public void descer(){
        Coordenada c_0 = new Coordenada(posicaoX, posicaoY, altitude);
        if (altitude-1 >= 0){
            if (!ambiente.tem_obstaculo(posicaoX,  posicaoY, altitude-1)){
                if (ambiente.tem_robo(posicaoX,  posicaoY, altitude-1)) {
                        System.out.printf("Há um robô na posição: (%d,%d,%d)\n", posicaoX, posicaoY, altitude-1);
                        return;
                    }
                else{
                    altitude--;
                }
            }else 
                System.out.println("Há um obtáculo nessa posição!");
        } else 
            System.out.println("Essa posição encontra-se fora dos limites do ambiente!");
        Coordenada c = new Coordenada(posicaoX, posicaoY, altitude);
        atualizarAmbiente(c_0, c);
    }

    @Override
    public char movimentacao(){
        char movimento_robo = ' ';
        System.out.printf("Teste\n");
        while(movimento_robo != 'x' && movimento_robo != 'n'){
            movimento_robo = scanner.next().charAt(0);
            switch(movimento_robo) {
            case 'a':
                this.mover(-1, 0);
                break;   
            case 'd':
                this.mover(1, 0);
                break;
            case 'w':
                this.mover(0, 1);
                break;
            case 's':
                this.mover(0, -1);
                break;
            case 'u':
                subir();
                break;
            case 'j':
                descer();
                break;
            case 'p':
                identificarObstaculo();
                break;
            case 'x':
                System.out.println("Encerrando movimentação...");
                break;
            case 'n':
                break;
            default:
                System.out.println("Comando inválido! Use w, s, a, d, u, j ou x");
            }
            identificarArea(altitude);
        }
        return movimento_robo;
    }

}