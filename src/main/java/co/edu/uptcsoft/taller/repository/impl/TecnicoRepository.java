package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class TecnicoRepository implements Repository<Tecnico> {

    private final Map<String, Tecnico> tecnicos;

    public TecnicoRepository() {
        this.tecnicos = new HashMap<>();
    }

    @Override
    public Tecnico guardar(Tecnico elemento) {
        if (elemento == null || elemento.getId() == null || elemento.getId().isBlank()) {
            return null;
        }
        tecnicos.put(elemento.getId(), elemento);
        return elemento;
    }

    @Override
    public Optional<Tecnico> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return tecnicos.entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(id.trim()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        String clave = tecnicos.keySet().stream()
                .filter(key -> key.equalsIgnoreCase(id.trim()))
                .findFirst()
                .orElse(null);
        return clave != null && tecnicos.remove(clave) != null;
    }

    @Override
    public List<Tecnico> listar() {
        return List.copyOf(tecnicos.values());
    }

    @Override
    public List<Tecnico> filtrar(Predicate<Tecnico> criterio) {
        if (criterio == null) {
            return listar();
        }
        return tecnicos.values().stream()
                .filter(criterio)
                .toList();
    }
}