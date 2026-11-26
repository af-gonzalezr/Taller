package co.edu.uptcsoft.taller.vista.gui;

import co.edu.uptcsoft.taller.controller.OrdenController;
import co.edu.uptcsoft.taller.controller.TecnicoController;
import co.edu.uptcsoft.taller.controller.VehiculoController;
import co.edu.uptcsoft.taller.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PanelOrdenes extends JPanel {

    private final OrdenController ordenController;
    private final TecnicoController tecnicoController;
    private final VehiculoController vehiculoController;

    private final DefaultTableModel modeloTabla;
    private final JTable tablaOrdenes;
    private final JLabel lblEstado;

    private final JComboBox<String> cmbTipoFiltro;
    private final JTextField txtValorFiltro;
    private final JComboBox<String> cmbEstadoFiltro;
    private final JSpinner spDesde;
    private final JSpinner spHasta;
    private final JLabel lblDesde;
    private final JLabel lblHasta;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public PanelOrdenes(OrdenController ordenController,
                        TecnicoController tecnicoController,
                        VehiculoController vehiculoController) {
        this.ordenController = ordenController;
        this.tecnicoController = tecnicoController;
        this.vehiculoController = vehiculoController;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Barra superior
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        JButton btnNuevaOrden = new JButton("+ Nueva Orden");

        cmbTipoFiltro = new JComboBox<>(new String[]{
                "Todas", "ID Orden", "Estado", "ID Cliente", "ID Técnico", "Placa", "Fecha ingreso"});
        txtValorFiltro = new JTextField(12);
        cmbEstadoFiltro = new JComboBox<>(new String[]{"EN_PROCESO", "FINALIZADA", "CANCELADA"});
        spDesde = crearSelectorFecha(LocalDateTime.now().minusMonths(1).toLocalDate());
        spHasta = crearSelectorFecha(LocalDateTime.now().toLocalDate());
        lblDesde = new JLabel("Desde:");
        lblHasta = new JLabel("Hasta:");
        cmbEstadoFiltro.setVisible(false);
        txtValorFiltro.setVisible(false);
        lblDesde.setVisible(false);
        lblHasta.setVisible(false);
        spDesde.setVisible(false);
        spHasta.setVisible(false);

        JButton btnFiltrar = new JButton("Aplicar");

        cmbTipoFiltro.addActionListener(e -> {
            String sel = (String) cmbTipoFiltro.getSelectedItem();
            boolean esTodas = sel != null && sel.equalsIgnoreCase("Todas");
            boolean esEstado = sel != null && sel.equalsIgnoreCase("Estado");
            boolean esFecha = sel != null && sel.equalsIgnoreCase("Fecha ingreso");
            cmbEstadoFiltro.setVisible(esEstado);
            txtValorFiltro.setVisible(!esTodas && !esEstado && !esFecha);
            lblDesde.setVisible(esFecha);
            lblHasta.setVisible(esFecha);
            spDesde.setVisible(esFecha);
            spHasta.setVisible(esFecha);
            barraSuperior.revalidate();
            barraSuperior.repaint();
        });

        barraSuperior.add(btnNuevaOrden);
        barraSuperior.add(new JSeparator(SwingConstants.VERTICAL));
        barraSuperior.add(new JLabel("Filtrar por:"));
        barraSuperior.add(cmbTipoFiltro);
        barraSuperior.add(txtValorFiltro);
        barraSuperior.add(cmbEstadoFiltro);
        barraSuperior.add(lblDesde);
        barraSuperior.add(spDesde);
        barraSuperior.add(lblHasta);
        barraSuperior.add(spHasta);
        barraSuperior.add(btnFiltrar);
        add(barraSuperior, BorderLayout.NORTH);

        // Tabla central con columna de acciones "⋮"
        String[] columnas = {"ID Orden", "Ingreso", "Vehículo", "Cliente", "Técnico", "Estado", "Trabajos", "Total", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaOrdenes = new JTable(modeloTabla);
        tablaOrdenes.getTableHeader().setReorderingAllowed(false);
        tablaOrdenes.setDefaultEditor(Object.class,null);
        tablaOrdenes.setRowHeight(28);
        tablaOrdenes.getColumnModel().getColumn(8).setMaxWidth(65);
        configurarMenuAccionesTabla();
        add(new JScrollPane(tablaOrdenes), BorderLayout.CENTER);

        // Barra inferior
        lblEstado = new JLabel("Órdenes mostradas: 0");
        add(lblEstado, BorderLayout.SOUTH);

        // Eventos
        btnNuevaOrden.addActionListener(e -> mostrarDialogoNuevaOrden());
        btnFiltrar.addActionListener(e -> aplicarFiltro());

        refrescar();
    }

    public void refrescar() {
        cmbTipoFiltro.setSelectedIndex(0);
        cargarTabla(ordenController.listarTodas());
    }

    private void cargarTabla(List<OrdenTrabajo> ordenes) {
        modeloTabla.setRowCount(0);
        double granTotal = ordenController.calcularTotal(ordenes);

        for (OrdenTrabajo o : ordenes) {
            List<TrabajoRealizado> trabajos = o.getListaTrabajos() != null ? o.getListaTrabajos() : List.of();
            double totalOrden = o.calcularCostoTotal();

            String fecha = o.getFechaHoraIngreso() != null ? o.getFechaHoraIngreso().format(FORMATO_FECHA) : "-";
            String vehiculo = o.getVehiculo().getPlaca() + " (" + o.getVehiculo().getMarca() + ")";
            String cliente = o.getVehiculo().getCliente().getNombre();
            String tecnico = o.getTecnico().getId() + " - " + o.getTecnico().getNombre();

            modeloTabla.addRow(new Object[]{
                    o.getIdOrden(),
                    fecha,
                    vehiculo,
                    cliente,
                    tecnico,
                    o.getEstado().name(),
                    trabajos.isEmpty() ? "Sin trabajos" : trabajos.size(),
                    String.format("$ %.2f", totalOrden),
                    "⋮"
            });
        }
        lblEstado.setText(String.format("Órdenes mostradas: %d   |   Total acumulado: $ %.2f", ordenes.size(), granTotal));
    }

    private void aplicarFiltro() {
        String tipo = (String) cmbTipoFiltro.getSelectedItem();
        try {
            switch (tipo) {
                case "Todas" -> cargarTabla(ordenController.listarTodas());
                case "ID Orden" -> cargarTabla(List.of(ordenController.buscarPorId(txtValorFiltro.getText().trim())));
                case "Estado" -> cargarTabla(ordenController.listarPorEstado((String) cmbEstadoFiltro.getSelectedItem()));
                case "ID Cliente" -> cargarTabla(ordenController.listarPorCliente(txtValorFiltro.getText().trim()));
                case "ID Técnico" -> cargarTabla(ordenController.listarPorTecnico(txtValorFiltro.getText().trim()));
                case "Placa" -> cargarTabla(ordenController.listarPorVehiculo(txtValorFiltro.getText().trim().toUpperCase()));
                case "Fecha ingreso" -> cargarTablaPorFecha();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Filtro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JSpinner crearSelectorFecha(LocalDate fechaInicial) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(fechaInicial.getYear(), fechaInicial.getMonthValue() - 1, fechaInicial.getDayOfMonth());

        Date fecha = calendar.getTime();
        SpinnerDateModel modelo = new SpinnerDateModel(fecha, null, null, Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(modelo);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
        spinner.setEditor(editor);
        return spinner;
    }

    private void cargarTablaPorFecha() {
        LocalDate desde = ((Date) spDesde.getValue())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate hasta = ((Date) spHasta.getValue())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        cargarTabla(ordenController.listarPorFechaIngreso(desde, hasta));
    }

    private void configurarMenuAccionesTabla() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemDetalle = new JMenuItem("Ver detalle y trabajos");
        JMenuItem itemAgregarTrabajo = new JMenuItem("Agregar trabajo realizado");
        JMenuItem itemQuitarTrabajo = new JMenuItem("Quitar trabajo realizado");
        JMenuItem itemReasignar = new JMenuItem("Reasignar técnico");
        JMenuItem itemFinalizar = new JMenuItem("Finalizar orden");
        JMenuItem itemCancelar = new JMenuItem("Cancelar orden");

        menu.add(itemDetalle);
        menu.addSeparator();
        menu.add(itemAgregarTrabajo);
        menu.add(itemQuitarTrabajo);
        menu.add(itemReasignar);
        menu.addSeparator();
        menu.add(itemFinalizar);
        menu.add(itemCancelar);

        // Listeners del menú
        itemDetalle.addActionListener(e -> mostrarDetalleOrden((String) menu.getClientProperty("idOrden")));
        itemAgregarTrabajo.addActionListener(e -> dialogAgregarTrabajo((String) menu.getClientProperty("idOrden")));
        itemQuitarTrabajo.addActionListener(e -> dialogQuitarTrabajo((String) menu.getClientProperty("idOrden")));
        itemReasignar.addActionListener(e -> dialogReasignarTecnico((String) menu.getClientProperty("idOrden")));
        itemFinalizar.addActionListener(e -> dialogFinalizarOrden((String) menu.getClientProperty("idOrden")));
        itemCancelar.addActionListener(e -> dialogCancelarOrden((String) menu.getClientProperty("idOrden")));

        // Renderer del botón "⋮"
        tablaOrdenes.getColumnModel().getColumn(8).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton btn = new JButton("...");
            btn.setFocusable(false);
            return btn;
        });

        tablaOrdenes.getColumnModel().getColumn(8).setCellEditor(null);
        tablaOrdenes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaOrdenes.rowAtPoint(e.getPoint());
                int columna = tablaOrdenes.columnAtPoint(e.getPoint());
                if (fila < 0 || columna != 8) {
                    return;
                }

                tablaOrdenes.setRowSelectionInterval(fila, fila);
                String idOrden = tablaOrdenes.getValueAt(fila, 0).toString();
                String estado = tablaOrdenes.getValueAt(fila, 5).toString();
                boolean enProceso = estado != null && estado.equalsIgnoreCase("EN_PROCESO");

                itemAgregarTrabajo.setEnabled(enProceso);
                itemQuitarTrabajo.setEnabled(enProceso);
                itemReasignar.setEnabled(enProceso);
                itemFinalizar.setEnabled(enProceso);
                itemCancelar.setEnabled(enProceso);

                menu.putClientProperty("idOrden", idOrden);
                menu.show(tablaOrdenes, e.getX(), e.getY());
            }
        });
    }

    private void mostrarDialogoNuevaOrden() {
        List<Tecnico> tecnicos = tecnicoController.listarTodos();
        List<Vehiculo> vehiculos = vehiculoController.listarTodos();

        if (tecnicos.isEmpty() || vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe haber al menos un vehículo y un técnico registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField txtIdOrden = new JTextField();
        JComboBox<String> cmbVehiculo = new JComboBox<>();
        for (Vehiculo v : vehiculos) {
            cmbVehiculo.addItem(v.getPlaca() + " - " + v.getMarca() + " (" + v.getCliente().getNombre() + ")");
        }

        JComboBox<String> cmbTecnico = new JComboBox<>();
        for (Tecnico t : tecnicos) {
            cmbTecnico.addItem(t.getId() + " - " + t.getNombre() + " [" + t.getEspecialidad() + "]");
        }

        JTextArea txtObservaciones = new JTextArea(3, 20);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("ID de la Orden:"));
        form.add(txtIdOrden);
        form.add(new JLabel("Vehículo:"));
        form.add(cmbVehiculo);
        form.add(new JLabel("Técnico asignado:"));
        form.add(cmbTecnico);
        form.add(new JLabel("Observaciones ingreso:"));
        form.add(new JScrollPane(txtObservaciones));

        int res = JOptionPane.showConfirmDialog(this, form, "Crear Orden de Trabajo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String placa = cmbVehiculo.getSelectedItem().toString().split(" - ")[0];
                String idTecnico = cmbTecnico.getSelectedItem().toString().split(" - ")[0];
                ordenController.crear(txtIdOrden.getText(), placa, idTecnico, txtObservaciones.getText());
                refrescar();
                JOptionPane.showMessageDialog(this, "Orden creada exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dialogAgregarTrabajo(String idOrden) {
        JTextField txtDesc = new JTextField();
        JTextField txtValor = new JTextField();

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Descripción del trabajo:"));
        form.add(txtDesc);
        form.add(new JLabel("Valor ($):"));
        form.add(txtValor);

        int res = JOptionPane.showConfirmDialog(this, form, "Agregar Trabajo - Orden " + idOrden, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                ordenController.agregarTrabajo(idOrden, txtDesc.getText(), txtValor.getText());
                aplicarFiltro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dialogQuitarTrabajo(String idOrden) {
        try {
            OrdenTrabajo orden = ordenController.buscarPorId(idOrden);
            List<TrabajoRealizado> trabajos = orden.getListaTrabajos();
            if (trabajos == null || trabajos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Esta orden no tiene trabajos registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JComboBox<String> cmbTrabajos = new JComboBox<>();
            for (int i = 0; i < trabajos.size(); i++) {
                TrabajoRealizado t = trabajos.get(i);
                cmbTrabajos.addItem((i+1) + " - " + t.getDescripcion() + " ($" + t.getValor() + ")");
            }

            int res = JOptionPane.showConfirmDialog(this, cmbTrabajos, "Seleccione trabajo a quitar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                String indice = String.valueOf(cmbTrabajos.getSelectedIndex());
                ordenController.quitarTrabajo(idOrden, indice);
                aplicarFiltro();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dialogReasignarTecnico(String idOrden) {
        List<Tecnico> tecnicos = tecnicoController.listarTodos();
        JComboBox<String> cmbTecnico = new JComboBox<>();
        for (Tecnico t : tecnicos) {
            cmbTecnico.addItem(t.getId() + " - " + t.getNombre() + " [" + t.getEspecialidad() + "]");
        }

        int res = JOptionPane.showConfirmDialog(this, cmbTecnico, "Reasignar Técnico - Orden " + idOrden, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION && cmbTecnico.getSelectedItem() != null) {
            try {
                String idNuevoTecnico = cmbTecnico.getSelectedItem().toString().split(" - ")[0];
                ordenController.reasignarTecnico(idOrden, idNuevoTecnico);
                aplicarFiltro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dialogFinalizarOrden(String idOrden) {
        JTextArea txtObsEntrega = new JTextArea(3, 22);
        JPanel form = new JPanel(new BorderLayout(5, 5));
        form.add(new JLabel("Observaciones de entrega:"), BorderLayout.NORTH);
        form.add(new JScrollPane(txtObsEntrega), BorderLayout.CENTER);

        int res = JOptionPane.showConfirmDialog(this, form, "Finalizar Orden " + idOrden, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                ordenController.finalizar(idOrden, txtObsEntrega.getText());
                aplicarFiltro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dialogCancelarOrden(String idOrden) {
        int conf = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cancelar la orden " + idOrden + "?", "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            try {
                ordenController.cancelar(idOrden);
                aplicarFiltro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDetalleOrden(String idOrden) {
        try {
            OrdenTrabajo o = ordenController.buscarPorId(idOrden);
            StringBuilder sb = new StringBuilder();
            sb.append("Orden ID: ").append(o.getIdOrden()).append("\n");
            sb.append("Estado: ").append(o.getEstado()).append("\n");
            sb.append("Ingreso: ").append(o.getFechaHoraIngreso() != null ? o.getFechaHoraIngreso().format(FORMATO_FECHA) : "-").append("\n");
            sb.append("Entrega: ").append(o.getFechaHoraEntrega() != null ? o.getFechaHoraEntrega().format(FORMATO_FECHA) : "Pendiente").append("\n");
            sb.append("Vehículo: ").append(o.getVehiculo().getPlaca()).append(" - ").append(o.getVehiculo().getMarca()).append("\n");
            sb.append("Cliente: ").append(o.getVehiculo().getCliente().getNombre()).append("\n");
            sb.append("Técnico: ").append(o.getTecnico().getNombre()).append("\n");
            sb.append("Obs. Ingreso: ").append(o.getObservacionesIngreso()).append("\n");
            sb.append("Obs. Entrega: ").append(o.getObservacionesEntrega()).append("\n\n");
            sb.append("--- Trabajos Realizados ---\n");

            List<TrabajoRealizado> trabajos = o.getListaTrabajos() != null ? o.getListaTrabajos() : List.of();
            if (trabajos.isEmpty()) {
                sb.append("(Sin trabajos registrados)\n");
            } else {
                for (int i = 0; i < trabajos.size(); i++) {
                    TrabajoRealizado t = trabajos.get(i);
                    sb.append(i + 1).append(". ").append(t.getDescripcion())
                            .append(" -> $ ").append(String.format("%.2f", t.getValor())).append("\n");
                }
            }
            double total = o.calcularCostoTotal();
            sb.append("\nTOTAL: $ ").append(String.format("%.2f", total));

            JTextArea area = new JTextArea(sb.toString(), 16, 38);
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Detalle Orden " + idOrden, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}