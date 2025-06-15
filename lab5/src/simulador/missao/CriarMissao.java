package simulador.missao;

import simulador.ambiente.Coordenada;
import simulador.interfaces.Missao;

public class CriarMissao {

    public static Missao criarMissao(String[] infos) {
        if (infos.length < 3) return null;
        String tipoMissao = infos[2].toUpperCase();

        try {
            switch (tipoMissao) {
                case "EXPLORAR":
                    int passos = infos.length > 3 ? Integer.parseInt(infos[3]) : 50;
                    return new MissaoExplorar(passos);
                
                case "BUSCAR": 
                    int x = Integer.parseInt(infos[3]);
                    int y = Integer.parseInt(infos[4]);
                    int z = Integer.parseInt(infos[5]);
                    Coordenada destino = new Coordenada(x, y, z);
                    return new MissaoBuscarPonto(destino, 30);
                
                case "PATRULHAR":
                    System.out.println("Missão Patrulhar ainda não implementada");

                case "MONITORAR":
                    System.out.println("Missão Monitorar ainda não implementada");

                default:
                    System.err.println("ERRO: Tipo de missão '" + tipoMissao + "' desconhecido.");
                    return null;
            }
        } catch (Exception e) {
            System.err.println("ERRO ao processar parâmetros da missão: " + e.getMessage());
            return null;
        }
    }
}