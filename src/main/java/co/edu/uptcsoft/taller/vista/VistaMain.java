package co.edu.uptcsoft.taller.vista;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.service.Respuesta;

import javax.swing.*;
import java.awt.*;

public class VistaMain {

    private final ControlRegistros controlRegistros;

    public VistaMain(ControlRegistros controlRegistros) {
        this.controlRegistros = controlRegistros;
    }


    public void mostrarMain(){
        String[] opciones = {
                "1. Registros",
                "2. Gestion de Ordenes",
                "3. Actualizar Datos",
                "4. Consultas",
                "5. Salir"
        };
        boolean salir = false;
        while (!salir){
            int seleccion = JOptionPane.showOptionDialog(null,
                    "SELECCIONE UNA OPCION DEL MENU",
                    "SISTEMA TALLER",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
                    );
            switch (seleccion){
                case 0->menuRegistros();

                case 1->menuGestionOrdenes();

            }
        }
    }
    public void menuRegistros(){
        String[] opciones = {
                "1. RegistrarCliente"
        };
        int seleccion = JOptionPane.showOptionDialog(null,
                "SELECCIONE UNA OPCION DEL MENU",
                "SISTEMA TALLER",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        switch (seleccion){
            case 0->registrarCliente();
        }

    }

    public void registrarCliente(){
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField idClienteTxt = new JTextField();
        JTextField nombreTxt = new JTextField();
        JTextField telefonoTxt = new JTextField();
        JTextField emailTxt = new JTextField();


        panel.add(new JLabel("Identificación:"));
        panel.add(idClienteTxt);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreTxt);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoTxt);
        panel.add(new JLabel("Correo Electrónico:"));
        panel.add(emailTxt);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Registrar Automóvil",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String idCliente = idClienteTxt.getText();
            String nombre = nombreTxt.getText();
            String telefono = telefonoTxt.getText();
            String email = emailTxt.getText();
            Respuesta<Cliente> respuesta = controlRegistros
                    .crearCLiente(idCliente,
                    nombre,
                    telefono,
                    email);
            if(respuesta.completado()){
                Cliente c = respuesta.elemento();

                String mensaje = respuesta.mensaje() + "\n\n"
                        + "Identificación: " + c.getIdCliente() + "\n"
                        + "Nombre: " + c.getNombre() + "\n"
                        + "Teléfono: " + c.getTelefono() + "\n"
                        + "Correo Electrónico: " + c.getEmail();

                JOptionPane.showMessageDialog(
                        null,
                        mensaje,
                        "Cliente Registrado",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + respuesta.mensaje(),
                        "Atención",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }
    public void menuGestionOrdenes(){

    String[] opciones = {
            "1. Crear",
            "2. Finalizar",
            "3. Cancelar"
    };

    int seleccion = JOptionPane.showOptionDialog(
            null,
            "GESTIÓN DE ORDENES",
            "SISTEMA TALLER",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
    );

    switch (seleccion){

        case 0 -> crearOrden();

        case 1 -> finalizarOrden();

        case 2 -> cancelarOrden();
        default->volver = true;
    }
}
}

    public void crearOrden(){
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField idOrdenTxt = new JTextField();
        JTextField placaTxt = new JTextField();
        JTextField idTecnicoTxt = new JTextField();
        JTextField observacionesTxt = new JTextField();

        panel.add(new JLabel("ID de la orden:"));
        panel.add(idOrdenTxt);
        panel.add(new JLabel("Placa del vehículo:"));
        panel.add(placaTxt);
        panel.add(new JLabel("ID del técnico:"));
        panel.add(idTecnicoTxt);
        panel.add(new JLabel("Observaciones de ingreso:"));
        panel.add(observacionesTxt);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Crear Orden de Trabajo",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            mostrarRespuestaOrden(controlOrdenes.crearOrden(
                    idOrdenTxt.getText(),
                    placaTxt.getText(),
                    idTecnicoTxt.getText(),
                    observacionesTxt.getText()),
                    "Orden Creada");
        }
    }

    public void finalizarOrden(){
        String idOrden = pedirIdOrden("Ingrese el ID de la orden a finalizar:", "Finalizar Orden");
        if (idOrden == null) {
            return;
        }
        if (!idOrden.isBlank() && !confirmar("¿Desea finalizar la orden " + idOrden.trim() + "?", "Finalizar Orden")) {
            return;
        }
        mostrarRespuestaOrden(controlOrdenes.finalizarOrden(idOrden), "Orden Finalizada");
    }

    public void cancelarOrden(){
        String idOrden = pedirIdOrden("Ingrese el ID de la orden a cancelar:", "Cancelar Orden");
        if (idOrden == null) {
            return;
        }
        if (!idOrden.isBlank() && !confirmar("¿Desea cancelar la orden " + idOrden.trim() + "?\nEsta acción no se puede deshacer.", "Cancelar Orden")) {
            return;
        }
        mostrarRespuestaOrden(controlOrdenes.cancelarOrden(idOrden), "Orden Cancelada");
    }

    private String pedirIdOrden(String mensaje, String titulo){
        return JOptionPane.showInputDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private boolean confirmar(String mensaje, String titulo){
        return JOptionPane.showConfirmDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) == JOptionPane.YES_OPTION;
    }

    private void mostrarRespuestaOrden(Respuesta<OrdenTrabajo> respuesta, String titulo){
        if (respuesta.completado()) {
            OrdenTrabajo o = respuesta.elemento();
            StringBuilder mensaje = new StringBuilder(respuesta.mensaje()).append("\n\n")
                    .append("ID orden: ").append(o.getIdOrden()).append("\n")
                    .append("Estado: ").append(o.getEstado()).append("\n")
                    .append("Vehículo (placa): ").append(o.getVehiculo().getPlaca()).append("\n")
                    .append("Técnico: ").append(o.getTecnico().getNombre()).append("\n")
                    .append("Ingreso: ").append(o.getFechaHoraIngreso().format(FORMATO_FECHA)).append("\n");
            if (o.getFechaHoraEntrega() != null) {
                mensaje.append("Entrega: ").append(o.getFechaHoraEntrega().format(FORMATO_FECHA)).append("\n");
            }
            mensaje.append("Costo total: ").append(o.calcularCostoTotal());
            JOptionPane.showMessageDialog(null, mensaje.toString(), titulo, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + respuesta.mensaje(),
                    "Atención",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void menuRegistros(){
        String[] opciones = {
                "1. RegistrarCliente"
        };
        int seleccion = JOptionPane.showOptionDialog(null,
                "SELECCIONE UNA OPCION DEL MENU",
                "SISTEMA TALLER",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        switch (seleccion){
            case 0->registrarCliente();
        }

    }

    public void registrarCliente(){
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField idClienteTxt = new JTextField();
        JTextField nombreTxt = new JTextField();
        JTextField telefonoTxt = new JTextField();
        JTextField emailTxt = new JTextField();


        panel.add(new JLabel("Identificación:"));
        panel.add(idClienteTxt);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreTxt);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoTxt);
        panel.add(new JLabel("Correo Electrónico:"));
        panel.add(emailTxt);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Registrar Automóvil",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String idCliente = idClienteTxt.getText();
            String nombre = nombreTxt.getText();
            String telefono = telefonoTxt.getText();
            String email = emailTxt.getText();
            Respuesta<Cliente> respuesta = controlRegistros
                    .crearCLiente(idCliente,
                    nombre,
                    telefono,
                    email);
            if(respuesta.completado()){
                Cliente c = respuesta.elemento();

                String mensaje = respuesta.mensaje() + "\n\n"
                        + "Identificación: " + c.getIdCliente() + "\n"
                        + "Nombre: " + c.getNombre() + "\n"
                        + "Teléfono: " + c.getTelefono() + "\n"
                        + "Correo Electrónico: " + c.getEmail();

                JOptionPane.showMessageDialog(
                        null,
                        mensaje,
                        "Cliente Registrado",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + respuesta.mensaje(),
                        "Atención",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }
}