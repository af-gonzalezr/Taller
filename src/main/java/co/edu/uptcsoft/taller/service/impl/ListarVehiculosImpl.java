package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ListarVehiculos;

import java.util.List;

public class ListarVehiculosImpl implements ListarVehiculos {

    private final Repository<Vehiculo> vehiculoRepository;

    public ListarVehiculosImpl(Repository<Vehiculo> vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> execute() {
        return vehiculoRepository.listar();
    }
}
