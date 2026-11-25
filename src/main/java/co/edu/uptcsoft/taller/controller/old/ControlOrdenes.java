package co.edu.uptcsoft.taller.controller.old;

import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import co.edu.uptcsoft.taller.service.*;


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
                throw new ServiceException("El campo idOrden esta vacío");
            }
            if (estaVacio(placa)) {
                throw new ServiceException("El campo placa del vehículo esta vacío");
            }
            if (estaVacio(idTecnico)) {
                throw new ServiceException("El campo idTécnico esta vacío");
            }
            String obs = observaciones == null ? "" : observaciones.trim();
            return crearOrdenTrabajo.execute(idOrden.trim(), placa.trim(), idTecnico.trim(), obs);
        }

        public OrdenTrabajo finalizarOrden(String idOrden, String observaciones) {
            if (estaVacio(idOrden)) {
                throw new ServiceException("El campo idOrden esta vacío");
            }
            if (estaVacio(observaciones)){
                throw new ServiceException("El campo observaciones esta vacío");
            }
            return finalizarOrden.execute(idOrden.trim(),observaciones.trim());
        }

        public OrdenTrabajo cancelarOrden(String idOrden) {
            if (estaVacio(idOrden)) {
                throw new ServiceException("El campo idOrden esta vacío");
            }
            return cancelarOrden.execute(idOrden.trim());
        }

        public TrabajoRealizado agregarServicio(String idOrden, String descripcion, String valor){
            if (estaVacio(idOrden)){
                throw new ServiceException("El campo idOrden esta vacío");
            }
            if (estaVacio(descripcion)){
                throw new ServiceException("El campo description esta vacío");
            }
            if (estaVacio(valor)){
                throw new ServiceException("El campo valor esta vacío");
            }
            try {
                double valorDouble = Double.parseDouble(valor);
                return agregarTrabajoAOrden.execute(idOrden,descripcion,valorDouble);
            } catch (NumberFormatException e) {
                throw new ServiceException("El valor debe ser un numero");
            }
        }
        private boolean estaVacio(String texto) {
            return texto == null || texto.isBlank();
        }
}