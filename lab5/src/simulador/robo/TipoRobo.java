package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.TipoDeRoboInexistenteException;

public enum TipoRobo {

    // Robôs Aéreos
    DESTRUIDOR("Robô Destruidor", true),
    TELETRANSPORTADOR("Robô Teletransportador", true),

    // Robôs Terrestres
    LIMITADO("Robô Limitado", false),
    GUINDASTE("Robô Guindaste", false),
    EXPLORADOR("Robô Explorador", false),
    PATRULEIRO("Robô Patruleiro", false),
    HIBRIDO("Robô Híbrido", false);

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

    public Robo criar(Ambiente ambiente, Scanner scanner, String []infos) throws TipoDeRoboInexistenteException {
        String nome = infos[2];
        int x = Integer.parseInt(infos[3]), y = Integer.parseInt(infos[4]), z = Integer.parseInt(infos[5]);
        Coordenada pos_inicial = new Coordenada(x, y, z);
        switch (this) {
            case LIMITADO: 
                return new RoboLimitado(ambiente, scanner, EstadoRobo.ligado, nome, pos_inicial);
            case GUINDASTE:
                return new RoboGuindaste(ambiente, scanner, EstadoRobo.ligado, nome, pos_inicial);
            case DESTRUIDOR: 
                return new RoboDestruidor(ambiente, scanner, EstadoRobo.ligado, nome, pos_inicial);
            case TELETRANSPORTADOR:
                return new RoboTeletransportador(ambiente, scanner, EstadoRobo.ligado, nome, pos_inicial);
            case EXPLORADOR:
                return new RoboExplorador(ambiente, nome, pos_inicial);
            case HIBRIDO:
                return new RoboHibrido(ambiente, nome, pos_inicial);
            // case PATRULEIRO:
            //     return new RoboPatrulheiro(ambiente, nome, pos_inicial);
            default:
                throw new TipoDeRoboInexistenteException("Não foi possível criar o robô, pois " + this + " não existe!");
        }
    }

}