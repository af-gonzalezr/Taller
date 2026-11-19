package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrdenesVehiculoImplTest {
    private Repository<OrdenTrabajo> ordenRepository;
    private Repository<Vehiculo> vehiculoRepository;
    private OrdenesVehiculoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenRepository = Mockito.mock(Repository.class);
        vehiculoRepository = Mockito.mock(Repository.class);
        service = new OrdenesVehiculoImpl(ordenRepository, vehiculoRepository);
    }

    @Test
    void execute() {
        String placa = "V1";
        Vehiculo vehiculo = new Vehiculo(placa, null, "Auto");
        when(vehiculoRepository.buscarPorId(placa)).thenReturn(Optional.of(vehiculo));

        OrdenTrabajo orden = new OrdenTrabajo("1", vehiculo, null, null);
        List<OrdenTrabajo> ordenes = Collections.singletonList(orden);

        when(ordenRepository.filtrar(any())).thenReturn(ordenes);

        assertEquals(ordenes, service.execute(placa));
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(vehiculoRepository.buscarPorId("V1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("V1"));
    }
}
