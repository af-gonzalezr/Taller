package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarMoto;

import java.util.Optional;

public class RegistrarMotoImpl implements RegistrarMoto {

    private final Repository<Vehiculo> vehiculoRepository;
    private final Repository<Cliente> clienteRepository;

    public RegistrarMotoImpl(Repository<Vehiculo> vehiculoRepository, Repository<Cliente> clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Motocicleta execute(String placa, String marca, int modelo, String idCliente, int cilindraje) {
        if (modelo <= 0 || cilindraje <= 0) {
            throw new ServiceException("El modelo y el cilindraje deben ser positivos");
        }

        if(vehiculoRepository.buscarPorId(placa).isPresent()){
            throw new ServiceException("La placa ya esta en uso");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);

        if (cOpt.isEmpty()){
            throw new ElementoNoEncontradoException("El cliente no existe");
        }

        Cliente c = cOpt.get();
        Motocicleta m = new Motocicleta(placa, marca, modelo, c, cilindraje);
        return (Motocicleta) vehiculoRepository.guardar(m);
    }
}
