package co.edu.uptcsoft.taller.vista.gui;

import co.edu.uptcsoft.taller.controller.ClienteController;
import co.edu.uptcsoft.taller.controller.VehiculoController;
import co.edu.uptcsoft.taller.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PanelVehiculos extends JPanel {

    private final VehiculoController vehiculoController;
    private final ClienteController clienteController;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaVehiculos;
    private final JTextField txtFiltro;
    private final JLabel lblEstado;

    public PanelVehiculos(VehiculoController vehiculoController, ClienteController clienteController) {
        this.vehiculoController = vehiculoController;
        this.clienteController = clienteController;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        JButton btnNuevo = new JButton("+ Nuevo Vehículo");
        txtFiltro = new JTextField(14);
        JButton btnBuscarPlaca = new JButton("Buscar Placa");
        JButton btnFiltrarCliente = new JButton("Filtrar por ID Cliente");
        JButton btnTodos = new JButton("Ver Todos");

        barraSuperior.add(btnNuevo);
        barraSuperior.add(new JSeparator(SwingConstants.VERTICAL));
        barraSuperior.add(new JLabel("Placa / ID Cliente:"));
        barraSuperior.add(txtFiltro);
        barraSuperior.add(btnBuscarPlaca);
        barraSuperior.add(btnFiltrarCliente);
        barraSuperior.add(btnTodos);
        add(barraSuperior, BorderLayout.NORTH);

        String[] columnas = {"Placa", "Tipo", "Marca", "Modelo", "Propietario (ID - Nombre)"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setDefaultEditor(Object.class, null);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.setRowHeight(26);
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);

        lblEstado = new JLabel("Total vehículos: 0 (Doble clic para ver detalle)");
        add(lblEstado, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> mostrarDialogoRegistrar());
        btnBuscarPlaca.addActionListener(e -> buscarPorPlaca());
        btnFiltrarCliente.addActionListener(e -> filtrarPorCliente());
        btnTodos.addActionListener(e -> refrescar());

        tablaVehiculos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaVehiculos.getSelectedRow();
                    if (fila != -1) {
                        String placa = modeloTabla.getValueAt(fila, 0).toString();
                        mostrarDetalleVehiculo(placa);
                    }
                }
            }
        });

        refrescar();
    }

    public void refrescar() {
        List<Vehiculo> todos = new ArrayList<>();
        for (Cliente c : clienteController.listarTodos()) {
            todos.addAll(vehiculoController.listarPorCliente(c.getId()));
        }
        cargarTabla(todos);
    }

    private void cargarTabla(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0);
        for (Vehiculo v : vehiculos) {
            String tipo = v.getClass().getSimpleName();
            String propietario = v.getCliente().getId() + " - " + v.getCliente().getNombre();

            modeloTabla.addRow(new Object[]{
                    v.getPlaca(),
                    tipo,
                    v.getMarca(),
                    v.getModelo(),
                    propietario
            });
        }
        lblEstado.setText("Vehículos mostrados: " + vehiculos.size() + " (Doble clic para ver detalle)");
    }

    private void mostrarDetalleVehiculo(String placa) {
        try {
            Vehiculo v = vehiculoController.buscarPorPlaca(placa);
            Cliente c = v.getCliente();

            String detalleEspecifico = "";
            if (v instanceof Automovil a) {
                detalleEspecifico = "Número de puertas: " + a.getNumeroPuertas();
            } else if (v instanceof Motocicleta m) {
                detalleEspecifico = "Cilindraje: " + m.getCilindraje() + " cc";
            } else if (v instanceof Camion cam) {
                detalleEspecifico = "Capacidad de carga: " + cam.getCapacidadCargaTon() + " ton";
            }

            String info = "DATOS DEL VEHÍCULO\n"
                    + "----------------------------------------\n"
                    + "Placa: " + v.getPlaca() + "\n"
                    + "Tipo: " + v.getClass().getSimpleName() + "\n"
                    + "Marca: " + v.getMarca() + "\n"
                    + "Modelo: " + v.getModelo() + "\n"
                    + detalleEspecifico + "\n\n"
                    + "PROPIETARIO\n"
                    + "----------------------------------------\n"
                    + "ID / Documento: " + c.getId() + "\n"
                    + "Nombre: " + c.getNombre() + "\n"
                    + "Teléfono: " + c.getTelefono() + "\n"
                    + "Correo: " + c.getEmail();

            JOptionPane.showMessageDialog(this, info, "Detalle - " + placa, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPorPlaca() {
        String placa = txtFiltro.getText().trim();
        if (placa.isEmpty()) {
            refrescar();
            return;
        }
        try {
            Vehiculo v = vehiculoController.buscarPorPlaca(placa);
            cargarTabla(List.of(v));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void filtrarPorCliente() {
        String idCliente = txtFiltro.getText().trim();
        if (idCliente.isEmpty()) {
            refrescar();
            return;
        }
        try {
            cargarTabla(vehiculoController.listarPorCliente(idCliente));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Filtro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarDialogoRegistrar() {
        List<Cliente> clientes = clienteController.listarTodos();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe registrar al menos un cliente primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Automóvil", "Motocicleta", "Camión"});
        JComboBox<String> cmbCliente = new JComboBox<>();
        for (Cliente c : clientes) {
            cmbCliente.addItem(c.getId() + " - " + c.getNombre());
        }

        JTextField txtPlaca = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JLabel lblExtra = new JLabel("Número de puertas:");
        JTextField txtExtra = new JTextField();

        cmbTipo.addActionListener(e -> {
            String seleccion = (String) cmbTipo.getSelectedItem();
            if ("Automóvil".equals(seleccion)) {
                lblExtra.setText("Número de puertas:");
            } else if ("Motocicleta".equals(seleccion)) {
                lblExtra.setText("Cilindraje (cc):");
            } else {
                lblExtra.setText("Capacidad de carga (ton):");
            }
        });

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Tipo de vehículo:"));
        form.add(cmbTipo);
        form.add(new JLabel("Cliente propietario:"));
        form.add(cmbCliente);
        form.add(new JLabel("Placa:"));
        form.add(txtPlaca);
        form.add(new JLabel("Marca:"));
        form.add(txtMarca);
        form.add(new JLabel("Modelo (año):"));
        form.add(txtModelo);
        form.add(lblExtra);
        form.add(txtExtra);

        int res = JOptionPane.showConfirmDialog(this, form, "Registrar Vehículo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String idCliente = cmbCliente.getSelectedItem().toString().split(" - ")[0];
                String tipo = (String) cmbTipo.getSelectedItem();

                if ("Automóvil".equals(tipo)) {
                    vehiculoController.registrarAuto(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), idCliente, txtExtra.getText());
                } else if ("Motocicleta".equals(tipo)) {
                    vehiculoController.registrarMoto(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), idCliente, txtExtra.getText());
                } else {
                    vehiculoController.registrarCamion(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), idCliente, txtExtra.getText());
                }
                refrescar();
                JOptionPane.showMessageDialog(this, "Vehículo registrado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
