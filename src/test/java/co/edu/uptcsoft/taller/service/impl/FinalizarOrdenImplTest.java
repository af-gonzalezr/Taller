package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FinalizarOrdenImplTest {
    private Repository<OrdenTrabajo> ordenTrabajoRepository;
    private FinalizarOrdenImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenTrabajoRepository = mock(Repository.class);
        service = new FinalizarOrdenImpl(ordenTrabajoRepository);
    }

    @Test
    void finalizaExitosamente() {
        String idOrden = "O1";
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, LocalDateTime.now(), null, EstadoOrden.EN_PROCESO, mock(Vehiculo.class), mock(Tecnico.class), "Revisión", null);

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));

        OrdenTrabajo resultado = service.execute(idOrden, "Todo bien");

        assertEquals(EstadoOrden.FINALIZADA, resultado.getEstado());
        assertEquals("Todo bien", resultado.getObservacionesEntrega());
        assertNotNull(resultado.getFechaHoraEntrega());
        verify(ordenTrabajoRepository).guardar(orden);
    }

    @Test
    void lanzaExcepcionSiOrdenNoExiste() {
        when(ordenTrabajoRepository.buscarPorId("O1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("O1", ""));
    }

    @Test
    void lanzaExcepcionSiOrdenNoEstaEnProceso() {
        String idOrden = "O1";
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, LocalDateTime.now(), null, EstadoOrden.CANCELADA, mock(Vehiculo.class), mock(Tecnico.class), "Revisión", null);

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));

        assertThrows(ServiceException.class, () -> service.execute(idOrden, "Observaciones"));
    }

    @Test
    void rejectsNullOrBlankDeliveryObservationsBeforeChangingOrder() {
        assertThrows(ServiceException.class, () -> service.execute("O1", null));
        assertThrows(ServiceException.class, () -> service.execute("O1", "   "));
        verifyNoInteractions(ordenTrabajoRepository);
    }
}
