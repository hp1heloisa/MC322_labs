package simulador.interfaces;
import simulador.ambiente.Ambiente;

/**
 * Contrato que define que um robô tem a capacidade de se mover de forma aleatória.
 * Esta é uma CAPACIDADE, não um TIPO de robô.
 */
public interface MoverParaOutroPonto{
    /**
     * Tenta mover o robô para uma posição adjacente aleatória.
     * @param ambiente O ambiente onde o robô está.
     * @return true se o movimento foi bem-sucedido, false caso contrário.
     */
    boolean tentarMover(Ambiente ambiente);
}