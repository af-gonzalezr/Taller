package co.edu.uptcsoft.taller.vista.gui;


import co.edu.uptcsoft.taller.controller.TecnicoController;
import co.edu.uptcsoft.taller.model.Tecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelTecnicos extends JPanel {

    private final TecnicoController tecnicoController;
    private final DefaultTableModel modeloTabla;
    private final JTable tableTecnicos;
    private final JTextField txtBuscarId;
    private final JLabel lblEstado;

    public PanelTecnicos(TecnicoController tecnicoController) {
        this.tecnicoController = tecnicoController;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Barra superior
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        JButton btnNuevo = new JButton("+ Nuevo Técnico");
        txtBuscarId = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnTodos = new JButton("Ver Todos");

        barraSuperior.add(btnNuevo);
        barraSuperior.add(new JSeparator(SwingConstants.VERTICAL));
        barraSuperior.add(new JLabel("ID:"));
        barraSuperior.add(txtBuscarId);
        barraSuperior.add(btnBuscar);
        barraSuperior.add(btnTodos);
        add(barraSuperior, BorderLayout.NORTH);

        // Tabla central
        String[] columnas = {"ID", "Nombre", "Especialidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTecnicos = new JTable(modeloTabla);
        tableTecnicos.setRowHeight(26);
        add(new JScrollPane(tableTecnicos), BorderLayout.CENTER);

        // Barra inferior
        lblEstado = new JLabel("Total técnicos: 0");
        add(lblEstado, BorderLayout.SOUTH);

        // Eventos
        btnNuevo.addActionListener(e -> mostrarDialogoRegistrar());
        btnBuscar.addActionListener(e -> buscarPorId());
        btnTodos.addActionListener(e -> refrescar());

        refrescar();
    }

    public void refrescar() {
        cargarTabla(tecnicoController.listarTodos());
    }

    private void cargarTabla(List<Tecnico> tecnicos) {
        modeloTabla.setRowCount(0);
        for (Tecnico t : tecnicos) {
            modeloTabla.addRow(new Object[]{
                    t.getId(),
                    t.getNombre(),
                    t.getEspecialidad()
            });
        }
        lblEstado.setText("Técnicos mostrados: " + tecnicos.size());
    }

    private void buscarPorId() {
        String id = txtBuscarId.getText().trim();
        if (id.isEmpty()) {
            refrescar();
            return;
        }
        try {
            Tecnico t = tecnicoController.buscarPorId(id);
            cargarTabla(List.of(t));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarDialogoRegistrar() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtEspecialidad = new JTextField();

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Especialidad:"));
        form.add(txtEspecialidad);

        int res = JOptionPane.showConfirmDialog(this, form, "Registrar Técnico", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                tecnicoController.registrar(txtId.getText(), txtNombre.getText(), txtEspecialidad.getText());
                refrescar();
                JOptionPane.showMessageDialog(this, "Técnico registrado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}