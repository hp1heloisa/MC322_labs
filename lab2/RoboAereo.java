class RoboAereo extends Robo{
    protected int altitude;
    protected int altitudeMaxima;
    public RoboAereo(){
        super();
        this.altitude = 1;
        this.altitudeMaxima = 2;
    }
    public void subir(){
        if (altitude+1 <= altitudeMaxima){
            altitude++;
        }else {
            altitude = altitudeMaxima;
        }
        printAltura();
    }
    public void descer(){
        if (altitude-1 >= 0){
            altitude--;        
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