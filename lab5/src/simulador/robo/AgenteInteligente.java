package simulador.robo;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;

/**
 * Robô que executa missões de forma autônoma.
 */
public abstract class AgenteInteligente extends Robo {

    protected Missao missao;

    public AgenteInteligente(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, null, ambiente.getlistRobos(), EstadoRobo.ligado, nome, pos_inicial);
    }

    /**
     * Adiciona uma missão à lista de tarefas do robô.
     * @param missao A missão a ser adicionada.
     */
    public void adicionarMissao(Missao missao) {
        if (missao != null) {
            this.pipeline.add(missao);
            System.out.println("INFO: Missão '" + missao.getDescricao() + "' adicionada ao robô " + getNome());
        }
    }
    
    /**
     * Executa todas as missões na pipeline, em ordem.
     * Esta lógica agora vive aqui e não precisa ser reescrita nos filhos.
     */
    public void executarMissao(Ambiente ambiente) {
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