package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class OrdenRepository implements Repository<OrdenTrabajo> {

    private final Map<String, OrdenTrabajo> ordenes;

    public OrdenRepository() {
        this.ordenes = new HashMap<>();
    }

    @Override
    public OrdenTrabajo guardar(OrdenTrabajo elemento) {
        if (elemento == null || elemento.getIdOrden() == null || elemento.getIdOrden().isBlank()) {
            return null;
        }
        ordenes.put(elemento.getIdOrden(), elemento);
        return elemento;
    }

    @Override
    public Optional<OrdenTrabajo> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return ordenes.entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(id.trim()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        String clave = ordenes.keySet().stream()
                .filter(key -> key.equalsIgnoreCase(id.trim()))
                .findFirst()
                .orElse(null);
        return clave != null && ordenes.remove(clave) != null;
    }

    @Override
    public List<OrdenTrabajo> listar() {
        return List.copyOf(ordenes.values());
    }

    @Override
    public List<OrdenTrabajo> filtrar(Predicate<OrdenTrabajo> criterio) {
        if (criterio == null) {
            return listar();
        }
        return ordenes.values().stream()
                .filter(criterio)
                .toList();
    }
}