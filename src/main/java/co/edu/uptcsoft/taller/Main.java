package co.edu.uptcsoft.taller;

import co.edu.uptcsoft.taller.control.ControlConsultas;
import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.OrdenRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.*;
import co.edu.uptcsoft.taller.service.impl.*;
import co.edu.uptcsoft.taller.vista.VistaMain;
import co.edu.uptcsoft.taller.vista.cli.consultas.ConsultarOrdenPorIdCLI;
import co.edu.uptcsoft.taller.vista.cli.consultas.ConsultarOrdenesClienteCLI;
import co.edu.uptcsoft.taller.vista.cli.consultas.ConsultasOrdenesCLI;
import co.edu.uptcsoft.taller.vista.cli.gestion.*;
import co.edu.uptcsoft.taller.vista.cli.registros.*;

import java.util.Scanner;

public class Main  {
    static void main() {
        //repositorios
        Repository<Cliente> clienteRepository = new ClienteRepository();
        Repository<Vehiculo> vehiculoRepository = new VehiculoRepository();
        Repository<Tecnico> tecnicoRepository = new TecnicoRepository();
        OrdenRepo ordenRepository = new OrdenRepository();

        //servicios casos de uso
        //registros
        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        RegistrarTecnico registrarTecnico = new RegistrarTecnicoImpl(tecnicoRepository);
        RegistrarAuto registrarAuto = new RegistrarAutoImpl(clienteRepository,vehiculoRepository);
        RegistrarMoto registrarMoto = new RegistrarMotoImpl(vehiculoRepository,clienteRepository);
        RegistrarCamion registrarCamion = new RegistrarCamionImpl(clienteRepository, vehiculoRepository);

        //gestion
        CrearOrdenTrabajo crearOrdenTrabajo = new CrearOrdenTrabajoImpl(vehiculoRepository,tecnicoRepository,ordenRepository);
        AgregarTrabajoAOrden agregarTrabajoAOrden = new AgregarTrabajoAOrdenImpl(ordenRepository);
        FinalizarOrden finalizarOrden = new FinalizarOrdenImpl(ordenRepository);
        CancelarOrden cancelarOrden = new CancelarOrdenImpl(ordenRepository);

        //consulta de ordenes
        OrdenesCliente ordenesCliente = new OrdenesClienteImpl(clienteRepository,ordenRepository);
        OrdenesEstado ordenesEstado = new OrdenesEstadoImpl(ordenRepository);
        TodasOrdenes todasOrdenes = new TodasOrdenesImpl(ordenRepository);
        BuscarOrden buscarOrden = new BuscarOrdenImpl(ordenRepository);

        //controladores

        ControlRegistros controlRegistros = new ControlRegistros(registrarCliente,
                registrarTecnico,
                registrarAuto,
                registrarMoto,
                registrarCamion);

        ControlOrdenes controlOrdenes = new ControlOrdenes(crearOrdenTrabajo,
                finalizarOrden,
                cancelarOrden,
                agregarTrabajoAOrden);

        ControlConsultas controlConsultas = new ControlConsultas(buscarOrden,
                todasOrdenes,
                ordenesCliente,
                ordenesEstado);


        //menus

        Scanner sc = new Scanner(System.in);
        //registro

        
        RegistrarClienteCLI registrarClienteCLI = new RegistrarClienteCLI(sc,controlRegistros);
        RegistrarTecnicoCLI registrarTecnicoCLI = new RegistrarTecnicoCLI(sc,controlRegistros);
        
        RegistrarAutomovilCLI registrarAutomovilCLI = new RegistrarAutomovilCLI(sc,controlRegistros);
        RegistrarMotocicletaCLI registrarMotocicletaCLI = new RegistrarMotocicletaCLI(sc,controlRegistros);
        RegistrarCamionCLI registrarCamionCLI = new RegistrarCamionCLI(sc,controlRegistros);
        
        RegistrarVehiculoCLI registrarVehiculoCLI = new RegistrarVehiculoCLI(sc,
                registrarAutomovilCLI,
                registrarMotocicletaCLI,
                registrarCamionCLI);
        
        
        RegistrosCLI registrosCLI = new RegistrosCLI(sc,registrarClienteCLI,registrarTecnicoCLI,registrarVehiculoCLI);
        //gestion

        AgregarServicioCLI agregarServicioCLI = new AgregarServicioCLI(sc,controlOrdenes);
        CancelarOrdenCLI cancelarOrdenCLI = new CancelarOrdenCLI(sc,controlOrdenes);
        CrearOrdenCLI crearOrdenCLI = new CrearOrdenCLI(sc,controlOrdenes);
        FinalizarOrdenCLI finalizarOrdenCLI = new FinalizarOrdenCLI(sc,controlOrdenes);

        GestionCLI gestionCLI = new GestionCLI(sc,
                crearOrdenCLI,
                finalizarOrdenCLI,
                cancelarOrdenCLI,
                agregarServicioCLI);

        //consultas

        ConsultarOrdenesClienteCLI consultarOrdenesClienteCLI = new ConsultarOrdenesClienteCLI(sc,controlConsultas);
        ConsultarOrdenPorIdCLI consultarOrdenPorIdCLI = new ConsultarOrdenPorIdCLI(sc,controlConsultas);

        ConsultasOrdenesCLI consultasOrdenesCLI = new ConsultasOrdenesCLI(sc,
                controlConsultas,
                consultarOrdenPorIdCLI,
                consultarOrdenesClienteCLI);

        VistaMain vistaMain = new VistaMain(sc,registrosCLI,gestionCLI,consultasOrdenesCLI);
        vistaMain.mostrarMain();
    }
}