package co.edu.uptcsoft.taller.vista;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
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
}
