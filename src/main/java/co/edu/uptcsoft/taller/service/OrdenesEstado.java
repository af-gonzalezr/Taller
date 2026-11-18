package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface OrdenesEstado {
    List<OrdenTrabajo> execute(EstadoOrden estadoOrden);
}
