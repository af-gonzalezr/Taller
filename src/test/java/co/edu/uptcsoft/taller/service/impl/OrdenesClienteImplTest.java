package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrdenesClienteImplTest {
    private Repository<Cliente> clienteRepository;
    private Repository<OrdenTrabajo> ordenRepository;
    private OrdenesClienteImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        clienteRepository = Mockito.mock(Repository.class);
        ordenRepository = Mockito.mock(Repository.class);
        service = new OrdenesClienteImpl(clienteRepository, ordenRepository);
    }

    @Test
    void execute() {
        String idCliente = "1";
        Cliente cliente = new Cliente(idCliente, "Ana", "123", "a@a.com");
        when(clienteRepository.buscarPorId(idCliente)).thenReturn(Optional.of(cliente));

        OrdenTrabajo orden = new OrdenTrabajo("1", new Vehiculo("V1", cliente, "Auto"), null, null);
        List<OrdenTrabajo> ordenes = Collections.singletonList(orden);

        when(ordenRepository.filtrar(any())).thenReturn(ordenes);

        assertEquals(ordenes, service.execute(idCliente));
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(clienteRepository.buscarPorId("1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1"));
    }
}
