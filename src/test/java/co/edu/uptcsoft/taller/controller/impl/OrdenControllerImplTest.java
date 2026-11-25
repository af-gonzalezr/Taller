package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.service.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdenControllerImplTest {
    private CrearOrdenTrabajo crearOrdenNoop() {
        return new CrearOrdenTrabajo() {
            @Override
            public OrdenTrabajo execute(String id, String vehiculo, String tecnico, String observaciones) {
                return null;
            }

            @Override
            public OrdenTrabajo execute(String id, LocalDateTime fecha, String vehiculo,
                                        String tecnico, String observaciones) {
                return null;
            }
        };
    }

    private OrdenControllerImpl emptyController() {
        return new OrdenControllerImpl(
                crearOrdenNoop(),
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
                placa -> List.of(),
                (desde, hasta) -> List.of(),
                ordenes -> 0);
    }

    @Test
    void rejectsInvalidOrderInputBeforeDelegation() {
        OrdenControllerImpl controller = emptyController();

        assertThrows(ValidationException.class,
                () -> controller.crear(" ORD-1 ", "18/11/2026", "ABC-123", "TEC-1", " "));
        assertThrows(ValidationException.class,
                () -> controller.agregarTrabajo(" ORD-1 ", "Cambio", "10d"));
    }

    @Test
    void delegatesNormalizedAndConvertedOrderValues() {
        final String[] strings = new String[5];
        final LocalDateTime[] date = new LocalDateTime[1];
        CrearOrdenTrabajo crear = new CrearOrdenTrabajo() {
            @Override
            public OrdenTrabajo execute(String id, String vehiculo, String tecnico, String observaciones) {
                return null;
            }

            @Override
            public OrdenTrabajo execute(String id, LocalDateTime fecha, String vehiculo,
                                        String tecnico, String observaciones) {
                strings[0] = id;
                strings[1] = vehiculo;
                strings[2] = tecnico;
                strings[3] = observaciones;
                date[0] = fecha;
                return null;
            }
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
                placa -> List.of(),
                (desde, hasta) -> List.of(),
                ordenes -> 0);

        controller.crear(" ORD-1 ", " 2026-11-18T13:30 ", " ABC-123 ",
                " TEC-1 ", " Cambio completo ");

        assertEquals("ORD-1", strings[0]);
        assertEquals("ABC-123", strings[1]);
        assertEquals("TEC-1", strings[2]);
        assertEquals("Cambio completo", strings[3]);
        assertEquals(LocalDateTime.parse("2026-11-18T13:30"), date[0]);
    }

    @Test
    void delegatesCreationWithoutDateToServiceOverload() {
        final String[] captured = new String[4];
        CrearOrdenTrabajo crear = new CrearOrdenTrabajo() {
            @Override
            public OrdenTrabajo execute(String id, String vehiculo, String tecnico, String observaciones) {
                captured[0] = id;
                captured[1] = vehiculo;
                captured[2] = tecnico;
                captured[3] = observaciones;
                return null;
            }

            @Override
            public OrdenTrabajo execute(String id, LocalDateTime fecha, String vehiculo,
                                        String tecnico, String observaciones) {
                throw new AssertionError("Debe delegar en la sobrecarga sin fecha");
            }
        };
        OrdenControllerImpl controller = new OrdenControllerImpl(
                crear,
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
                placa -> List.of(),
                (desde, hasta) -> List.of(),
                ordenes -> 0);

        controller.crear(" ORD-1 ", " ABC-123 ", " TEC-1 ", " Observación ");

        assertEquals("ORD-1", captured[0]);
        assertEquals("ABC-123", captured[1]);
        assertEquals("TEC-1", captured[2]);
        assertEquals("Observación", captured[3]);
    }

    @Test
    void normalizesOptionalDeliveryObservationsBeforeDelegation() {
        final String[] captured = new String[1];
        OrdenControllerImpl controller = new OrdenControllerImpl(
                crearOrdenNoop(),
                (id, descripcion, valor) -> null,
                (id, indice) -> null,
                (id, tecnico) -> null,
                (id, observaciones) -> {
                    captured[0] = observaciones;
                    return null;
                },
                id -> null,
                id -> null,
                () -> List.of(),
                id -> List.of(),
                estado -> List.of(),
                id -> List.of(),
                placa -> List.of(),
                (desde, hasta) -> List.of(),
                ordenes -> 0);

        controller.finalizar(" ORD-1 ", " ");

        assertEquals("", captured[0]);
    }

    @Test
    void delegatesDateRangeToServiceWithoutValidatingItInController() {
        final LocalDate[] dates = new LocalDate[2];
        OrdenControllerImpl controller = new OrdenControllerImpl(
                crearOrdenNoop(),
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
                placa -> List.of(),
                (desde, hasta) -> {
                    dates[0] = desde;
                    dates[1] = hasta;
                    return List.of();
                },
                ordenes -> 0);
        LocalDate desde = LocalDate.parse("2026-11-01");
        LocalDate hasta = LocalDate.parse("2026-11-30");

        controller.listarPorFechaIngreso(desde, hasta);

        assertEquals(desde, dates[0]);
        assertEquals(hasta, dates[1]);

        controller.listarPorFechaIngreso(hasta, desde);

        assertEquals(hasta, dates[0]);
        assertEquals(desde, dates[1]);
    }
}
