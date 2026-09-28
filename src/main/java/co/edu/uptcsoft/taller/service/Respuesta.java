package co.edu.uptcsoft.taller.service;

public record Respuesta<T>(boolean completado, String mensaje, T elemento) {

    public static <T> Respuesta<T> completado(String mensaje, T elemento) {
        return new Respuesta<>(true, mensaje, elemento);
    }

    public static <T> Respuesta<T> error(String mensaje) {
        return new Respuesta<>(false, mensaje, null);
    }


    /**
     *
     * @param mensaje mensaje de error
     * @param index numero del elemento q fallo la validación, índice
     * @return una respuesta de error con mensaje y un índice
     */
    public static Respuesta<Integer> validacionError(String mensaje, int index){
        return new Respuesta<>(false,mensaje,index);
    }
}
