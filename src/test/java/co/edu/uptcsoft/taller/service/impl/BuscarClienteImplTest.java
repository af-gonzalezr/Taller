package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class BuscarClienteImplTest {
    private Optional<Cliente> encontrado;
    private BuscarClienteImpl service;

    @BeforeEach
    void setUp() {
        encontrado = Optional.empty();
        Repository<Cliente> clienteRepository = new Repository<>() {
            @Override public Cliente guardar(Cliente e) { return e; }
            @Override public Optional<Cliente> buscarPorId(String id) { return encontrado; }
            @Override public boolean eliminar(String id) { return false; }
            @Override public List<Cliente> listar() { return List.of(); }
            @Override public List<Cliente> filtrar(Predicate<Cliente> c) { return List.of(); }
        };
        service = new BuscarClienteImpl(clienteRepository);
    }

    @Test
    void encuentraExitosamente() {
        Cliente cliente = new Cliente("1", "Ana", "3001234567", "ana@dominio.com");
        encontrado = Optional.of(cliente);

        Cliente resultado = service.execute("1");

        assertEquals(cliente, resultado);
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1"));
    }
}
