package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrarTecnicoImplTest {
    private Repository<Tecnico> tecnicoRepository;
    private RegistrarTecnicoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        tecnicoRepository = mock(Repository.class);
        service = new RegistrarTecnicoImpl(tecnicoRepository);
    }

    @Test
    void registraExitosamente() {
        when(tecnicoRepository.buscarPorId("T1")).thenReturn(Optional.empty());

        Tecnico resultado = service.execute("T1", "Pedro", "Mecánica");

        assertNotNull(resultado);
        verify(tecnicoRepository).guardar(resultado);
    }

    @Test
    void lanzaExcepcionSiYaExiste() {
        when(tecnicoRepository.buscarPorId("T1")).thenReturn(Optional.of(new Tecnico("T1", "Pedro", "Mecánica")));

        assertThrows(ServiceException.class, () -> service.execute("T1", "Pedro", "Mecánica"));
    }
}
