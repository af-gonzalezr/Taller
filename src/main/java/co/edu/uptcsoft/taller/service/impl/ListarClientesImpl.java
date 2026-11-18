package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ListarClientes;

import java.util.List;

public class ListarClientesImpl implements ListarClientes {

    private final Repository<Cliente> clienteRepository;

    public ListarClientesImpl(Repository<Cliente> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> execute() {
        return clienteRepository.listar();
    }
}
