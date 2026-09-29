package co.edu.uptcsoft.taller.service;

public record Respuesta<T>(boolean completado, String mensaje, T elemento) {

    public static <T> Respuesta<T> completado(String mensaje, T elemento) {
        return new Respuesta<>(true, mensaje, elemento);
    }

    public static <T> Respuesta<T> error(String mensaje) {
        return new Respuesta<>(false, mensaje, null);
    }
}
