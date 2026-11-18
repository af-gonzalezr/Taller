package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarCliente;

public class RegistrarClienteImpl implements RegistrarCliente {

    private final Repository<Cliente> clienteRepository;

    public RegistrarClienteImpl(Repository<Cliente> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente execute(String idCliente, String nombre, String telefono, String email) {
        if(clienteRepository.buscarPorId(idCliente).isPresent()){
            throw new ServiceException("Ya existe un cliente con esa id");
        }

        Cliente c = new Cliente(idCliente,nombre,telefono,email);
        clienteRepository.guardar(c);
        return c;
    }
}
