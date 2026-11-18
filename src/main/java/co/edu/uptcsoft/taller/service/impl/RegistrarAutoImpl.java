package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarAuto;

import java.util.Optional;

public class RegistrarAutoImpl implements RegistrarAuto {

    private final Repository<Cliente> clienteRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public RegistrarAutoImpl(Repository<Cliente> clienteRepository, Repository<Vehiculo> vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Automovil execute(String placa, String marca, int modelo, String idCliente, int numeroPuertas) {

        if(vehiculoRepository.buscarPorId(placa).isPresent()){
            throw new BusinessRuleException("Placa ya registrada");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if (cOpt.isEmpty()){
            throw new ResourceNotFoundException("El cliente no existe");
        }
        Cliente c = cOpt.get();
        Automovil a = new Automovil(placa, marca, modelo, c, numeroPuertas);
        return (Automovil) vehiculoRepository.guardar(a);

    }
}
