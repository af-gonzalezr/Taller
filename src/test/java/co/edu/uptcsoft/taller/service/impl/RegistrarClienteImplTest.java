package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarClienteImplTest {
    private List<Cliente> guardados;
    private Optional<Cliente> encontrado;
    private RegistrarClienteImpl service;

    @BeforeEach
    void setUp() {
        guardados = new ArrayList<>();
        encontrado = Optional.empty();
        Repository<Cliente> clienteRepository = new Repository<>() {
            @Override public Cliente guardar(Cliente e) { guardados.add(e); return e; }
            @Override public Optional<Cliente> buscarPorId(String id) { return encontrado; }
            @Override public boolean eliminar(String id) { return false; }
            @Override public List<Cliente> listar() { return List.of(); }
            @Override public List<Cliente> filtrar(Predicate<Cliente> c) { return List.of(); }
        };
        service = new RegistrarClienteImpl(clienteRepository);
    }

    @Test
    void registraExitosamente() {
        Cliente resultado = service.execute("1", "Ana", "3001234567", "ana@dominio.com");

        assertNotNull(resultado);
        assertEquals(1, guardados.size());
        assertEquals(resultado, guardados.get(0));
    }

    @Test
    void lanzaExcepcionSiYaExiste() {
        encontrado = Optional.of(new Cliente("1", "Ana", "3001234567", "ana@dominio.com"));

        assertThrows(ServiceException.class, () -> service.execute("1", "Ana", "3001234567", "ana@dominio.com"));
    }
}
