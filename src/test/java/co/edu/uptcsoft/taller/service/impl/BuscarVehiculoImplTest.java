package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarVehiculoImplTest {
    private Repository<Vehiculo> vehiculoRepository;
    private BuscarVehiculoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        vehiculoRepository = mock(Repository.class);
        service = new BuscarVehiculoImpl(vehiculoRepository);
    }

    @Test
    void encuentraExitosamente() {
        Vehiculo vehiculo = new Automovil("ABC-123", "Toyota", 2024, null, 4);
        when(vehiculoRepository.buscarPorId("ABC-123")).thenReturn(Optional.of(vehiculo));

        Vehiculo resultado = service.execute("ABC-123");

        assertEquals(vehiculo, resultado);
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(vehiculoRepository.buscarPorId("ABC-123")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("ABC-123"));
    }
}
