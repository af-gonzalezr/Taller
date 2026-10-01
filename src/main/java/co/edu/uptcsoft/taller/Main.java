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

        //datos de prueba
        // 1. Clientes (cedula, nombre, telefono, email)
        controlRegistros.crearCliente("1", "Carlos Perez", "3101234567", "carlos@gmail.com");
        controlRegistros.crearCliente("2", "Ana Gomez", "3209876543", "ana@gmail.com");

        // 2. Tecnicos (cedula, nombre, especialidad)
        controlRegistros.crearTecnico("808080", "Roberto Silva", "Electricidad");
        controlRegistros.crearTecnico("909090", "Martha Ruiz", "Mecanica General");

        // 3. Vehiculos (placa, marca, modeloAnio, idCliente, atributoEspecifico)
        controlRegistros.crearAutomovil("ABC123", "Toyota", "2020", "101010", "4");
        controlRegistros.crearMotocicleta("XYZ987", "Yamaha", "2022", "202020", "250");
        controlRegistros.crearCamion("TRK456", "Volvo", "2019", "101010", "12.5");


        controlOrdenes.crearOrden("O1", "ABC123", "808080", "Falla en sistema eléctrico");
        controlOrdenes.crearOrden("O2", "XYZ987", "909090", "Mantenimiento preventivo 10k km");

        // 5. Agregar un servicio de prueba a la primera orden (idOrden, nombreServicio, costo)
        controlOrdenes.agregarServicio("O1", "Cambio de batería", "180000");

        VistaMain vistaMain = new VistaMain(sc,registrosCLI,gestionCLI,consultasOrdenesCLI);
        vistaMain.mostrarMain();
    }
}