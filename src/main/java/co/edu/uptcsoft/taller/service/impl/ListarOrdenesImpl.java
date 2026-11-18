package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ListarOrdenes;

import java.util.List;

public class ListarOrdenesImpl implements ListarOrdenes {
    private  final Repository<OrdenTrabajo> ordenRepository;

    public ListarOrdenesImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenTrabajo> execute() {
        return ordenRepository.listar();
    }
}
