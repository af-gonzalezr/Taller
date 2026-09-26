package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface OredenesCliente {
    List<OrdenTrabajo> execute(String idCliente);
}
