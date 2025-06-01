package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.exceptions.TipoDeRoboInexistente;

public enum TipoRobo {

    // Robôs Terrestres
    LIMITADO("Robô Limitado", false),
    GUINDASTE("Robô Guindaste", false),

    // Robôs Aéreos
    DESTRUIDOR("Robô Destruidor", true),
    TELETRANSPORTADOR("Robô Teletransportador", true);

    private final String nome;
    private final boolean aereo;

    TipoRobo(String nome, boolean aereo) {
        this.nome = nome;
        this.aereo = aereo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAereo() {
        return aereo;
    }

    public Robo criar(Ambiente ambiente, Scanner scanner) throws TipoDeRoboInexistente {
        switch (this) {
            case LIMITADO: 
                return new RoboLimitado(ambiente, scanner, EstadoRobo.ligado);
            case GUINDASTE:
                return new RoboGuindaste(ambiente, scanner, EstadoRobo.ligado);
            case DESTRUIDOR: 
                return new RoboDestruidor(ambiente, scanner, EstadoRobo.ligado);
            case TELETRANSPORTADOR:
                return new RoboTeletransportador(ambiente, scanner, EstadoRobo.ligado);
            default:
                throw new TipoDeRoboInexistente("Não foi possível criar o robô, pois " + this + " não existe!");
        }
    }

}