package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
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

class OrdenesTecnicoImplTest {
    private Repository<OrdenTrabajo> ordenRepository;
    private Repository<Tecnico> tecnicoRepository;
    private OrdenesTecnicoImpl service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ordenRepository = Mockito.mock(Repository.class);
        tecnicoRepository = Mockito.mock(Repository.class);
        service = new OrdenesTecnicoImpl(ordenRepository, tecnicoRepository);
    }

    @Test
    void execute() {
        String idTecnico = "1";
        Tecnico tecnico = new Tecnico(idTecnico, "Pepe");
        when(tecnicoRepository.buscarPorId(idTecnico)).thenReturn(Optional.of(tecnico));

        OrdenTrabajo orden = new OrdenTrabajo("1", null, tecnico, null);
        List<OrdenTrabajo> ordenes = Collections.singletonList(orden);

        when(ordenRepository.filtrar(any())).thenReturn(ordenes);

        assertEquals(ordenes, service.execute(idTecnico));
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        when(tecnicoRepository.buscarPorId("1")).thenReturn(Optional.empty());
        assertThrows(ElementoNoEncontradoException.class, () -> service.execute("1"));
    }
}
