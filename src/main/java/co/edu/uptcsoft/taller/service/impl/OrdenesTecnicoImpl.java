package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.OrdenesTecnico;

import java.util.List;

public class OrdenesTecnicoImpl implements OrdenesTecnico {

    private final Repository<OrdenTrabajo> ordenTrabajoRepository;
    private final Repository<Tecnico> tecnicoRepository;

    public OrdenesTecnicoImpl(Repository<OrdenTrabajo> ordenTrabajoRepository, Repository<Tecnico> tecnicoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(String idTecnico) {

        tecnicoRepository
                .buscarPorId(idTecnico)
                .orElseThrow(()-> new ElementoNoEncontradoException("El tecnico no existe"));

        return ordenTrabajoRepository.filtrar(o->o.getTecnico().getId().equalsIgnoreCase(idTecnico));
    }
}
