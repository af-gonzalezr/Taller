package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ListarTecnicos;

import java.util.List;

public class ListarTecnicosImpl implements ListarTecnicos {

    private final Repository<Tecnico> tecnicoRepository;

    public ListarTecnicosImpl(Repository<Tecnico> tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }


    @Override
    public List<Tecnico> execute() {
        return tecnicoRepository.listar();
    }
}
