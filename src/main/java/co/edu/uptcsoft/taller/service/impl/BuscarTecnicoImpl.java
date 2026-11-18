package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.BuscarTecnico;

public class BuscarTecnicoImpl implements BuscarTecnico {

    private final Repository<Tecnico> tecnicoRepository;

    public BuscarTecnicoImpl(Repository<Tecnico> tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public Tecnico execute(String idTecnico) {
        return tecnicoRepository
                .buscarPorId(idTecnico)
                .orElseThrow(()-> new ElementoNoEncontradoException("El tecnico no existe"));
    }
}
