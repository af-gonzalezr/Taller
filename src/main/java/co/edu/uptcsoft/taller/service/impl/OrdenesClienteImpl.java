package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesCliente;

import java.util.List;

public class OrdenesClienteImpl implements OrdenesCliente {

    private final Repository<Cliente> clienteRepository;
    private final OrdenRepo ordenTrabajoRepository;

    public OrdenesClienteImpl(Repository<Cliente> clienteRepository, OrdenRepo ordenTrabajoRepository) {
        this.clienteRepository = clienteRepository;
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(String idCliente) {
        if(clienteRepository.buscarPorId(idCliente).isEmpty()){
            throw new ResourceNotFoundException("El cliente no existe");
        }
        List<OrdenTrabajo> ordenes = ordenTrabajoRepository.buscarPorCliente(idCliente);
        return ordenes;
    }
}
