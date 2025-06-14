package simulador.robo;

import java.util.ArrayList;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;

public abstract class AgenteInteligente extends Robo {

    private ArrayList<Missao> pipeline;

    public AgenteInteligente(Ambiente ambiente, Scanner scanner, ArrayList<Robo> listaRobos, EstadoRobo estado,
            String nome, Coordenada pos_inicial) {
        super(ambiente, scanner, listaRobos, estado, nome, pos_inicial);
    }
    
    public abstract char movimentacao() throws ForadosLimitesException;
}