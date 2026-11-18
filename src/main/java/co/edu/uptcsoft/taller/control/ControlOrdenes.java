package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.exception.BusinessRuleException;
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
        public OrdenTrabajo crearOrden(String idOrden, String placa, String idTecnico, String observaciones) {
            if (estaVacio(idOrden)) {
                throw new BusinessRuleException("El campo idOrden esta vacío");
            }
            if (estaVacio(placa)) {
                throw new BusinessRuleException("El campo placa del vehículo esta vacío");
            }
            if (estaVacio(idTecnico)) {
                throw new BusinessRuleException("El campo idTécnico esta vacío");
            }
            String obs = observaciones == null ? "" : observaciones.trim();
            return crearOrdenTrabajo.execute(idOrden.trim(), LocalDateTime.now(), placa.trim(), idTecnico.trim(), obs);
        }

        public OrdenTrabajo finalizarOrden(String idOrden, String observaciones) {
            if (estaVacio(idOrden)) {
                throw new BusinessRuleException("El campo idOrden esta vacío");
            }
            if (estaVacio(observaciones)){
                throw new BusinessRuleException("El campo observaciones esta vacío");
            }
            return finalizarOrden.execute(idOrden.trim(),observaciones.trim());
        }

        public OrdenTrabajo cancelarOrden(String idOrden) {
            if (estaVacio(idOrden)) {
                throw new BusinessRuleException("El campo idOrden esta vacío");
            }
            return cancelarOrden.execute(idOrden.trim());
        }

        public ServicioRealizado agregarServicio(String idOrden, String descripcion, String valor){
            if (estaVacio(idOrden)){
                throw new BusinessRuleException("El campo idOrden esta vacío");
            }
            if (estaVacio(descripcion)){
                throw new BusinessRuleException("El campo description esta vacío");
            }
            if (estaVacio(valor)){
                throw new BusinessRuleException("El campo valor esta vacío");
            }
            try {
                double valorDouble = Double.parseDouble(valor);
                return agregarTrabajoAOrden.execute(idOrden,descripcion,valorDouble);
            } catch (NumberFormatException e) {
                throw new BusinessRuleException("El valor debe ser un numero");
            }
        }
        private boolean estaVacio(String texto) {
            return texto == null || texto.isBlank();
        }
}