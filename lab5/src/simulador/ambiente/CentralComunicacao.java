package simulador.ambiente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralComunicacao {

    private static final CentralComunicacao instanciaComunicacao = new CentralComunicacao();
    private Map<String, List<String>> mensagensEnviadasPorRobo = new HashMap<>();
    private Map<String, List<String>> mensagensRecebidasPorRobo = new HashMap<>();

    private CentralComunicacao() {}

    public static CentralComunicacao getComunicacao() {
        return instanciaComunicacao;
    }

    public void registrarMensagemEnviada(String remetente, String destinatario, String msg) {
        mensagensEnviadasPorRobo.putIfAbsent(remetente, new ArrayList<>());
        mensagensEnviadasPorRobo.get(remetente).add(String.format("Para %s: %s \n", destinatario, msg));
    }

    public void registrarMensagemRecebida(String remetente, String destinatario, String msg) {
        mensagensEnviadasPorRobo.putIfAbsent(destinatario, new ArrayList<>());
        mensagensEnviadasPorRobo.get(destinatario).add(String.format("De %s: %s \n", remetente, msg));
    }

    public void exibirMensagensEnviadasPorRobo(String nome) {
        List <String> mensagens = mensagensEnviadasPorRobo.get(nome);
        if (mensagens == null || mensagens.isEmpty())
            System.out.printf("O Robô %s não enviou nenhuma mensagem!\n", nome);
        else {
            System.out.printf("Mensagens enviadas pelo Robô %s:\n", nome);
            for (String msg : mensagens)
                System.out.println(msg);
        }
    }

    public void exibirMensagensRecebidasPorRobo(String nome) {
        List <String> mensagens = mensagensRecebidasPorRobo.get(nome);
        if (mensagens == null || mensagens.isEmpty())
            System.out.printf("O Robô %s não recebeu nenhuma mensagem!\n", nome);
        else {
            System.out.printf("Mensagens recebidas pelo Robô %s:\n", nome);
            for (String msg : mensagens)
                System.out.println(msg);
        }
    }

    public void exibirTodasMensagens() {
        for (String nome : mensagensEnviadasPorRobo.keySet()) {
            exibirMensagensEnviadasPorRobo(nome);
            exibirMensagensRecebidasPorRobo(nome);
            System.out.println("-------------------------");
        }
    }
}
