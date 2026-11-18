package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.FinalizarOrden;

import java.time.LocalDateTime;
import java.util.Optional;

public class FinalizarOrdenImpl implements FinalizarOrden {

    private final OrdenRepo ordenTrabajoRepository;

    public FinalizarOrdenImpl(OrdenRepo ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden, String obsevacionesEntrega) {
        Optional<OrdenTrabajo> ordenOpt = ordenTrabajoRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()){
            throw new ResourceNotFoundException("La orden no existe");
        }
        OrdenTrabajo orden = ordenOpt.get();
        if (orden.getEstado()!= EstadoOrden.EN_PROCESO){
            throw new BusinessRuleException("Solo se pueden finalizar ordenes en proceso");
        }
        orden.setEstado(EstadoOrden.FINALIZADA);
        orden.setObservacionesEntrega(obsevacionesEntrega);
        orden.setFechaHoraEntrega(LocalDateTime.now());
        return ordenTrabajoRepository.guardar(orden);
    }
}
