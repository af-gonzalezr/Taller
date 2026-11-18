package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClienteRepository implements Repository<Cliente> {

    private final Map<String, Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new HashMap<>();
    }

    @Override
    public Cliente guardar(Cliente elemento) {
        if (elemento == null || elemento.getIdCliente() == null || elemento.getIdCliente().isBlank()) {
            return null;
        }
        clientes.put(elemento.getIdCliente(), elemento);
        return elemento;
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public boolean eliminar(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return clientes.remove(id) != null;
    }
}