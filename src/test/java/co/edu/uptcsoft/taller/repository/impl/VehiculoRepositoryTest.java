package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoRepositoryTest {
    private VehiculoRepository repository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repository = new VehiculoRepository();
        cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
    }

    @Test
    void guardarYBuscar() {
        Vehiculo vehiculo = new Automovil("ABC-123", "Toyota", 2024, cliente, 4);
        repository.guardar(vehiculo);
        Optional<Vehiculo> encontrado = repository.buscarPorId("ABC-123");
        assertTrue(encontrado.isPresent());
        assertEquals(vehiculo, encontrado.get());
    }

    @Test
    void eliminar() {
        Vehiculo vehiculo = new Automovil("ABC-123", "Toyota", 2024, cliente, 4);
        repository.guardar(vehiculo);
        assertTrue(repository.eliminar("ABC-123"));
        assertTrue(repository.buscarPorId("ABC-123").isEmpty());
    }
}
