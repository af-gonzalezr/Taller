package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesVehiculo;
import co.edu.uptcsoft.taller.service.ReasignarTecnicoOrden;

import java.util.List;

public class OrdenesVehiculoImpl implements OrdenesVehiculo {

    private final Repository<OrdenTrabajo> ordenTrabajoRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public OrdenesVehiculoImpl(Repository<OrdenTrabajo> ordenTrabajoRepository, Repository<Vehiculo> vehiculoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(String placa) {
        vehiculoRepository
                .buscarPorId(placa)
                .orElseThrow(()-> new ElementoNoEncontradoException("El vehiculo no existe"));

        return ordenTrabajoRepository.filtrar(o->o.getVehiculo().getPlaca().equalsIgnoreCase(placa));
    }
}
