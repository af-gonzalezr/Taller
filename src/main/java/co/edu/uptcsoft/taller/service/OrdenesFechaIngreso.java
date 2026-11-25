package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.time.LocalDate;
import java.util.List;

public interface OrdenesFechaIngreso {
    List<OrdenTrabajo> execute(LocalDate desde, LocalDate hasta);
}
