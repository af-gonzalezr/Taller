package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.OrdenRepository;
import co.edu.uptcsoft.taller.service.OrdenesCliente;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.List;

public class OrdenesClienteImpl implements OrdenesCliente {

    private final Repository<Cliente> clienteRepository;
    private final OrdenRepo ordenTrabajoRepository;

    public OrdenesClienteImpl(Repository<Cliente> clienteRepository, OrdenRepo ordenTrabajoRepository) {
        this.clienteRepository = clienteRepository;
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public Respuesta<List<OrdenTrabajo>> execute(String idCliente) {
        if(clienteRepository.buscarPorId(idCliente).isEmpty()){
            return Respuesta.error("El cliente no existe");
        }
        return Respuesta.completado("Completado exitosamente",
                ordenTrabajoRepository.buscarPorCliente(idCliente));
    }
}
