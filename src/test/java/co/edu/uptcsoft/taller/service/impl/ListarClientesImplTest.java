package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListarClientesImplTest {
    private ListarClientesImpl service;
    private List<Cliente> lista;

    @BeforeEach
    void setUp() {
        lista = List.of(new Cliente("1", "Ana", "123", "a@a.com"));
        Repository<Cliente> repository = new Repository<>() {
            @Override public Cliente guardar(Cliente e) { return null; }
            @Override public Optional<Cliente> buscarPorId(String id) { return Optional.empty(); }
            @Override public boolean eliminar(String id) { return false; }
            @Override public List<Cliente> listar() { return lista; }
            @Override public List<Cliente> filtrar(Predicate<Cliente> c) { return List.of(); }
        };
        service = new ListarClientesImpl(repository);
    }

    @Test
    void execute() {
        assertEquals(lista, service.execute());
    }
}
