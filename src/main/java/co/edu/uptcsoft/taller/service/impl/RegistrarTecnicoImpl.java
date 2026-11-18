package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;

public class RegistrarTecnicoImpl implements RegistrarTecnico {

    private final Repository<Tecnico> tecnicoRepository;

    public RegistrarTecnicoImpl(Repository<Tecnico> tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public Tecnico execute(String idTecnico, String nombre, String especialidad) {
        if(tecnicoRepository.buscarPorId(idTecnico).isPresent()){
            throw new BusinessRuleException("Ya existe un técnico con esa id");
        }
        Tecnico t = new Tecnico(idTecnico,nombre,especialidad);
        return tecnicoRepository.guardar(t);
    }
}
