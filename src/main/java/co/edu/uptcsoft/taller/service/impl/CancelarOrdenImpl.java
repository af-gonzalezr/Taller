package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.CancelarOrden;

import java.util.Optional;

public class CancelarOrdenImpl implements CancelarOrden {

    private final Repository<OrdenTrabajo> ordenRepository;

    public CancelarOrdenImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden) {
        Optional<OrdenTrabajo> ordenOpt = ordenRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("La orden no existe");
        }
        OrdenTrabajo o = ordenOpt.get();
        if (o.getEstado() != EstadoOrden.EN_PROCESO) {
            throw new ServiceException("Solo se pueden cancelar ordenes en proceso");
        }
        o.setEstado(EstadoOrden.CANCELADA);
        return ordenRepository.guardar(o);
    }
}