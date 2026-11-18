package co.edu.uptcsoft.taller.vista.gui;

import co.edu.uptcsoft.taller.controller.ClienteController;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelClientes extends JPanel {
    private final ClienteController clienteController;
    private final DefaultTableModel modeloTabla;
    private final JTable tableCliente;
    private final JTextField txtBuscarId;
    private final JLabel lblEstado;

    public PanelClientes(ClienteController clienteController){
        this.clienteController = clienteController;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT,8,5));
        JButton btnNuevo = new JButton("+ Nuevo Cliente");
        txtBuscarId = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnTodos = new JButton("Ver Todos");

        barraSuperior.add(btnNuevo);
        barraSuperior.add(new JSeparator(SwingConstants.VERTICAL));
        barraSuperior.add(new JLabel("ID:"));
        barraSuperior.add(txtBuscarId);
        barraSuperior.add(btnBuscar);
        barraSuperior.add(btnTodos);
        add(barraSuperior,BorderLayout.NORTH);

        String[] columnas = {"ID / Documento", "Nombre", "Teléfono", "Correo"};
        modeloTabla = new DefaultTableModel(columnas,0);
        tableCliente = new JTable(modeloTabla);
        tableCliente.setRowHeight(26);

        tableCliente.setDefaultEditor(Object.class,null);

        add(new JScrollPane(tableCliente),BorderLayout.CENTER);

        lblEstado = new JLabel("TOTAL CLIENTES: 0");
        add(lblEstado,BorderLayout.SOUTH);

        btnNuevo.addActionListener(e->dialogoRegistrar());
        btnBuscar.addActionListener(e->buscarPorId());
        btnTodos.addActionListener(e->refrescar());

        refrescar();

    }

    public void refrescar(){
        cargarTabla(clienteController.listarTodos());
    }

    private void cargarTabla(List<Cliente> clientes){
        modeloTabla.setRowCount(0);
        for(Cliente c : clientes){
            modeloTabla.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getTelefono(),
                    c.getEmail()
            });
        }
        lblEstado.setText("TOTAL CLIENTES: "+clientes.size());
    }

    private void buscarPorId(){
        String id = txtBuscarId.getText().trim();

        if (id.isEmpty()){
            refrescar();
            return;
        }

        try{
            Cliente c = clienteController.buscarPorId(id);
            cargarTabla(List.of(c));
        }catch (TallerException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Busqueda",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void dialogoRegistrar() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreo = new JTextField();

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("ID / Documento:"));
        form.add(txtId);
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Teléfono:"));
        form.add(txtTelefono);
        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);

        int res = JOptionPane.showConfirmDialog(this,
                form,
                "Registrar Cliente",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (res == JOptionPane.OK_OPTION) {
            try {
                clienteController.registrar(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
                refrescar();
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



}
