package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ActualizarCliente;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class ActualizarClienteImpl implements ActualizarCliente {

    private final Repository<Cliente> clienteRepository;

    public ActualizarClienteImpl(Repository<Cliente> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Respuesta<Cliente> execute(String idCliente, String nuevoNombre, String nuevoTelefono, String nuevoEmail) {
        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if(cOpt.isEmpty()){
            return Respuesta.error("El cliente no existe");
        }
        Cliente c = cOpt.get();
        c.setInfo(nuevoNombre,nuevoTelefono,nuevoEmail);
        return Respuesta.completado("Datos actualizados exitosamente",
                clienteRepository.guardar(c));
    }
}
