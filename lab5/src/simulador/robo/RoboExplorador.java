package simulador.robo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import simulador.ambiente.Ambiente;
import simulador.ambiente.Coordenada;
import simulador.exceptions.ColisaoException;
import simulador.exceptions.ForadosLimitesException;
import simulador.interfaces.Missao;
import simulador.missao.MissaoBuscarPonto;
import simulador.missao.MissaoExplorar;

/** Robô terrestre autônomo capaz de explorar e buscar um ponto. */
public class RoboExplorador extends AgenteInteligente {
    private List<Missao> pipeline;

    public RoboExplorador(Ambiente ambiente, String nome, Coordenada pos_inicial) {
        super(ambiente, nome, pos_inicial);
        setNome(nome);
    }

    public RoboExplorador(Ambiente ambiente, int passosExplorar, Coordenada destino, String nome, Coordenada pos_inicial) {
        this(ambiente, nome, pos_inicial);  // chama o construtor
        pipeline = Arrays.asList(
                new MissaoExplorar(passosExplorar),
                new MissaoBuscarPonto(destino, 30));
    }

    @Override
    public String getDescricao() {
        return "Robô Explorador autônomo (explorar + opcional buscar ponto)";
    }

    @Override 
    public void explicar_movimentacao() {

    }

    @Override 
    public char movimentacao() throws ColisaoException, ForadosLimitesException { 
        return 'x'; 
    }

    /** Executa pipeline, se existir; caso contrário usa definirMissao individualmente. */
    @Override
    public void executarMissao(Ambiente ambiente) {
        if (pipeline != null && !pipeline.isEmpty()) {
            for (Missao m : pipeline) {
                definirMissao(m);
                super.executarMissao(ambiente);
            }
        } else {
            super.executarMissao(ambiente);
        }
    }

    public boolean tentarMoverAleatorio(Ambiente ambiente) {
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int dx = rnd.nextInt(3) - 1;
            int dy = rnd.nextInt(3) - 1;
            if (dx == 0 && dy == 0) continue;

            int nx = getPosicaoX() + dx;
            int ny = getPosicaoY() + dy;
            int nz = getposicaoZ();
            Coordenada nova = new Coordenada(nx, ny, nz);

            if (ambiente.estaOcupado(nova)) continue;

            try {
                ambiente.moverEntidade(this, nx, ny, nz, this);
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }
}
