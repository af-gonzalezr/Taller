package co.edu.uptcsoft.taller.vista.gui;

import co.edu.uptcsoft.taller.controller.ClienteController;
import co.edu.uptcsoft.taller.controller.OrdenController;
import co.edu.uptcsoft.taller.controller.TecnicoController;
import co.edu.uptcsoft.taller.controller.VehiculoController;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final PanelClientes panelClientes;
    private final PanelTecnicos panelTecnicos;
    private final PanelVehiculos panelVehiculos;
    private final PanelOrdenes panelOrdenes;

    public VentanaPrincipal(ClienteController clienteController,
                            TecnicoController tecnicoController,
                            VehiculoController vehiculoController,
                            OrdenController ordenController) {
        setTitle("Sistema de Gestión de Taller Mecánico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.panelClientes = new PanelClientes(clienteController);
        this.panelTecnicos = new PanelTecnicos(tecnicoController);
        this.panelVehiculos = new PanelVehiculos(vehiculoController, clienteController);
        this.panelOrdenes = new PanelOrdenes(ordenController, tecnicoController, vehiculoController);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Órdenes de Trabajo", panelOrdenes);
        tabbedPane.addTab("Clientes", panelClientes);
        tabbedPane.addTab("Vehículos", panelVehiculos);
        tabbedPane.addTab("Técnicos", panelTecnicos);

        tabbedPane.addChangeListener(e -> {
            Component seleccionado = tabbedPane.getSelectedComponent();
            if (seleccionado == panelOrdenes) {
                panelOrdenes.refrescar();
            } else if (seleccionado == panelClientes) {
                panelClientes.refrescar();
            } else if (seleccionado == panelVehiculos) {
                panelVehiculos.refrescar();
            } else if (seleccionado == panelTecnicos) {
                panelTecnicos.refrescar();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }
}
