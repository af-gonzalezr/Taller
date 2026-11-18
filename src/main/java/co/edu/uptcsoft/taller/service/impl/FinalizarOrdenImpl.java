package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.FinalizarOrden;

import java.time.LocalDateTime;
import java.util.Optional;

public class FinalizarOrdenImpl implements FinalizarOrden {

    private final Repository<OrdenTrabajo> ordenTrabajoRepository;

    public FinalizarOrdenImpl(Repository<OrdenTrabajo> ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden, String obsevacionesEntrega) {
        Optional<OrdenTrabajo> ordenOpt = ordenTrabajoRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("La orden no existe");
        }
        OrdenTrabajo orden = ordenOpt.get();
        if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
            throw new ServiceException("Solo se pueden finalizar ordenes en proceso");
        }
        orden.setEstado(EstadoOrden.FINALIZADA);
        orden.setObservacionesEntrega(obsevacionesEntrega);
        orden.setFechaHoraEntrega(LocalDateTime.now());
        return ordenTrabajoRepository.guardar(orden);
    }
}