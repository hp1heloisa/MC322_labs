package simulador.missao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Random rand = new Random();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        System.out.printf("%s - Iniciando missão de exploração.%n", dtf.format(LocalDateTime.now()));

        int passos = 0;
        while (passos < passosMax) {
            int dir = rand.nextInt(4);
            int dx = 0, dy = 0;
            switch (dir) {
                case 0 -> dx = 1;
                case 1 -> dx = -1;
                case 2 -> dy = 1;
                case 3 -> dy = -1;
            }

            try {
                ambiente.moverEntidade(robo, robo.getPosicaoX() + dx, robo.getPosicaoY() + dy, robo.getposicaoZ(), robo);
                System.out.printf("%s - Passo %d: Moveu para (%d, %d, %d)%n", dtf.format(LocalDateTime.now()), passos + 1,
                        robo.getPosicaoX(), robo.getPosicaoY(), robo.getposicaoZ());
                passos++;
            } catch (Exception e) {
                System.out.printf("%s - Obstáculo detectado. Tentando outro caminho.%n", dtf.format(LocalDateTime.now()));
            }
        }

        System.out.printf("%s - Fim da missão de exploração após %d passos.%n", dtf.format(LocalDateTime.now()), passos);
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
        return "Missão de exploração aleatória pelo ambiente. Exploração por" + passosMax + " passos.";
    }
}