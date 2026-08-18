package dev.julio.juliocraft.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class Utils {

    private Utils() {}

    public static String readFile(String filePath) {
        try (InputStream in = Utils.class.getResourceAsStream(filePath)) {
            if (in == null) {
                throw new RuntimeException("Arquivo não encontrado no classpath: " + filePath);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Erro lendo " + filePath, e);
        }
    }
}
