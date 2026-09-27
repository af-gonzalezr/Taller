package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface OrdenesCliente {
    Respuesta<List<OrdenTrabajo>> execute(String idCliente);
}
