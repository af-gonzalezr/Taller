package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.ServicioRealizado;
import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.AgregarTrabajoAOrden;

import java.util.Optional;

public class AgregarTrabajoAOrdenImpl implements AgregarTrabajoAOrden {

    private final OrdenRepo ordenTrabajoRepository;

    public AgregarTrabajoAOrdenImpl(OrdenRepo ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public ServicioRealizado execute(String idOrden, String descripcion, double valor) {
        Optional<OrdenTrabajo> ordenOpt = ordenTrabajoRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()){
            throw new ResourceNotFoundException("La orden no existe");
        }
        OrdenTrabajo orden = ordenOpt.get();
        if(orden.getEstado() != EstadoOrden.EN_PROCESO){
            throw new BusinessRuleException("Solo se pueden agregar trabajos a órdenes que estén en proceso");
        }
        ServicioRealizado servicioRealizado = new ServicioRealizado(descripcion,valor);
        orden.agregarTrabajo(servicioRealizado);
        ordenTrabajoRepository.guardar(orden);
        return servicioRealizado;
    }
}
