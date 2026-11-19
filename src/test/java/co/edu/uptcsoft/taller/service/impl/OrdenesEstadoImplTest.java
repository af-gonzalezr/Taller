package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrdenesEstadoImplTest {
    private Repository<OrdenTrabajo> repository;
    private OrdenesEstadoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new OrdenesEstadoImpl(repository);
    }

    @Test
    void execute() {
        EstadoOrden estado = EstadoOrden.PENDIENTE;
        OrdenTrabajo orden = new OrdenTrabajo("1", null, null, estado);
        List<OrdenTrabajo> ordenes = Collections.singletonList(orden);

        when(repository.filtrar(any())).thenReturn(ordenes);

        assertEquals(ordenes, service.execute(estado));
    }
}
