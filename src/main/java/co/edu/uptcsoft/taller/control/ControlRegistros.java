package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Camion;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;
import co.edu.uptcsoft.taller.service.Respuesta;

public class ControlRegistros {

    private final RegistrarCliente registrarCliente;
    private final RegistrarTecnico registrarTecnico;
    private final RegistrarAuto registrarAuto;
    private final RegistrarMoto registrarMoto;
    private final RegistrarCamion registrarCamion;

    public ControlRegistros(RegistrarCliente registrarCliente,
                            RegistrarTecnico registrarTecnico,
                            RegistrarAuto registrarAuto,
                            RegistrarMoto registrarMoto,
                            RegistrarCamion registrarCamion) {
        this.registrarCliente = registrarCliente;
        this.registrarTecnico = registrarTecnico;
        this.registrarAuto = registrarAuto;
        this.registrarMoto = registrarMoto;
        this.registrarCamion = registrarCamion;
    }

    public Respuesta<Cliente> crearCliente(String idCliente, String nombre, String telefono, String email) {
        if (estaVacio(idCliente)) {
            return Respuesta.error("El campo idCliente esta vacío");
        }
        if (estaVacio(nombre)) {
            return Respuesta.error("El campo nombre esta vacío");
        }
        if (estaVacio(telefono)) {
            return Respuesta.error("El campo teléfono esta vacío");
        }
        if (estaVacio(email)) {
            return Respuesta.error("El campo email esta vacío");
        }

        if (!email.contains("@")) {
            return Respuesta.error("El email debe contener '@'");
        } else if (telefono.charAt(0) != '3') {
            return Respuesta.error("El teléfono debe empezar por 3");
        } else if (telefono.length() != 10) {
            return Respuesta.error("El teléfono debe tener 10 dígitos");
        }

        return registrarCliente.execute(idCliente.trim(), nombre.trim(), telefono.trim(), email.trim());
    }

    public Respuesta<Tecnico> crearTecnico(String idTecnico, String nombre, String especialidad) {
        if (estaVacio(idTecnico)) {
            return Respuesta.error("El campo idTecnico esta vacío");
        }
        if (estaVacio(nombre)) {
            return Respuesta.error("El campo nombre esta vacío");
        }
        if (estaVacio(especialidad)) {
            return Respuesta.error("El campo especialidad esta vacío");
        }

        return registrarTecnico.execute(idTecnico.trim(), nombre.trim(), especialidad.trim());
    }

    public Respuesta<Automovil> crearAutomovil(String placa, String marca, String modelo, String idCliente, String numeroPuertas) {
        Respuesta<?> validacionBase = validarVehiculoBase(placa, marca, modelo, idCliente);

        if (!validacionBase.completado()) {
            return Respuesta.error(validacionBase.mensaje());
        }
        if (estaVacio(numeroPuertas)) {
            return Respuesta.error("El campo numeroPuertas esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            int puertasInt = Integer.parseInt(numeroPuertas.trim());
            return registrarAuto.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), puertasInt);
        } catch (NumberFormatException e) {
            return Respuesta.error("El modelo y número de puertas deben ser números enteros");
        }
    }

    public Respuesta<Motocicleta> crearMotocicleta(String placa, String marca, String modelo, String idCliente, String cilindraje) {
        Respuesta<?> validacionBase = validarVehiculoBase(placa, marca, modelo, idCliente);
        if (!validacionBase.completado()) {
            return Respuesta.error(validacionBase.mensaje());
        }
        if (estaVacio(cilindraje)) {
            return Respuesta.error("El campo cilindraje esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            int cilindrajeInt = Integer.parseInt(cilindraje.trim());
            return registrarMoto.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), cilindrajeInt);
        } catch (NumberFormatException e) {
            return Respuesta.error("El modelo y cilindraje deben ser números enteros");
        }
    }

    public Respuesta<Camion> crearCamion(String placa, String marca, String modelo, String idCliente, String capacidadCargaTon) {
        Respuesta<?> validacionBase = validarVehiculoBase(placa, marca, modelo, idCliente);
        if (!validacionBase.completado()) {
            return Respuesta.error(validacionBase.mensaje());
        }
        if (estaVacio(capacidadCargaTon)) {
            return Respuesta.error("El campo capacidadCargaTon esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            double capacidadDouble = Double.parseDouble(capacidadCargaTon.trim());
            return registrarCamion.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), capacidadDouble);
        } catch (NumberFormatException e) {
            return Respuesta.error("El modelo debe ser entero y la capacidad de carga un número válido");
        }
    }

    private Respuesta<?> validarVehiculoBase(String placa, String marca, String modelo, String idCliente) {
        if (estaVacio(placa)) {
            return Respuesta.error("El campo placa esta vacío");
        }
        if (estaVacio(marca)) {
            return Respuesta.error("El campo marca esta vacío");
        }
        if (estaVacio(modelo)) {
            return Respuesta.error("El campo modelo esta vacío");
        }
        if (estaVacio(idCliente)) {
            return Respuesta.error("El campo idCliente esta vacío");
        }
        return Respuesta.completado("Validado", null);
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}