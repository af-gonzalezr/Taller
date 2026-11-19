package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrdenRepositoryTest {
    private OrdenRepository repository;
    private Vehiculo vehiculo;
    private Tecnico tecnico;

    @BeforeEach
    void setUp() {
        repository = new OrdenRepository();
        Cliente cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
        vehiculo = new Automovil("ABC-123", "Toyota", 2024, cliente, 4);
        tecnico = new Tecnico("T1", "Pedro", "Mecánica");
    }

    @Test
    void guardarYBuscar() {
        OrdenTrabajo orden = new OrdenTrabajo("O1", LocalDateTime.now(), null, EstadoOrden.EN_PROCESO, vehiculo, tecnico, "Revisión", null);
        repository.guardar(orden);
        Optional<OrdenTrabajo> encontrada = repository.buscarPorId("O1");
        assertTrue(encontrada.isPresent());
        assertEquals(orden, encontrada.get());
    }

    @Test
    void eliminar() {
        OrdenTrabajo orden = new OrdenTrabajo("O1", LocalDateTime.now(), null, EstadoOrden.EN_PROCESO, vehiculo, tecnico, "Revisión", null);
        repository.guardar(orden);
        assertTrue(repository.eliminar("O1"));
        assertTrue(repository.buscarPorId("O1").isEmpty());
    }
}
