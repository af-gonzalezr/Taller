package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalcularTotalOrdenesImplTest {

    @Test
    void excludesCancelledOrdersFromTotal() {
        OrdenTrabajo activa = orden(EstadoOrden.EN_PROCESO, 125.0);
        OrdenTrabajo finalizada = orden(EstadoOrden.FINALIZADA, 75.0);
        OrdenTrabajo cancelada = orden(EstadoOrden.CANCELADA, 900.0);

        assertEquals(200.0, new CalcularTotalOrdenesImpl()
                .execute(List.of(activa, finalizada, cancelada)));
    }

    @Test
    void rejectsNullOrderList() {
        assertThrows(ServiceException.class,
                () -> new CalcularTotalOrdenesImpl().execute(null));
    }

    private OrdenTrabajo orden(EstadoOrden estado, double costo) {
        OrdenTrabajo orden = mock(OrdenTrabajo.class);
        when(orden.getEstado()).thenReturn(estado);
        when(orden.calcularCostoTotal()).thenReturn(costo);
        return orden;
    }
}
