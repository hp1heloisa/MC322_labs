package simulador.robo;

import java.util.Scanner;
import simulador.ambiente.Ambiente;
import simulador.exceptions.TipoDeRoboInexistenteException;

/**
 * Função construtora de TiposRobos
 */
public class TiposRobos {

    Scanner scanner;

    public TiposRobos(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Método responsável retornar as classes possíveis do robô e criá-lo
     * com base na escolha do usuário.
     */
    public Robo definir_robo(Ambiente ambiente) {
        System.out.println("Qual tipo de robô você quer criar?");
        TipoRobo[] tipos = TipoRobo.values();

        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("%d. %s (%s)\n", i+1, tipos[i].getNome(), tipos[i].isAereo() ? "Aéreo" : "Terrestre");
        }

        int robo_escolhido = scanner.nextInt();
        scanner.nextLine();

        if (robo_escolhido < 1 || robo_escolhido > tipos.length) {
            System.out.println("Opção inválida!");
            return null;
        }
        
        TipoRobo tipo = tipos[robo_escolhido - 1];
        try {
            Robo robo = tipo.criar(ambiente, scanner);
            return robo;
        } catch (TipoDeRoboInexistenteException e) {
            System.out.println("Erro ao criar robô: " + e.getMessage());
            return null;
        }
    }
}
