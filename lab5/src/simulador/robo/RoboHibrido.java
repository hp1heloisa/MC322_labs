package simulador.robo;
import java.util.Arrays;
import java.util.List;
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

    private final List<Missao> pipeline;

    // O construtor recebe todos os parâmetros necessários para todas as missões
    public RoboHibrido(
            Ambiente ambiente,
            String nome,
            Coordenada pos_inicial,
            int passosExplorar, // Parâmetro da MissaoExplorar
            List<Coordenada> waypoints, // Parâmetro da MissaoPatrulhar
            int ciclosMonitoramento // Parâmetro da MissaoMonitorar
    ) {
        super(ambiente, nome, pos_inicial);

        // Criamos o pipeline combinado, na ordem que quisermos!
        this.pipeline = Arrays.asList(
            new MissaoExplorar(passosExplorar),
            new MissaoPatrulhar(waypoints),
            new MissaoMonitorar(waypoints.get(0), ciclosMonitoramento)
        );
    }

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