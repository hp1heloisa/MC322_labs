package simulador.robo;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.missao.MissaoExplorar; 
import simulador.missao.MissaoPatrulhar; 
import simulador.missao.MissaoMonitorar; 

/**
 * Um robô híbrido que combina as capacidades de um Explorador e um Patrulheiro.
 * Ele primeiro explora uma área e depois inicia uma patrulha.
 */
public class RoboHibrido extends AgenteInteligente {

    // 1. O construtor agora é muito simples.
    // Ele apenas passa as informações básicas para a superclasse.
    public RoboHibrido(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
    }

    // 2. A descrição continua útil para identificar o tipo do robô.
    @Override
    public String getDescricao() {
        return "Robô Híbrido (Explorar + Patrulhar)";
    }

  
    @Override
    public void executarMissao(Ambiente ambiente) {
        System.out.println("Iniciando pipeline de missões para o Robô Híbrido: " + getNome());
        for (Missao m : pipeline) {
            if (getEstado() == EstadoRobo.desligado) {
                System.out.println("Missão interrompida, robô foi desligado.");
                break;
            }
            definirMissao(m);
            super.executarMissao(ambiente); // Executa a missão atual
        }
        System.out.println("Pipeline de missões do Robô Híbrido concluído.");
    }
    
    @Override

    public void explicar_movimentacao() {
        System.out.println("Este robô executa uma série de missões predefinidas.");
    }

    @Override
    public char movimentacao() {
        return 'x';
    }
}