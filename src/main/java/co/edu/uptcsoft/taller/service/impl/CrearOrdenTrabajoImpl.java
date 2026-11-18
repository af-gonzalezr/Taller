package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.CrearOrdenTrabajo;

import java.time.LocalDateTime;
import java.util.Optional;

public class CrearOrdenTrabajoImpl implements CrearOrdenTrabajo {

    private final Repository<Vehiculo> vehiculoRepository;
    private final Repository<Tecnico> tecnicoRepository;
    private final Repository<OrdenTrabajo> ordenTrabajoRepository;

    public CrearOrdenTrabajoImpl(Repository<Vehiculo> vehiculoRepository, Repository<Tecnico> tecnicoRepository, Repository<OrdenTrabajo> ordenTrabajoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden,
                                LocalDateTime fechaHoraIngreso,
                                String idVehiculo,
                                String idTecnico,
                                String observacionesIngreso) {
        if (ordenTrabajoRepository.buscarPorId(idOrden).isPresent()) {
            throw new ServiceException("Ya existe una Orden con esa id");
        }
        Optional<Tecnico> tOpt = tecnicoRepository.buscarPorId(idTecnico);
        Optional<Vehiculo> vOpt = vehiculoRepository.buscarPorId(idVehiculo);
        if (tOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("El técnico no existe");
        } else if (vOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("El vehiculo no existe");
        }
        OrdenTrabajo o = new OrdenTrabajo(idOrden,
                fechaHoraIngreso,
                null,
                EstadoOrden.EN_PROCESO,
                vOpt.get(),
                tOpt.get(),
                observacionesIngreso,
                "");

        return ordenTrabajoRepository.guardar(o);
    }
}