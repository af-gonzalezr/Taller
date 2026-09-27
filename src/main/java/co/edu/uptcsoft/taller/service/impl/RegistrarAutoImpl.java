package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class RegistrarAutoImpl implements RegistrarAuto {

    private final Repository<Cliente> clienteRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public RegistrarAutoImpl(Repository<Cliente> clienteRepository, Repository<Vehiculo> vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Respuesta<Automovil> execute(String placa, String marca, int modelo, String idCliente, int numeroPuertas) {

        if(vehiculoRepository.buscarPorId(placa).isPresent()){
            return Respuesta.error("Placa ya registrada");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if (cOpt.isEmpty()){
            return Respuesta.error("El cliente no existe");
        }
        Cliente c = cOpt.get();
        Automovil automovil = new Automovil(placa, marca, modelo, c, numeroPuertas);
        vehiculoRepository.guardar(automovil);
        return Respuesta.completado("Automóvil registrado exitosamente",automovil);
    }
}
