package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.controller.VehiculoController;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Camion;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.service.BuscarVehiculo;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.VehiculosCliente;

import java.util.List;

public class VehiculoControllerImpl implements VehiculoController {
    private final RegistrarAuto registrarAuto;
    private final RegistrarMoto registrarMoto;
    private final RegistrarCamion registrarCamion;
    private final BuscarVehiculo buscarVehiculo;
    private final VehiculosCliente vehiculosCliente;

    public VehiculoControllerImpl(RegistrarAuto registrarAuto,
                                  RegistrarMoto registrarMoto,
                                  RegistrarCamion registrarCamion,
                                  BuscarVehiculo buscarVehiculo,
                                  VehiculosCliente vehiculosCliente) {
        this.registrarAuto = registrarAuto;
        this.registrarMoto = registrarMoto;
        this.registrarCamion = registrarCamion;
        this.buscarVehiculo = buscarVehiculo;
        this.vehiculosCliente = vehiculosCliente;
    }

    @Override
    public Automovil registrarAuto(String placa, String marca, String modelo, String idCliente, String numeroPuertas) {
        String normalizedPlate = ControllerValidation.requiredText(placa, "placa");
        String normalizedBrand = ControllerValidation.requiredText(marca, "marca");
        int normalizedModel = ControllerValidation.positiveInt(modelo, "modelo");
        String normalizedClientId = ControllerValidation.requiredText(idCliente, "idCliente");
        int normalizedDoors = ControllerValidation.positiveInt(numeroPuertas, "numeroPuertas");
        return registrarAuto.execute(normalizedPlate, normalizedBrand, normalizedModel,
                normalizedClientId, normalizedDoors);
    }

    @Override
    public Motocicleta registrarMoto(String placa, String marca, String modelo, String idCliente, String cilindraje) {
        String normalizedPlate = ControllerValidation.requiredText(placa, "placa");
        String normalizedBrand = ControllerValidation.requiredText(marca, "marca");
        int normalizedModel = ControllerValidation.positiveInt(modelo, "modelo");
        String normalizedClientId = ControllerValidation.requiredText(idCliente, "idCliente");
        int normalizedDisplacement = ControllerValidation.positiveInt(cilindraje, "cilindraje");
        return registrarMoto.execute(normalizedPlate, normalizedBrand, normalizedModel,
                normalizedClientId, normalizedDisplacement);
    }

    @Override
    public Camion registrarCamion(String placa, String marca, String modelo, String idCliente, String capacidadCarga) {
        String normalizedPlate = ControllerValidation.requiredText(placa, "placa");
        String normalizedBrand = ControllerValidation.requiredText(marca, "marca");
        int normalizedModel = ControllerValidation.positiveInt(modelo, "modelo");
        String normalizedClientId = ControllerValidation.requiredText(idCliente, "idCliente");
        double normalizedCapacity = ControllerValidation.positiveDouble(capacidadCarga, "capacidadCarga");
        return registrarCamion.execute(normalizedPlate, normalizedBrand, normalizedModel,
                normalizedClientId, normalizedCapacity);
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        return buscarVehiculo.execute(ControllerValidation.requiredText(placa, "placa"));
    }

    @Override
    public List<Vehiculo> listarPorCliente(String idCliente) {
        return vehiculosCliente.execute(
                ControllerValidation.requiredText(idCliente, "idCliente"));
    }
}
