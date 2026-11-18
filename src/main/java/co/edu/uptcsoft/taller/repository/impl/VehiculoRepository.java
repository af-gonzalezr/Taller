package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehiculoRepository implements Repository<Vehiculo> {

    private final Map<String, Vehiculo> vehiculos;

    public VehiculoRepository() {
        this.vehiculos = new HashMap<>();
    }

    @Override
    public Vehiculo guardar(Vehiculo elemento) {
        if (elemento == null || elemento.getPlaca() == null || elemento.getPlaca().isBlank()) {
            return null;
        }
        String placaFormateada = elemento.getPlaca().trim().toUpperCase();
        vehiculos.put(placaFormateada, elemento);
        return elemento;
    }

    @Override
    public Optional<Vehiculo> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(vehiculos.get(id.trim().toUpperCase()));
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return vehiculos.remove(id.trim().toUpperCase()) != null;
    }
}