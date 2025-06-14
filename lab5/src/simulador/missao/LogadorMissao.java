package simulador.missao;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/** Logger para registrar os passos da missão num txt */
public class LogadorMissao implements Closeable {

    private final BufferedWriter bw;

    public LogadorMissao(String nomeArquivo) throws IOException {
        bw = new BufferedWriter(new FileWriter(nomeArquivo, true));
    }

    public void log(String msg) {
        try { bw.write(LocalDateTime.now() + " - " + msg + System.lineSeparator()); }
        catch (IOException ignored) {}
    }

    @Override public void close() throws IOException { bw.close(); }
}
