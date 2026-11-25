package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface CalcularTotalOrdenes {
    double execute(List<OrdenTrabajo> ordenes);
}
