package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest {
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ClienteRepository();
    }

    @Test
    void guardarYBuscar() {
        Cliente cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
        repository.guardar(cliente);
        Optional<Cliente> encontrado = repository.buscarPorId("1");
        assertTrue(encontrado.isPresent());
        assertEquals(cliente, encontrado.get());
    }

    @Test
    void eliminar() {
        Cliente cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
        repository.guardar(cliente);
        assertTrue(repository.eliminar("1"));
        assertTrue(repository.buscarPorId("1").isEmpty());
    }
}
