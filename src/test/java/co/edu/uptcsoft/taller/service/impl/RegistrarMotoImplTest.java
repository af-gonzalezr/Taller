package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrarMotoImplTest {
    private Repository<Cliente> clienteRepository;
    private Repository<Vehiculo> vehiculoRepository;
    private RegistrarMotoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        clienteRepository = mock(Repository.class);
        vehiculoRepository = mock(Repository.class);
        service = new RegistrarMotoImpl(vehiculoRepository, clienteRepository);
    }

    @Test
    void registraExitosamente() {
        String placa = "ABC-123";
        String idCliente = "1";

        when(vehiculoRepository.buscarPorId(placa)).thenReturn(Optional.empty());
        when(clienteRepository.buscarPorId(idCliente)).thenReturn(Optional.of(new Cliente(idCliente, "Ana", "3001234567", "ana@dominio.com")));

        Motocicleta resultado = service.execute(placa, "Yamaha", 2024, idCliente, 250);

        assertNotNull(resultado);
        verify(vehiculoRepository).guardar(any(Motocicleta.class));
    }

    @Test
    void lanzaExcepcionSiYaExiste() {
        String placa = "ABC-123";
        when(vehiculoRepository.buscarPorId(placa)).thenReturn(Optional.of(mock(Vehiculo.class)));

        assertThrows(ServiceException.class, () -> service.execute(placa, "Yamaha", 2024, "1", 250));
    }

    @Test
    void lanzaExcepcionSiClienteNoExiste() {
        String placa = "ABC-123";
        String idCliente = "1";

        when(vehiculoRepository.buscarPorId(placa)).thenReturn(Optional.empty());
        when(clienteRepository.buscarPorId(idCliente)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.execute(placa, "Yamaha", 2024, idCliente, 250));
    }
}
