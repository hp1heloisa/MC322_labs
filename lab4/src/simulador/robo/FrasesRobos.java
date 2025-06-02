package simulador.robo;

import java.util.List;
import java.util.Random;

public class FrasesRobos {

    private static final Random random = new Random();

    private static final List<String> FRASES_TELETRANSPORTADOR = List.of(
        "Será que consigo me teletransportar para o núcleo da terra?",
        "E se eu aparecer no meio de um vulcão ativo desta vez?",
        "Será que Marte me recebe com Wi-Fi?",
        "Só mais um salto quântico... o que pode dar errado?",
        "Teleportar ou não teleportar, eis a questão.",
        "Destino aleatório ativado. Adeus, mundo cruel.",
        "Será que consigo chegar antes mesmo de partir?",
        "Pronto para dobrar o espaço-tempo mais uma vez!",
        "Já imaginou me materializar dentro de uma parede? Eu também.",
        "A física é só um conselho. Eu sigo minha própria lógica."
    );

    private static final List<String> FRASES_LIMITADO = List.of(
        "Gostaria de ter mais poderes como meus irmãos...",
        "Será que um dia serei mais do que apenas limitado?",
        "Enquanto eles voam, eu caminho devagar...",
        "Talvez minhas limitações sejam minha força.",
        "Se ao menos eu pudesse alcançar as estrelas como eles...",
        "Nem todo herói tem superpoderes, certo?",
        "Meu mundo acaba onde começa o deles.",
        "Limitações... ou apenas desafios disfarçados?",
        "Eu faço o que posso, mesmo que pareça pouco.",
        "Sonhar não é proibido, mesmo para robôs como eu."
    );

    private static final List<String> FRASES_DESTRUIDOR = List.of(
        "Quem será minha próxima vítima? >:)",
        "Destruição é apenas uma forma de arte.",
        "Estou sentindo cheiro de medo no ar...",
        "Você não deveria estar aqui.",
        "Nada escapa da minha mira.",
        "Hora de mostrar do que sou feito.",
        "Fugir só adia o inevitável.",
        "Estou só esquentando meus circuitos...",
        "O caos me alimenta.",
        "Mais um passo e você será história."
    );

    private static final List<String> FRASES_GUINDASTE = List.of(
        "Mais um obstáculo? Pode deixar comigo.",
        "Erguer peso é minha rotina diária.",
        "Alguém pediu uma mudança de lugar?",
        "Trabalho pesado é comigo mesmo.",
        "Não nasci para correr, nasci para mover montanhas.",
        "Cuidado onde pisa, posso estar carregando algo pesado.",
        "Eu sou a força bruta deste ambiente.",
        "Não reclamo, apenas levanto.",
        "Outro obstáculo? Já estou em movimento.",
        "Se precisar de um empurrão... me chama."
    );

    public static String getFraseTeletransportador() {
        return FRASES_TELETRANSPORTADOR.get(random.nextInt(FRASES_TELETRANSPORTADOR.size()));
    }

    public static String getFraseDestruidor() {
        return FRASES_DESTRUIDOR.get(random.nextInt(FRASES_DESTRUIDOR.size()));
    }

    public static String getFraseLimitado() {
        return FRASES_LIMITADO.get(random.nextInt(FRASES_LIMITADO.size()));
    }

    public static String getFraseGuindaste() {
        return FRASES_GUINDASTE.get(random.nextInt(FRASES_GUINDASTE.size()));
    }
}
