package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerValidationTest {
    @Test
    void rejectsNullRequiredText() {
        assertThrows(ValidationException.class,
                () -> ControllerValidation.requiredText(null, "nombre"));
    }

    @Test
    void rejectsTextThatIsEmptyAfterTrim() {
        assertThrows(ValidationException.class,
                () -> ControllerValidation.requiredText("   ", "nombre"));
    }

    @Test
    void normalizesRequiredText() {
        assertEquals("ABC-123",
                ControllerValidation.requiredText("  ABC-123  ", "placa"));
    }

    @Test
    void normalizesOptionalText() {
        assertEquals("", ControllerValidation.optionalText("   "));
        assertEquals("Entregar revisado",
                ControllerValidation.optionalText("  Entregar revisado  "));
    }

    @Test
    void validatesPhoneFormat() {
        assertEquals("3001234567",
                ControllerValidation.phone(" 3001234567 "));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.phone("2001234567"));
    }

    @Test
    void validatesEmailFormat() {
        assertEquals("usuario@dominio.com",
                ControllerValidation.email(" usuario@dominio.com "));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.email("a@b"));
    }

    @Test
    void parsesPositiveInt() {
        assertEquals(4, ControllerValidation.positiveInt(" 4 ", "puertas"));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.positiveInt("4.5", "puertas"));
    }

    @Test
    void parsesPositiveDoubleAndRejectsSpecialValues() {
        assertEquals(10.5, ControllerValidation.positiveDouble(" 10.5 ", "valor"), 0.0001);
        assertThrows(ValidationException.class,
                () -> ControllerValidation.positiveDouble("10d", "valor"));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.positiveDouble("NaN", "valor"));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.positiveDouble("Infinity", "valor"));
    }

    @Test
    void parsesLocalDateTime() {
        assertEquals(LocalDateTime.parse("2026-11-18T13:30"),
                ControllerValidation.localDateTime(" 2026-11-18T13:30 ", "fecha"));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.localDateTime("18/11/2026", "fecha"));
    }

    @Test
    void parsesState() {
        assertEquals(EstadoOrden.EN_PROCESO,
                ControllerValidation.state(" en_proceso "));
        assertThrows(ValidationException.class,
                () -> ControllerValidation.state("PENDIENTE"));
    }
}
