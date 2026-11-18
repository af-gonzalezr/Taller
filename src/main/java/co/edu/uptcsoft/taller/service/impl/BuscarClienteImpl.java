package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.BuscarCliente;

public class BuscarClienteImpl implements BuscarCliente {

    private final Repository<Cliente> clienteRepository;

    public BuscarClienteImpl(Repository<Cliente> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente execute(String idCliente) {

        return clienteRepository
                .buscarPorId(idCliente)
                .orElseThrow(()-> new ElementoNoEncontradoException("El cliente no existe"));
    }
}
