package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TecnicoControllerImplTest {
    @Test
    void rejectsBlankTechnicianInput() {
        TecnicoControllerImpl controller = new TecnicoControllerImpl(
                (id, nombre, especialidad) -> null,
                id -> null,
                () -> List.of());

        assertThrows(ValidationException.class,
                () -> controller.registrar(" TEC-1 ", " ", "Motor"));
    }

    @Test
    void delegatesNormalizedTechnicianValues() {
        List<String> captured = new ArrayList<>();
        RegistrarTecnico registrar = (id, nombre, especialidad) -> {
            captured.add(id);
            captured.add(nombre);
            captured.add(especialidad);
            return new Tecnico(id, nombre, especialidad);
        };
        TecnicoControllerImpl controller = new TecnicoControllerImpl(
                registrar, id -> null, () -> List.of());

        controller.registrar(" TEC-1 ", " Carlos Perez ", " Mecanica ");

        assertEquals(List.of("TEC-1", "Carlos Perez", "Mecanica"), captured);
    }
}
