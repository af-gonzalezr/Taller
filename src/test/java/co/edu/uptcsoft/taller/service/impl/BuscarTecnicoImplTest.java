package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarTecnicoImplTest {
    private Repository<Tecnico> tecnicoRepository;
    private BuscarTecnicoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        tecnicoRepository = mock(Repository.class);
        service = new BuscarTecnicoImpl(tecnicoRepository);
    }

    @Test
    void encuentraExitosamente() {
        Tecnico tecnico = new Tecnico("T1", "Pedro", "Mecánica");
        when(tecnicoRepository.buscarPorId("T1")).thenReturn(Optional.of(tecnico));

        Tecnico resultado = service.execute("T1");

        assertEquals(tecnico, resultado);
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(tecnicoRepository.buscarPorId("T1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("T1"));
    }
}
