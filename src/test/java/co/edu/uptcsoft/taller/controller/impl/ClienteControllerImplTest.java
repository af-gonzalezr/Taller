package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteControllerImplTest {
    @Test
    void rejectsInvalidClientInputBeforeDelegation() {
        ClienteControllerImpl controller = new ClienteControllerImpl(
                (id, nombre, telefono, correo) -> null,
                id -> null,
                () -> List.of());

        assertThrows(ValidationException.class,
                () -> controller.registrar(" 1 ", " Ana ", "2001234567", "ana@dominio.com"));
        assertThrows(ValidationException.class,
                () -> controller.registrar(" 1 ", " Ana ", "3001234567", "ana@dominio"));
    }

    @Test
    void delegatesNormalizedClientValues() {
        List<String> captured = new ArrayList<>();
        RegistrarCliente registrar = (id, nombre, telefono, correo) -> {
            captured.add(id);
            captured.add(nombre);
            captured.add(telefono);
            captured.add(correo);
            return new Cliente(id, nombre, telefono, correo);
        };
        ClienteControllerImpl controller = new ClienteControllerImpl(
                registrar, id -> null, () -> List.of());

        Cliente result = controller.registrar(
                " 123 ", " Ana Perez ", " 3001234567 ", " ana@dominio.com ");

        assertEquals("123", result.getId());
        assertEquals(List.of("123", "Ana Perez", "3001234567", "ana@dominio.com"),
                captured);
    }
}
