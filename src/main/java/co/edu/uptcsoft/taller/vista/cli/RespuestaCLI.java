package co.edu.uptcsoft.taller.vista.cli;

import co.edu.uptcsoft.taller.service.Respuesta;

public class RespuestaCLI {
    public static void mostrarRespuesta(Respuesta<?> respuesta){
        if (respuesta.completado()){
            System.out.println(respuesta.mensaje());
            System.out.println(respuesta.elemento().toString());
        }else {
            System.err.println("Error: "+respuesta.mensaje());
        }
    }
}
