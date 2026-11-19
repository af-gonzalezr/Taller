package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.exception.ValidationException;
import co.edu.uptcsoft.taller.model.EstadoOrden;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class ControllerValidation {
    private ControllerValidation() {
    }

    public static String requiredText(String value, String fieldName) {
        if (value == null) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio");
        }
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio");
        }
        return normalized;
    }

    public static String optionalText(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    public static int positiveInt(String value, String fieldName) {
        int parsed = parseInt(value, fieldName);
        if (parsed <= 0) {
            throw new ValidationException("El campo " + fieldName + " debe ser un entero positivo");
        }
        return parsed;
    }

    public static int nonNegativeInt(String value, String fieldName) {
        int parsed = parseInt(value, fieldName);
        if (parsed < 0) {
            throw new ValidationException("El campo " + fieldName + " debe ser un entero no negativo");
        }
        return parsed;
    }

    private static int parseInt(String value, String fieldName) {
        String normalized = requiredText(value, fieldName);
        try {
            return Integer.parseInt(normalized);
        } catch (NumberFormatException exception) {
            throw new ValidationException("El campo " + fieldName + " debe ser un entero válido");
        }
    }

    public static double positiveDouble(String value, String fieldName) {
        String normalized = requiredText(value, fieldName);
        if (!normalized.matches("\\d+(\\.\\d+)?")) {
            throw new ValidationException("El campo " + fieldName + " debe ser un decimal positivo válido");
        }

        try {
            double parsed = Double.parseDouble(normalized);
            if (!Double.isFinite(parsed) || parsed <= 0.0) {
                throw new ValidationException("El campo " + fieldName + " debe ser un decimal positivo válido");
            }
            return parsed;
        } catch (NumberFormatException exception) {
            throw new ValidationException("El campo " + fieldName + " debe ser un decimal positivo válido");
        }
    }

    public static String phone(String value) {
        String normalized = requiredText(value, "teléfono");
        if (!normalized.matches("3\\d{9}")) {
            throw new ValidationException("El teléfono debe tener 10 dígitos y comenzar por 3");
        }
        return normalized;
    }

    public static String plate(String value) {
        return requiredText(value, "placa").toUpperCase();
    }

    public static String email(String value) {
        String normalized = requiredText(value, "correo");
        int firstAt = normalized.indexOf('@');

        if (firstAt <= 0
                || firstAt != normalized.lastIndexOf('@')
                || normalized.indexOf('@') == normalized.length() - 1
                || normalized.indexOf('.', firstAt + 1) <= firstAt + 1
                || normalized.indexOf('.', firstAt + 1) == normalized.length() - 1
                || normalized.indexOf(' ') >= 0
                || normalized.indexOf('\t') >= 0) {
            throw new ValidationException("El correo tiene un formato inválido");
        }
        return normalized;
    }

    public static LocalDateTime localDateTime(String value, String fieldName) {
        String normalized = requiredText(value, fieldName);
        try {
            return LocalDateTime.parse(normalized);
        } catch (DateTimeParseException exception) {
            throw new ValidationException("El campo " + fieldName + " debe tener formato de fecha y hora válido");
        }
    }

    public static EstadoOrden state(String value) {
        String normalized = requiredText(value, "estado");
        for (EstadoOrden state : EstadoOrden.values()) {
            if (state.name().equalsIgnoreCase(normalized)) {
                return state;
            }
        }
        throw new ValidationException("El estado de la orden no es válido");
    }
}
