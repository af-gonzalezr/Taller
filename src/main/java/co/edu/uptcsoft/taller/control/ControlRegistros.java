package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.Respuesta;

public class ControlRegistros {

    private final RegistrarCliente registrarCliente;

    public ControlRegistros(RegistrarCliente registrarCliente) {
        this.registrarCliente = registrarCliente;
    }


    public String crearCLiente(String idCliente, String nombre, String telefono, String email){
        if(idCliente.isBlank()||nombre.isBlank()||telefono.isBlank()||email.isBlank()){
            return "Faltan algunos campos";
        } else if (!email.contains("@")){
            return "El email debe contener '@'";
        } else if (telefono.charAt(0)!= '3') {
            return "El teléfono debe empezar por 3";
        } else if (telefono.length()>10) {
            return "El teléfono debe tener 10 dígitos";
        }


    }
}
