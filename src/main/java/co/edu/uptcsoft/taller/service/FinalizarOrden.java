package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

public interface FinalizarOrden {
    OrdenTrabajo execute(String idOrden, String obsevacionesEntrega);
}
