package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReasignarTecnicoOrdenImplTest {
    private Repository<OrdenTrabajo> ordenRepository;
    private Repository<Tecnico> tecnicoRepository;
    private ReasignarTecnicoOrdenImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenRepository = Mockito.mock(Repository.class);
        tecnicoRepository = Mockito.mock(Repository.class);
        service = new ReasignarTecnicoOrdenImpl(ordenRepository, tecnicoRepository);
    }

    @Test
    void execute() {
        String idOrden = "1";
        String idTecnico = "T1";
        Tecnico tecnico = new Tecnico(idTecnico, "Pepe");
        OrdenTrabajo orden = new OrdenTrabajo(idOrden, null, null, EstadoOrden.EN_PROCESO);

        when(ordenRepository.buscarPorId(idOrden)).thenReturn(Optional.of(orden));
        when(tecnicoRepository.buscarPorId(idTecnico)).thenReturn(Optional.of(tecnico));
        when(ordenRepository.guardar(any())).thenReturn(orden);

        OrdenTrabajo resultado = service.execute(idOrden, idTecnico);

        assertEquals(tecnico, resultado.getTecnico());
    }

    @Test
    void lanzaExcepcionSiOrdenNoExiste() {
        when(ordenRepository.buscarPorId("1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1", "T1"));
    }
}
