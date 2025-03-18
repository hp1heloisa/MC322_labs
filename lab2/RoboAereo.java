class RoboAereo extends Robo{
    protected int altitude;
    protected int altitudeMaxima;
    public RoboAereo(String nome, int posicaoX, int posicaoY, int altitude, int altitudeMaxima){
        super(nome, posicaoX, posicaoY);
        this.altitude = altitude;
        this.altitudeMaxima = altitudeMaxima;
    }
    public void subir(int metros){
        if (altitude+metros <= altitudeMaxima){
            altitude += metros;
        }else {
            altitude = altitudeMaxima;
        }
        printAltura();
    }
    public void descer(int metros){
        if (altitude-metros >= 0){
            altitude -= metros;        
        } else 
            altitude = 0;
        printAltura();
    }

    public void printAltura(){
        if (altitude == 0)
            System.out.println("O robo está no solo");
        else if (altitude < altitudeMaxima)
            System.out.printf("A altura do robo é %d\n", altitude);
        else
            System.out.printf("O robo se encontra na altitude máxima de %d\n", altitude);
    }
}