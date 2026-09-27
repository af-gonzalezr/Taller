package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface OrdenesTecnico {
    List<OrdenTrabajo> execute(String idTecnico);
}
