package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.BuscarVehiculo;

public class BuscarVehiculoImpl implements BuscarVehiculo {
    private final Repository<Vehiculo> vehiculoRepository;

    public BuscarVehiculoImpl(Repository<Vehiculo> vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Vehiculo execute(String placa) {
        return vehiculoRepository.buscarPorId(placa)
                .orElseThrow(() -> new ElementoNoEncontradoException("El vehiculo no existe"));
    }
}
