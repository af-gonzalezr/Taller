package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.TodasOrdenes;

import java.util.List;

public class TodasOrdenesImpl implements TodasOrdenes {
    private  final OrdenRepo ordenRepository;

    public TodasOrdenesImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenTrabajo> execute() {
        return ordenRepository.todas();
    }
}
