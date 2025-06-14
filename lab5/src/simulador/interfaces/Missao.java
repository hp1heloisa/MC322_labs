package simulador.interfaces;

import simulador.ambiente.Ambiente;
import simulador.missao.LogadorMissao;
import simulador.robo.Robo;

public interface Missao {

    /**
     * Executa a missão para um determinado robô em um ambiente.
     * @param robo O robô que executará a missão.
     * @param ambiente O ambiente onde a missão ocorre.
     * @param log O logger para registrar os eventos da missão.
     */
    void executar(Robo robo, Ambiente ambiente, LogadorMissao log);

    /**
     * Retorna a descrição da missão.
     * @return A descrição.
     */
    String getDescricao();
}