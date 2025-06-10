package simulador.robo;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.interfaces.Missao;


public abstract class AgenteInteligente extends Robo{
    protected Missao missao;

    public AgenteInteligente(Ambiente ambiente, Scanner scanner, EstadoRobo estado){
        super(ambiente, scanner, ambiente.getlistRobos(), estado);
    }
}
