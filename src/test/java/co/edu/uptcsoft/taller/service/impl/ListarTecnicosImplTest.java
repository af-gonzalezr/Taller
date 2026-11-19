package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ListarTecnicosImplTest {
    private Repository<Tecnico> repository;
    private ListarTecnicosImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new ListarTecnicosImpl(repository);
    }

    @Test
    void execute() {
        List<Tecnico> tecnicos = Collections.singletonList(new Tecnico("1", "Pepe"));
        when(repository.listar()).thenReturn(tecnicos);
        assertEquals(tecnicos, service.execute());
    }
}
