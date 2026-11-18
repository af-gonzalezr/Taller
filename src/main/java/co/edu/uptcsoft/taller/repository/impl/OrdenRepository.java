package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrdenRepository implements OrdenRepo {

    private final Map<String, OrdenTrabajo> ordenes;

    public OrdenRepository() {
        this.ordenes = new HashMap<>();
    }

    @Override
    public OrdenTrabajo guardar(OrdenTrabajo elemento) {
        if (elemento == null || elemento.getIdOrden() == null || elemento.getIdOrden().isBlank()) {
            return null;
        }
        // Inserta o sobrescribe automáticamente si el idOrden ya existe
        ordenes.put(elemento.getIdOrden(), elemento);
        return elemento;
    }

    @Override
    public Optional<OrdenTrabajo> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ordenes.get(id));
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return ordenes.remove(id) != null;
    }

    @Override
    public List<OrdenTrabajo> buscarPorCliente(String idCliente) {
        if (idCliente == null || idCliente.isBlank()) {
            return List.of();
        }
        return ordenes.values().stream()
                .filter(o -> o.getVehiculo() != null
                        && o.getVehiculo().getCliente() != null
                        && idCliente.equalsIgnoreCase(o.getVehiculo().getCliente().getIdCliente()))
                .toList();
    }

    @Override
    public List<OrdenTrabajo> buscarPorEstado(EstadoOrden estadoOrden) {
        if (estadoOrden == null) {
            return List.of();
        }
        return ordenes.values().stream()
                .filter(o -> o.getEstado() == estadoOrden)
                .toList();
    }

    @Override
    public List<OrdenTrabajo> todas() {
        return List.copyOf(ordenes.values());
    }
}