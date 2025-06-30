package simulador.robo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;

/**
 * Representa um robô com a capacidade de seguir uma lista (pipeline) de
 * missões. Esta classe introduz a inteligência de seguir um plano.
 */
public abstract class AgenteInteligente extends Robo {

    
    protected List<Missao> pipeline;

    public AgenteInteligente(Ambiente ambiente, Scanner scanner, EstadoRobo estado, String nome, Coordenada pos_inicial) {
        super(ambiente, scanner, ambiente.getlistRobos(), estado, nome, pos_inicial);
        this.pipeline = new ArrayList<>(); // Inicializa como uma lista vazia.

    }

    public AgenteInteligente(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        this(ambiente, null, EstadoRobo.ligado, nome, pos_inicial);
        this.pipeline = new ArrayList<>();
    }

    /**
     * Adiciona uma missão à lista de tarefas do robô.
     *
     * @param missao A missão a ser adicionada.
     */
    public void adicionarMissao(Missao missao) {
        if (missao != null) {
            this.pipeline.add(missao);
            System.out.println("INFO: Missão '" + missao.getDescricao() + "' adicionada ao robô " + getNome());
        }
    }

    /**
     * Verifica se o robô possui pelo menos uma missão na pipeline.
     * 
     * @return true se houver missões pendentes, false caso contrário.
     */
    public boolean temMissao() {
        return !pipeline.isEmpty();
    }

    /**
     * Executa todas as missões na pipeline, em ordem. Esta lógica agora vive
     * aqui e não precisa ser reescrita nos filhos.
     */
    public void executarMissao(Ambiente ambiente) throws ColisaoException, IOException, ForadosLimitesException {
        if (pipeline.isEmpty()) {
            System.out.println("AVISO: " + getNome() + " não tem nenhuma missão na sua pipeline.");
            return;
        }

        System.out.println("--- Iniciando pipeline para " + getNome() + " ---");
        for (Missao missaoAtual : pipeline) {
            if (getEstado() == EstadoRobo.desligado) {
                System.out.println("Pipeline interrompido: Robô foi desligado.");
                break;
            }
            System.out.println("Executando missão: " + missaoAtual.getDescricao());
            // O Agente delega a execução para o objeto Missao.
            missaoAtual.executar(this, ambiente);
        }
        System.out.println("--- Pipeline de " + getNome() + " concluído ---");
        pipeline.clear(); // Limpa a pipeline para receber novas missões.
    }
    
}
