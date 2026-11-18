package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesCliente;

import java.util.List;

public class OrdenesClienteImpl implements OrdenesCliente {

    private final Repository<Cliente> clienteRepository;
    private final Repository<OrdenTrabajo> ordenTrabajoRepository;

    public OrdenesClienteImpl(Repository<Cliente> clienteRepository, Repository<OrdenTrabajo> ordenTrabajoRepository) {
        this.clienteRepository = clienteRepository;
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(String idCliente) {
        clienteRepository
                .buscarPorId(idCliente)
                .orElseThrow(()->new ElementoNoEncontradoException("El cliente no existe"));


        return ordenTrabajoRepository
                .filtrar(o -> idCliente
                        .equalsIgnoreCase(o.getVehiculo().getCliente().getId())
                );
    }
}