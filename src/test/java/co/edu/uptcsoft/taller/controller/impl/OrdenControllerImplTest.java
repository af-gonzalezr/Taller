package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.service.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdenControllerImplTest {
    private OrdenControllerImpl emptyController() {
        return new OrdenControllerImpl(
                (id, fecha, vehiculo, tecnico, observaciones) -> null,
                (id, descripcion, valor) -> null,
                (id, indice) -> null,
                (id, tecnico) -> null,
                (id, observaciones) -> null,
                id -> null,
                id -> null,
                () -> List.of(),
                id -> List.of(),
                estado -> List.of(),
                id -> List.of(),
                placa -> List.of());
    }

    @Test
    void rejectsInvalidOrderInputBeforeDelegation() {
        OrdenControllerImpl controller = emptyController();

        assertThrows(ValidationException.class,
                () -> controller.crear(" ORD-1 ", "18/11/2026", "ABC-123", "TEC-1", " "));
        assertThrows(ValidationException.class,
                () -> controller.agregarTrabajo(" ORD-1 ", "Cambio", "10d"));
        assertThrows(ValidationException.class,
                () -> controller.finalizar(" ORD-1 ", " "));
    }

    @Test
    void delegatesNormalizedAndConvertedOrderValues() {
        final String[] strings = new String[5];
        final LocalDateTime[] date = new LocalDateTime[1];
        CrearOrdenTrabajo crear = (id, fecha, vehiculo, tecnico, observaciones) -> {
            strings[0] = id;
            strings[1] = vehiculo;
            strings[2] = tecnico;
            strings[3] = observaciones;
            date[0] = fecha;
            return null;
        };
        AgregarTrabajoAOrden agregar = (id, descripcion, valor) -> null;
        OrdenControllerImpl controller = new OrdenControllerImpl(
                crear,
                agregar,
                (id, indice) -> null,
                (id, tecnico) -> null,
                (id, observaciones) -> null,
                id -> null,
                id -> null,
                () -> List.of(),
                id -> List.of(),
                estado -> List.of(),
                id -> List.of(),
                placa -> List.of());

        controller.crear(" ORD-1 ", " 2026-11-18T13:30 ", " ABC-123 ",
                " TEC-1 ", " Cambio completo ");

        assertEquals("ORD-1", strings[0]);
        assertEquals("ABC-123", strings[1]);
        assertEquals("TEC-1", strings[2]);
        assertEquals("Cambio completo", strings[3]);
        assertEquals(LocalDateTime.parse("2026-11-18T13:30"), date[0]);
    }
}
