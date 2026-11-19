package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoRepositoryTest {
    private TecnicoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TecnicoRepository();
    }

    @Test
    void guardarYBuscar() {
        Tecnico tecnico = new Tecnico("T1", "Pedro", "Mecánica");
        repository.guardar(tecnico);
        Optional<Tecnico> encontrado = repository.buscarPorId("T1");
        assertTrue(encontrado.isPresent());
        assertEquals(tecnico, encontrado.get());
    }

    @Test
    void eliminar() {
        Tecnico tecnico = new Tecnico("T1", "Pedro", "Mecánica");
        repository.guardar(tecnico);
        assertTrue(repository.eliminar("T1"));
        assertTrue(repository.buscarPorId("T1").isEmpty());
    }
}
