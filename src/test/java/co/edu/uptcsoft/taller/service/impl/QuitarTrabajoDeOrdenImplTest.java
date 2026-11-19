package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class QuitarTrabajoDeOrdenImplTest {
    private Repository<OrdenTrabajo> repository;
    private QuitarTrabajoDeOrdenImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new QuitarTrabajoDeOrdenImpl(repository);
    }

    @Test
    void execute() {
        String idOrden = "1";
        List<TrabajoRealizado> trabajos = new ArrayList<>();
        trabajos.add(new TrabajoRealizado("T1", 100));
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, null, null, EstadoOrden.EN_PROCESO);
        orden.setListaTrabajos(trabajos);

        when(repository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));
        when(repository.guardar(any())).thenReturn(orden);

        OrdenTrabajo resultado = service.execute(idOrden, 0);

        assertTrue(resultado.getListaTrabajos().isEmpty());
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(repository.buscarPorId("1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1", 0));
    }

    @Test
    void lanzaExcepcionSiNoEnProceso() {
        OrdenTrabajo orden = new OrdenTrabajo("1", null, null, EstadoOrden.PENDIENTE);
        when(repository.buscarPorId("1")).thenReturn(Optional.of(orden));
        assertThrows(ServiceException.class, () -> service.execute("1", 0));
    }
}
