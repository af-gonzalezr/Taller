package co.edu.uptcsoft.taller.Validation;

import co.edu.uptcsoft.taller.service.Respuesta;

public class Validacion {
    public static Respuesta<Integer> requerido(String... campos) {
        for (int i = 0; i < campos.length; i++) {
            if (campos[i] == null || campos[i].isBlank()) {
                return Respuesta.validacionError("Campo requerido vacío", i);
            }
        }
        return Respuesta.completado("Válido", -1);
    }
}