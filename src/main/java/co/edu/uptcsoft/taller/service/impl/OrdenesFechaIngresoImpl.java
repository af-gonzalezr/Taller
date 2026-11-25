package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesFechaIngreso;

import java.time.LocalDate;
import java.util.List;

public class OrdenesFechaIngresoImpl implements OrdenesFechaIngreso {

    private final Repository<OrdenTrabajo> ordenRepository;

    public OrdenesFechaIngresoImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null) {
            throw new ServiceException("Las fechas inicial y final son obligatorias.");
        }
        if (desde.isAfter(hasta)) {
            throw new ServiceException("La fecha inicial no puede ser posterior a la fecha final.");
        }

        return ordenRepository.filtrar(orden -> {
            if (orden.getFechaHoraIngreso() == null) {
                return false;
            }
            LocalDate fechaIngreso = orden.getFechaHoraIngreso().toLocalDate();
            return !fechaIngreso.isBefore(desde) && !fechaIngreso.isAfter(hasta);
        });
    }
}
