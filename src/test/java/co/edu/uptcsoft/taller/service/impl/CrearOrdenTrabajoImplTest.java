package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrearOrdenTrabajoImplTest {
    private Repository<Vehiculo> vehiculoRepository;
    private Repository<Tecnico> tecnicoRepository;
    private Repository<OrdenTrabajo> ordenTrabajoRepository;
    private CrearOrdenTrabajoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        vehiculoRepository = mock(Repository.class);
        tecnicoRepository = mock(Repository.class);
        ordenTrabajoRepository = mock(Repository.class);
        service = new CrearOrdenTrabajoImpl(vehiculoRepository, tecnicoRepository, ordenTrabajoRepository);
    }

    @Test
    void registraExitosamente() {
        String idOrden = "O1";
        String idVehiculo = "V1";
        String idTecnico = "T1";

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.empty());
        when(tecnicoRepository.buscarPorId(idTecnico)).thenReturn(Optional.of(new Tecnico(idTecnico, "Pedro", "Mecánica")));
        when(vehiculoRepository.buscarPorId(idVehiculo)).thenReturn(Optional.of(new Automovil(idVehiculo, "Toyota", 2024, null, 4)));

        OrdenTrabajo resultado = service.execute(idOrden, LocalDateTime.now(), idVehiculo, idTecnico, "Observaciones");

        assertNotNull(resultado);
        verify(ordenTrabajoRepository).guardar(resultado);
    }

    @Test
    void lanzaExcepcionSiYaExiste() {
        String idOrden = "O1";
        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.of(mock(OrdenTrabajo.class)));

        assertThrows(ServiceException.class, () -> service.execute(idOrden, LocalDateTime.now(), "V1", "T1", "Observaciones"));
    }

    @Test
    void lanzaExcepcionSiTecnicoNoExiste() {
        String idOrden = "O1";
        String idTecnico = "T1";

        when(ordenTrabajoRepository.buscarPorId(idOrden)).thenReturn(Optional.empty());
        when(tecnicoRepository.buscarPorId(idTecnico)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.execute(idOrden, LocalDateTime.now(), "V1", idTecnico, "Observaciones"));
    }
}
