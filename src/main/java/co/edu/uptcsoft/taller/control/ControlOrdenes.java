package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.ServicioRealizado;
import co.edu.uptcsoft.taller.service.*;

import java.time.LocalDateTime;

public class ControlOrdenes {

        private final CrearOrdenTrabajo crearOrdenTrabajo;
        private final FinalizarOrden finalizarOrden;
        private final CancelarOrden cancelarOrden;
        private final AgregarTrabajoAOrden agregarTrabajoAOrden;

        public ControlOrdenes(CrearOrdenTrabajo crearOrdenTrabajo,
                              FinalizarOrden finalizarOrden,
                              CancelarOrden cancelarOrden, AgregarTrabajoAOrden agregarTrabajoAOrden) {
            this.crearOrdenTrabajo = crearOrdenTrabajo;
            this.finalizarOrden = finalizarOrden;
            this.cancelarOrden = cancelarOrden;
            this.agregarTrabajoAOrden = agregarTrabajoAOrden;
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
                return Respuesta.error("El campo idTécnico esta vacío");
            }
            String obs = observaciones == null ? "" : observaciones.trim();
            return crearOrdenTrabajo.execute(idOrden.trim(), LocalDateTime.now(), placa.trim(), idTecnico.trim(), obs);
        }

        public Respuesta<OrdenTrabajo> finalizarOrden(String idOrden, String observaciones) {
            if (estaVacio(idOrden)) {
                return Respuesta.error("El campo idOrden esta vacío");
            }
            if (estaVacio(observaciones)){
                return Respuesta.error("El campo observaciones esta vacío");
            }
            return finalizarOrden.execute(idOrden.trim(),observaciones.trim());
        }

        public Respuesta<OrdenTrabajo> cancelarOrden(String idOrden) {
            if (estaVacio(idOrden)) {
                return Respuesta.error("El campo idOrden esta vacío");
            }
            return cancelarOrden.execute(idOrden.trim());
        }

        public Respuesta<ServicioRealizado> agregarServicio(String idOrden, String descripcion, String valor){
            if (estaVacio(idOrden)){
                return Respuesta.error("El campo idOrden esta vacío");
            }
            if (estaVacio(descripcion)){
                return Respuesta.error("El campo description esta vacío");
            }
            if (estaVacio(valor)){
                return Respuesta.error("El campo valor esta vacío");
            }
            try {
                double valorDouble = Double.parseDouble(valor);
                return agregarTrabajoAOrden.execute(idOrden,descripcion,valorDouble);
            } catch (NumberFormatException e) {
                return Respuesta.error("El valor debe ser un numero");
            }
        }
        private boolean estaVacio(String texto) {
            return texto == null || texto.isBlank();
        }
}