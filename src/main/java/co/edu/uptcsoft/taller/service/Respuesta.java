package co.edu.uptcsoft.taller.service;

public class Respuesta<T> {
    private final boolean completado;
    private final String mensaje;
    private final T elemento;

    public Respuesta(boolean completado, String mensaje, T elemento) {
        this.completado = completado;
        this.mensaje = mensaje;
        this.elemento = elemento;
    }

    public static <T> Respuesta<T> completado(String mensaje,T elemento){
        return new Respuesta<>(true,mensaje,elemento);
    }

    public static <T> Respuesta<T> error(String mensaje){
        return new Respuesta<>(false,mensaje,null);
    }

    public boolean isCompletado() {
        return completado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getElemento() {
        return elemento;
    }
}
