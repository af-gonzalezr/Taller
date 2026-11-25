package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdenesFechaIngresoImplTest {

    @Test
    void filtersOrdersInclusivelyByEntryDateAndIgnoresOrdersWithoutDate() {
        OrdenTrabajo start = ordenConFecha(LocalDateTime.parse("2026-11-01T00:00:00"));
        OrdenTrabajo middle = ordenConFecha(LocalDateTime.parse("2026-11-15T12:30:00"));
        OrdenTrabajo end = ordenConFecha(LocalDateTime.parse("2026-11-30T23:59:59"));
        OrdenTrabajo withoutDate = ordenConFecha(null);
        List<OrdenTrabajo> ordenes = List.of(start, middle, end, withoutDate);

        @SuppressWarnings("unchecked")
        Repository<OrdenTrabajo> repository = mock(Repository.class);
        when(repository.filtrar(any())).thenAnswer(invocation -> {
            Predicate<OrdenTrabajo> criterio = invocation.getArgument(0);
            return ordenes.stream().filter(criterio).toList();
        });

        OrdenesFechaIngresoImpl service = new OrdenesFechaIngresoImpl(repository);

        assertEquals(List.of(start, middle, end),
                service.execute(LocalDate.parse("2026-11-01"), LocalDate.parse("2026-11-30")));
    }

    @Test
    void rejectsInvalidDateRanges() {
        OrdenesFechaIngresoImpl service = new OrdenesFechaIngresoImpl(mock(Repository.class));

        assertThrows(ServiceException.class,
                () -> service.execute(LocalDate.parse("2026-11-30"), LocalDate.parse("2026-11-01")));
        assertThrows(ServiceException.class,
                () -> service.execute(null, LocalDate.parse("2026-11-30")));
    }

    private OrdenTrabajo ordenConFecha(LocalDateTime fechaIngreso) {
        OrdenTrabajo orden = mock(OrdenTrabajo.class);
        when(orden.getFechaHoraIngreso()).thenReturn(fechaIngreso);
        return orden;
    }
}
