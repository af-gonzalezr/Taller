package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.service.BuscarVehiculo;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.VehiculosCliente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehiculoControllerImplTest {
    private VehiculoControllerImpl controllerWith(RegistrarAuto registrarAuto,
                                                   RegistrarMoto registrarMoto,
                                                   RegistrarCamion registrarCamion,
                                                   BuscarVehiculo buscarVehiculo,
                                                   VehiculosCliente vehiculosCliente) {
        return new VehiculoControllerImpl(registrarAuto, registrarMoto, registrarCamion,
                buscarVehiculo, vehiculosCliente);
    }

    @Test
    void rejectsInvalidVehicleInputBeforeDelegation() {
        VehiculoControllerImpl controller = controllerWith(
                (placa, marca, modelo, idCliente, puertas) -> null,
                (placa, marca, modelo, idCliente, cilindraje) -> null,
                (placa, marca, modelo, idCliente, carga) -> null,
                placa -> null,
                idCliente -> List.of());

        assertThrows(ValidationException.class,
                () -> controller.registrarAuto(" ABC-123 ", "Toyota", "2024x", "1", "4"));
        assertThrows(ValidationException.class,
                () -> controller.registrarCamion(" ABC-123 ", "Volvo", "2024", "1", "10d"));
    }

    @Test
    void delegatesNormalizedAndConvertedVehicleValues() {
        final int[] modelAndDoors = new int[2];
        final String[] plateAndClient = new String[2];
        Cliente cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
        RegistrarAuto registrarAuto = (placa, marca, modelo, idCliente, puertas) -> {
            plateAndClient[0] = placa;
            plateAndClient[1] = idCliente;
            modelAndDoors[0] = modelo;
            modelAndDoors[1] = puertas;
            return new Automovil(placa, marca, modelo, cliente, puertas);
        };
        VehiculoControllerImpl controller = controllerWith(
                registrarAuto,
                (placa, marca, modelo, idCliente, cilindraje) -> null,
                (placa, marca, modelo, idCliente, carga) -> null,
                placa -> null,
                idCliente -> List.of());

        controller.registrarAuto(" ABC-123 ", " Toyota ", " 2024 ", " 1 ", " 4 ");

        assertEquals("ABC-123", plateAndClient[0]);
        assertEquals("1", plateAndClient[1]);
        assertEquals(2024, modelAndDoors[0]);
        assertEquals(4, modelAndDoors[1]);
    }
}
