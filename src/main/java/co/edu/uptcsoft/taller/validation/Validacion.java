package co.edu.uptcsoft.taller.validation;

import co.edu.uptcsoft.taller.service.Respuesta;

public class Validacion {

    public static Respuesta<Integer> requerido(String... args){//erg1, arg2,...
        for (int i = 0; i < args.length ; i++) {
            String entrada = args[i];
            if (entrada == null || entrada.isBlank()){
                return Respuesta.validacionError("El campo esta vacío",i);
            }
        }
        return Respuesta
                .completado("Todos los elementos han sido validados exitosamente",
                null);
    }

}
