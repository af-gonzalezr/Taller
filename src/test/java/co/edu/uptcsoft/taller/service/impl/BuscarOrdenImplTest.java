package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarOrdenImplTest {
    private Repository<OrdenTrabajo> ordenRepository;
    private BuscarOrdenImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenRepository = mock(Repository.class);
        service = new BuscarOrdenImpl(ordenRepository);
    }

    @Test
    void encuentraExitosamente() {
        OrdenTrabajo orden = new OrdenTrabajo("O1", LocalDateTime.now(), null, EstadoOrden.EN_PROCESO, mock(Vehiculo.class), mock(Tecnico.class), "Revisión", null);
        when(ordenRepository.buscarPorId("O1")).thenReturn(Optional.of(orden));

        OrdenTrabajo resultado = service.execute("O1");

        assertEquals(orden, resultado);
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(ordenRepository.buscarPorId("O1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("O1"));
    }
}
