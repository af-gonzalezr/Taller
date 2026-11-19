package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.QuitarTrabajoDeOrden;

import java.util.List;

public class QuitarTrabajoDeOrdenImpl implements QuitarTrabajoDeOrden {

    private final Repository<OrdenTrabajo> ordenRepository;

    public QuitarTrabajoDeOrdenImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden, int indiceTrabajo) {
        if (idOrden == null || idOrden.isBlank()) {
            throw new ServiceException("El ID de la orden de trabajo es obligatorio.");
        }

        OrdenTrabajo orden = ordenRepository.buscarPorId(idOrden)
                .orElseThrow(() -> new ElementoNoEncontradoException("La orden no existe"));

        if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
            throw new ServiceException("Solo se pueden quitar trabajos de órdenes en proceso");
        }

        List<TrabajoRealizado> trabajos = orden.getListaTrabajos();
        if (trabajos == null || indiceTrabajo < 0 || indiceTrabajo >= trabajos.size()) {
            throw new ServiceException("El índice del trabajo no es válido");
        }

        orden.eliminarTrabajo(indiceTrabajo);
        return ordenRepository.guardar(orden);
    }
}