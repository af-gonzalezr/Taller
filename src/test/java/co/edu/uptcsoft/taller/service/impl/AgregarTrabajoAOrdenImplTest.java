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

class AgregarTrabajoAOrdenImplTest {
    private Repository<OrdenTrabajo> ordenTrabajoRepository;
    private AgregarTrabajoAOrdenImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenTrabajoRepository = mock(Repository.class);
        service = new AgregarTrabajoAOrdenImpl(ordenTrabajoRepository);
    }

    @Test
    void agregaExitosamente() {
        String idOrden = "O1";
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, LocalDateTime.now(), null, EstadoOrden.EN_PROCESO, mock(Vehiculo.class), mock(Tecnico.class), "Revisión", null);

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));

        TrabajoRealizado resultado = service.execute(idOrden, "Cambio aceite", 50.0);

        assertNotNull(resultado);
        verify(ordenTrabajoRepository).guardar(orden);
        assertTrue(orden.getListaTrabajos().contains(resultado));
    }

    @Test
    void lanzaExcepcionSiOrdenNoExiste() {
        when(ordenTrabajoRepository.buscarPorId("O1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("O1", "Cambio aceite", 50.0));
    }

    @Test
    void lanzaExcepcionSiOrdenNoEstaEnProceso() {
        String idOrden = "O1";
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, LocalDateTime.now(), null, EstadoOrden.FINALIZADA, mock(Vehiculo.class), mock(Tecnico.class), "Revisión", null);

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));

        assertThrows(ServiceException.class, () -> service.execute(idOrden, "Cambio aceite", 50.0));
    }
}
