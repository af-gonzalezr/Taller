package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TecnicoRepository implements Repository<Tecnico> {

    private final Map<String, Tecnico> tecnicos;

    public TecnicoRepository() {
        this.tecnicos = new HashMap<>();
    }

    @Override
    public Tecnico guardar(Tecnico elemento) {
        if (elemento == null || elemento.getIdTecnico() == null || elemento.getIdTecnico().isBlank()) {
            return null;
        }
        tecnicos.put(elemento.getIdTecnico(), elemento);
        return elemento;
    }

    @Override
    public Optional<Tecnico> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(tecnicos.get(id));
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return tecnicos.remove(id) != null;
    }
}