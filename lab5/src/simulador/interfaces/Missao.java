package simulador.interfaces;
import simulador.robo.Robo;
import simulador.ambiente.Ambiente;


public interface Missao{
    void executar(Robo r, Ambiente a);
}