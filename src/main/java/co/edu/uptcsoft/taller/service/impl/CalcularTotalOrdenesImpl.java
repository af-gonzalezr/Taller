package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.service.CalcularTotalOrdenes;

import java.util.List;

public class CalcularTotalOrdenesImpl implements CalcularTotalOrdenes {

    @Override
    public double execute(List<OrdenTrabajo> ordenes) {
        if (ordenes == null) {
            throw new ServiceException("La lista de órdenes es obligatoria.");
        }

        double total = 0;
        for (OrdenTrabajo orden : ordenes) {
            if (orden == null) {
                throw new ServiceException("La lista no puede contener órdenes nulas.");
            }
            if (orden.getEstado() != EstadoOrden.CANCELADA) {
                total += orden.calcularCostoTotal();
            }
        }
        return total;
    }
}
