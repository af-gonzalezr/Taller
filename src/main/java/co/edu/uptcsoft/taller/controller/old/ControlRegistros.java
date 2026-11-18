package co.edu.uptcsoft.taller.controller.old;

import co.edu.uptcsoft.taller.exception.ServiceException;
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

    public Cliente crearCliente(String idCliente, String nombre, String telefono, String email) {
        if (estaVacio(idCliente)) {
            throw new ServiceException("El campo idCliente esta vacío");
        }
        if (estaVacio(nombre)) {
            throw new ServiceException("El campo nombre esta vacío");
        }
        if (estaVacio(telefono)) {
            throw new ServiceException("El campo teléfono esta vacío");
        }
        if (estaVacio(email)) {
            throw new ServiceException("El campo email esta vacío");
        }

        if (!email.contains("@")) {
            throw new ServiceException("El email debe contener '@'");
        } else if (telefono.charAt(0) != '3') {
            throw new ServiceException("El teléfono debe empezar por 3");
        } else if (telefono.length() != 10) {
            throw new ServiceException("El teléfono debe tener 10 dígitos");
        }

        return registrarCliente.execute(idCliente.trim(), nombre.trim(), telefono.trim(), email.trim());
    }

    public Tecnico crearTecnico(String idTecnico, String nombre, String especialidad) {
        if (estaVacio(idTecnico)) {
            throw new ServiceException("El campo idTecnico esta vacío");
        }
        if (estaVacio(nombre)) {
            throw new ServiceException("El campo nombre esta vacío");
        }
        if (estaVacio(especialidad)) {
            throw new ServiceException("El campo especialidad esta vacío");
        }

        return registrarTecnico.execute(idTecnico.trim(), nombre.trim(), especialidad.trim());
    }

    public Automovil crearAutomovil(String placa, String marca, String modelo, String idCliente, String numeroPuertas) {
        validarVehiculoBase(placa, marca, modelo, idCliente);

        if (estaVacio(numeroPuertas)) {
            throw new ServiceException("El campo numeroPuertas esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            int puertasInt = Integer.parseInt(numeroPuertas.trim());
            return registrarAuto.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), puertasInt);
        } catch (NumberFormatException e) {
            throw new ServiceException("El modelo y número de puertas deben ser números enteros");
        }
    }

    public Motocicleta crearMotocicleta(String placa, String marca, String modelo, String idCliente, String cilindraje) {
        validarVehiculoBase(placa, marca, modelo, idCliente);
        if (estaVacio(cilindraje)) {
            throw new ServiceException("El campo cilindraje esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            int cilindrajeInt = Integer.parseInt(cilindraje.trim());
            return registrarMoto.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), cilindrajeInt);
        } catch (NumberFormatException e) {
            throw new ServiceException("El modelo y cilindraje deben ser números enteros");
        }
    }

    public Camion crearCamion(String placa, String marca, String modelo, String idCliente, String capacidadCargaTon) {
        validarVehiculoBase(placa, marca, modelo, idCliente);
        if (estaVacio(capacidadCargaTon)) {
            throw new ServiceException("El campo capacidadCargaTon esta vacío");
        }

        try {
            int modeloInt = Integer.parseInt(modelo.trim());
            double capacidadDouble = Double.parseDouble(capacidadCargaTon.trim());
            return registrarCamion.execute(placa.trim(), marca.trim(), modeloInt, idCliente.trim(), capacidadDouble);
        } catch (NumberFormatException e) {
            throw new ServiceException("El modelo debe ser entero y la capacidad de carga un número válido");
        }
    }

    private void validarVehiculoBase(String placa, String marca, String modelo, String idCliente) {
        if (estaVacio(placa)) {
            throw new ServiceException("El campo placa esta vacío");
        }
        if (estaVacio(marca)) {
            throw new ServiceException("El campo marca esta vacío");
        }
        if (estaVacio(modelo)) {
            throw new ServiceException("El campo modelo esta vacío");
        }
        if (estaVacio(idCliente)) {
            throw new ServiceException("El campo idCliente esta vacío");
        }
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}