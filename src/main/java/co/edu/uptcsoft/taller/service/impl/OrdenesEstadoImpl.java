package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesEstado;

import java.util.List;

public class OrdenesEstadoImpl implements OrdenesEstado {

    private final Repository<OrdenTrabajo> ordenRepository;

    public OrdenesEstadoImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(EstadoOrden estadoOrden) {
        return ordenRepository.filtrar(o->o.getEstado() == estadoOrden);
    }
}
