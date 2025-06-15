package simulador.interfaces;

import simulador.ambiente.Ambiente;
import simulador.missao.LogadorMissao;
import simulador.robo.Robo;

public interface Missao {
    void executar(Robo robo, Ambiente ambiente);
    default void executar(Robo robo, Ambiente ambiente, LogadorMissao log) {
        executar(robo, ambiente);
    }
    String getDescricao();
}