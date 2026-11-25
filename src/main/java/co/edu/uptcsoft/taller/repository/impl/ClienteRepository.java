package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ClienteRepository implements Repository<Cliente> {

    private final Map<String, Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new HashMap<>();
    }

    @Override
    public Cliente guardar(Cliente elemento) {
        if (elemento == null || elemento.getId() == null || elemento.getId().isBlank()) {
            return null;
        }
        clientes.put(elemento.getId().trim().toUpperCase(), elemento);
        return elemento;
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(clientes.get(id.trim().toUpperCase()));
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return clientes.remove(id.trim().toUpperCase()) != null;
    }

    @Override
    public List<Cliente> listar() {
        return List.copyOf(clientes.values());
    }

    @Override
    public List<Cliente> filtrar(Predicate<Cliente> criterio) {
        if (criterio == null) {
            return listar();
        }
        return clientes.values().stream()
                .filter(criterio)
                .toList();
    }
}