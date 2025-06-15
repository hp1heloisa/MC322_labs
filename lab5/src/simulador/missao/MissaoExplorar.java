package simulador.missao;

import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.interfaces.Missao;
import simulador.robo.Robo;

public class MissaoExplorar implements Missao {

    private final int passosMax;
    private static final Random rnd = new Random();

    public MissaoExplorar(int passosMax) { 
        this.passosMax = passosMax; 
    }

    @Override
public void executar(Robo robo, Ambiente ambiente) {
    try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
        executar(robo, ambiente, log);
    } catch (Exception e) {
        // 👇 CORREÇÃO: Imprima o erro para saber o que está acontecendo
        System.err.println("FALHA CRÍTICA AO CRIAR LOG: Não foi possível criar o arquivo de log para o robô " + robo.getNome());
        e.printStackTrace(); // Imprime a "pilha de chamadas" completa do erro no console
    }
}

    /* Interface antiga sem logger */
    // @Override
    // public void executar(Robo robo, Ambiente ambiente) {
    //     try (LogadorMissao log = new LogadorMissao("missao_" + robo.getNome() + ".txt")) {
    //         executar(robo, ambiente, log);
    //     } catch (Exception ignored) {}
    // }
    @Override
    public String getDescricao() {
        return "Explorar por " + passosMax + " passos.";
    }
}