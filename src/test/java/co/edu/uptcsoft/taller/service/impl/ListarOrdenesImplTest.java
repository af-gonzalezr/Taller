package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ListarOrdenesImplTest {
    private Repository<OrdenTrabajo> repository;
    private ListarOrdenesImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new ListarOrdenesImpl(repository);
    }

    @Test
    void execute() {
        List<OrdenTrabajo> ordenes = Collections.singletonList(new OrdenTrabajo("1", null, null, null));
        when(repository.listar()).thenReturn(ordenes);
        assertEquals(ordenes, service.execute());
    }
}
