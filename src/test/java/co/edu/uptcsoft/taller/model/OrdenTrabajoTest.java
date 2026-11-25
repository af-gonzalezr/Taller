package co.edu.uptcsoft.taller.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class OrdenTrabajoTest {

    @Test
    void normalizesNullOrBlankEntryObservationsToEmptyText() {
        OrdenTrabajo nullObservations = new OrdenTrabajo("O1", LocalDateTime.now(), null,
                EstadoOrden.EN_PROCESO, mock(Vehiculo.class), mock(Tecnico.class), null, "");
        OrdenTrabajo blankObservations = new OrdenTrabajo("O2", LocalDateTime.now(), null,
                EstadoOrden.EN_PROCESO, mock(Vehiculo.class), mock(Tecnico.class), "   ", "");

        assertEquals("", nullObservations.getObservacionesIngreso());
        assertEquals("", blankObservations.getObservacionesIngreso());
    }
}
