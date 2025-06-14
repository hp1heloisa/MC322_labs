package simulador.interfaces;

import simulador.ambiente.Ambiente;
import simulador.robo.Robo;
import simulador.missao.LogadorMissao;

public interface Missao {
    void executar(Robo robo, Ambiente ambiente);
    default void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        executar(robo, ambiente);
    }
}
