package simulador.robo;

import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;

/**
 * Representa um tipo de robô "Híbrido".
 * Na nova arquitetura, ele é apenas um "casco" que herda a capacidade de executar
 * uma pipeline de missões da sua classe pai, AgenteInteligente.
 * As missões específicas (explorar, patrulhar, etc.) são adicionadas dinamicamente
 * após a sua criação.
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
        return "Robô Híbrido";
    }

    // 3. Os métodos abstratos da classe pai ainda precisam ser implementados,
    //    mesmo que a lógica principal esteja nas missões.
    @Override
    public void explicar_movimentacao() {
        System.out.println("Este robô executa a lista de missões que lhe foi atribuída.");
    }

    @Override
    public char movimentacao() {
        // A lógica de movimentação agora é controlada por cada Missao individualmente.
        // Este método pode não ser chamado diretamente se o robô só age via 'executarMissao'.
        System.out.println("Use o comando EXECUTAR para iniciar a pipeline de missões.");
        return ' '; // Retorna um caractere neutro
    }
    
    // O método executarMissao() e o campo 'pipeline' NÃO SÃO MAIS NECESSÁRIOS AQUI.
    // Eles são herdados diretamente da classe AgenteInteligente.
}