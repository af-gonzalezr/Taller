package co.edu.uptcsoft.taller.vista.cli;

import java.util.Collection;

public final class ConsolaUtils {
    private ConsolaUtils() {
    }

    public static void imprimirExito(String mensaje, Object resultado) {
        System.out.println(mensaje);
        if (resultado instanceof Collection<?> collection) {
            collection.forEach(System.out::println);
        } else if (resultado != null) {
            System.out.println(resultado);
        }
    }

    public static void imprimirError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}
