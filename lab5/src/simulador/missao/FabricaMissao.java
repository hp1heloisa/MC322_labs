package simulador.missao;

import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;
import simulador.robo.AgenteInteligente;
import simulador.robo.Robo;

public class FabricaMissao {

    public static Missao criarMissao(String[] comando) {
        String tipo = comando[2].toUpperCase();
        return switch (tipo) {
            case "EXPLORAR" -> new MissaoExplorar(20);

            case "MONITORAR" -> {
                // Ex: MISSAO robo_id MONITORAR 2 2 0 5
                if (comando.length < 7) {
                    System.err.println("Erro: missão MONITORAR requer coordenadas e número de ciclos.");
                    yield null;
                }
                int x = Integer.parseInt(comando[3]);
                int y = Integer.parseInt(comando[4]);
                int z = Integer.parseInt(comando[5]);
                int ciclos = Integer.parseInt(comando[6]);
                yield new MissaoMonitorar(new Coordenada(x, y, z), ciclos);
            }

            case "BUSCAR" -> {
                if (comando.length < 6) {
                    System.err.println("Erro: missão BUSCAR requer coordenadas.");
                    yield null;
                }
                int x = Integer.parseInt(comando[3]);
                int y = Integer.parseInt(comando[4]);
                int z = Integer.parseInt(comando[5]);
                yield new MissaoBuscarPonto(new Coordenada(x, y, z), 20);
            }

            default -> {
                System.err.println("Missão desconhecida: " + tipo);
                yield null;
            }
        };
    }

    public static void atribuirMissao(Robo robo, Missao missao) {
        if (robo instanceof AgenteInteligente inteligente) {
            inteligente.adicionarMissao(missao);
        } else {
            System.out.println("Este robô não suporta missões autônomas.");
        }
    }
}
