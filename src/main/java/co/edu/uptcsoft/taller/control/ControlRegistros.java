package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.Respuesta;
import co.edu.uptcsoft.taller.validation.Validacion;

public class ControlRegistros {

    private final RegistrarCliente registrarCliente;


    public ControlRegistros(RegistrarCliente registrarCliente) {
        this.registrarCliente = registrarCliente;
    }


    public Respuesta<Cliente> crearCLiente(String idCliente, String nombre, String telefono, String email){
        String [] nombreCampos ={
                "idCliente",
                "nombre",
                "teléfono",
                "email"
        };

        Respuesta<Integer> requeridos = Validacion.requerido(idCliente,nombre,telefono,email);
        if(!requeridos.completado()){
            return Respuesta.error("El campo " + nombreCampos[requeridos.elemento()] + " esta vacío" );
        }

         if (!email.contains("@")){
            return Respuesta.error("El email debe contener '@'");
        } else if (telefono.charAt(0)!= '3') {
            return Respuesta.error("El teléfono debe empezar por 3");
        } else if (telefono.length()!=10) {
            return Respuesta.error("El teléfono debe tener 10 dígitos");
        }
        return registrarCliente.execute(idCliente, nombre, telefono, email);
    }



}
