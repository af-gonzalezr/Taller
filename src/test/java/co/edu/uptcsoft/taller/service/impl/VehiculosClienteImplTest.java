package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VehiculosClienteImplTest {
    private Repository<Cliente> clienteRepository;
    private Repository<Vehiculo> vehiculoRepository;
    private VehiculosClienteImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        clienteRepository = Mockito.mock(Repository.class);
        vehiculoRepository = Mockito.mock(Repository.class);
        service = new VehiculosClienteImpl(clienteRepository, vehiculoRepository);
    }

    @Test
    void execute() {
        String idCliente = "1";
        Cliente cliente = new Cliente(idCliente, "Ana", "123", "a@a.com");
        when(clienteRepository.buscarPorId(idCliente)).thenReturn(Optional.of(cliente));

        Vehiculo vehiculo = new Vehiculo("V1", cliente, "Auto");
        List<Vehiculo> vehiculos = Collections.singletonList(vehiculo);

        when(vehiculoRepository.filtrar(any())).thenReturn(vehiculos);

        assertEquals(vehiculos, service.execute(idCliente));
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(clienteRepository.buscarPorId("1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1"));
    }
}
