
package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.service.CancelarOrden;
import co.edu.uptcsoft.taller.service.CrearOrdenTrabajo;
import co.edu.uptcsoft.taller.service.FinalizarOrden;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.time.LocalDateTime;

public class ControlOrdenes {

    private final CrearOrdenTrabajo crearOrdenTrabajo;
    private final FinalizarOrden finalizarOrden;
    private final CancelarOrden cancelarOrden;

    public ControlOrdenes(CrearOrdenTrabajo crearOrdenTrabajo,
                          FinalizarOrden finalizarOrden,
                          CancelarOrden cancelarOrden) {
        this.crearOrdenTrabajo = crearOrdenTrabajo;
        this.finalizarOrden = finalizarOrden;
        this.cancelarOrden = cancelarOrden;
    }

    /**
     * La fecha de ingreso se toma automáticamente al momento de crear la orden.
     * Las observaciones son opcionales.
     */
    public Respuesta<OrdenTrabajo> crearOrden(String idOrden, String placa, String idTecnico, String observaciones) {
        if (estaVacio(idOrden)) {
            return Respuesta.error("El campo idOrden esta vacío");
        }
        if (estaVacio(placa)) {
            return Respuesta.error("El campo placa del vehículo esta vacío");
        }
        if (estaVacio(idTecnico)) {
            return Respuesta.error("El campo idTecnico esta vacío");
        }
        String obs = observaciones == null ? "" : observaciones.trim();
        return crearOrdenTrabajo.execute(idOrden.trim(), LocalDateTime.now(), placa.trim(), idTecnico.trim(), obs);
    }

    public Respuesta<OrdenTrabajo> finalizarOrden(String idOrden) {
        if (estaVacio(idOrden)) {
            return Respuesta.error("El campo idOrden esta vacío");
        }
        return finalizarOrden.execute(idOrden.trim());
    }

    public Respuesta<OrdenTrabajo> cancelarOrden(String idOrden) {
        if (estaVacio(idOrden)) {
            return Respuesta.error("El campo idOrden esta vacío");
        }
        return cancelarOrden.execute(idOrden.trim());
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}

