package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.time.LocalDate;

import co.edu.uptcsoft.taller.Validation.Validacion;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Camion;


public class ControlRegistros {

    private final RegistrarCliente registrarCliente; 
    private final RegistrarTecnico registrarTecnico;
    private final RegistrarAuto registrarAuto;
    private final RegistrarCamion registrarCamion;
    private final RegistrarMoto registrarMoto;
    
    
    


    public ControlRegistros(RegistrarCliente registrarCliente, RegistrarTecnico registrarTecnico,
            RegistrarAuto registrarAuto, RegistrarCamion registrarCamion, RegistrarMoto registrarMoto) {
        this.registrarCliente = registrarCliente;
        this.registrarTecnico = registrarTecnico;
        this.registrarAuto = registrarAuto;
        this.registrarCamion = registrarCamion;
        this.registrarMoto = registrarMoto;
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
    
   
    public Respuesta<Tecnico> crearTecnico(String idTecnico, String nombre, String especialidad){
        String [] nombreCampos ={
                "idTecnico",
                "nombre",
                "especialidad"
                
        };

        Respuesta<Integer> requeridos = Validacion.requerido(idTecnico,nombre,especialidad);
        if(!requeridos.completado()){
            return Respuesta.error("El campo " + nombreCampos[requeridos.elemento()] + " esta vacío" );
        }

        
        return registrarTecnico.execute(idTecnico, nombre, especialidad);
    }
    
   public Respuesta<Automovil> crearAuto(String placa, String marca, String modelo,
                                      String idCliente, String numeroPuertas) {
    String[] nombreCampos = {"placa", "marca", "modelo", "idCliente", "número de puertas"};

    Respuesta<Integer> requeridos = Validacion.requerido(placa, marca, modelo, idCliente, numeroPuertas);
    if (!requeridos.completado()) {
        return Respuesta.error("El campo " + nombreCampos[requeridos.elemento()] + " esta vacío");
    }

    int modeloInt;
    int puertasInt;
    try {
        modeloInt = Integer.parseInt(modelo.trim());
        puertasInt = Integer.parseInt(numeroPuertas.trim());
    } catch (NumberFormatException e) {
        return Respuesta.error("El modelo y el número de puertas deben ser números");
    }

    if (modeloInt < 1900 || modeloInt > LocalDate.now().getYear() + 1) {
        return Respuesta.error("El modelo no es un año válido");
    }
    if (puertasInt <= 0) {
        return Respuesta.error("El número de puertas debe ser mayor a 0");
    }

    return registrarAuto.execute(placa, marca, modeloInt, idCliente, puertasInt);
}

     public Respuesta<Camion> crearCamion(String placa, String marca, String modelo,
                                      String idCliente, String capacidadCargaTon) {
    String[] nombreCampos = {"placa", "marca", "modelo", "idCliente", "capacida de carga en toneladas"};

    Respuesta<Integer> requeridos = Validacion.requerido(placa, marca, modelo, idCliente, capacidadCargaTon);
    if (!requeridos.completado()) {
        return Respuesta.error("El campo " + nombreCampos[requeridos.elemento()] + " esta vacío");
    }

    int modeloInt;
    double capacidadCargaDouble;
    try {
        modeloInt = Integer.parseInt(modelo.trim());
      capacidadCargaDouble = Double.parseDouble(capacidadCargaTon.trim());
    } catch (NumberFormatException e) {
        return Respuesta.error("El modelo y la capacidad de carga deben ser números");
    }

    if (modeloInt < 1900 || modeloInt > LocalDate.now().getYear() + 1) {
        return Respuesta.error("El modelo no es un año válido");
    }
    if (capacidadCargaDouble <= 0) {
        return Respuesta.error("La capacidad de carga debe ser mayor a 0");
    }

    return registrarCamion.execute(placa, marca, modeloInt, idCliente, capacidadCargaDouble);
}

    public Respuesta<Motocicleta> crearMoto(String placa, String marca, String modelo,
                                      String idCliente, String cilindraje) {
    String[] nombreCampos = {"placa", "marca", "modelo", "idCliente", "cilindraje"};

    Respuesta<Integer> requeridos = Validacion.requerido(placa, marca, modelo, idCliente, cilindraje);
    if (!requeridos.completado()) {
        return Respuesta.error("El campo " + nombreCampos[requeridos.elemento()] + " esta vacío");
    }

    int modeloInt;
    int cilindrajeInt;
    try {
        modeloInt = Integer.parseInt(modelo.trim());
        cilindrajeInt = Integer.parseInt(cilindraje.trim());
    } catch (NumberFormatException e) {
        return Respuesta.error("El modelo y el cilindraje deben ser números");
    }

    if (modeloInt < 1900 || modeloInt > LocalDate.now().getYear() + 1) {
        return Respuesta.error("El modelo no es un año válido");
    }
    if (cilindrajeInt <= 0) {
        return Respuesta.error("El cilindraje debe ser mayor a 0");
    }

    return registrarMoto.execute(placa, marca, modeloInt, idCliente, cilindrajeInt);
}

}
