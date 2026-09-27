package co.edu.uptcsoft.taller.repository;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

import java.util.List;
import java.util.Optional;

public interface OrdenRepo {
    OrdenTrabajo guardar(OrdenTrabajo elemento);
    Optional<OrdenTrabajo> buscarPorId(String id);
    boolean eliminar(String id);
    List<OrdenTrabajo> buscarPorCliente(String idCliente);
}
