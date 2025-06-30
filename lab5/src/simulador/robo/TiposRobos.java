package simulador.robo;

import java.util.Scanner;

import simulador.ambiente.Ambiente;
import simulador.exceptions.TipoDeRoboInexistenteException;

/**
 * Classe utilitária (fábrica) para criar instâncias de robôs.
 */
public class TiposRobos {

    /**
     * Cria e retorna uma instância de Robô com base nos argumentos de um comando.
     *
     * @param ambiente O ambiente onde o robô existirá.
     * @param infos    O array de strings do comando (ex: "ROBO", "TIPO", "NOME", "X", "Y", "Z").
     * @return Uma instância de Robô ou null se a criação falhar.
     */
    public static Robo criarRobo(Ambiente ambiente, String[] infos, Scanner scanner) {
        if (infos.length < 6) {
            System.err.println("ERRO: Comando ROBO incompleto. Use: ROBO TIPO NOME X Y Z");
            return null;
        }
        
        String tipo_robo_str = infos[1];
        
        try {
            // Converte a string do tipo de robô para o enum correspondente
            TipoRobo tipo = TipoRobo.valueOf(tipo_robo_str.toUpperCase());
            
            // O Scanner é passado como nulo, pois a criação é via comando, não interativa.
            return tipo.criar(ambiente, scanner, infos);
        } catch (IllegalArgumentException e) {
            // Ocorre se valueOf() não encontrar o enum
            System.err.println("ERRO: Tipo de robô '" + tipo_robo_str + "' desconhecido.");
            return null;
        } catch (TipoDeRoboInexistenteException e) {
            // Exceção personalizada da lógica de criação
            System.err.println("Erro ao criar robô: " + e.getMessage());
            return null;
        }
    }
}