package co.edu.uptcsoft.taller.repository.impl;

import java.util.Optional;

public interface Repository<T> {
    T guardar(T element);
    Optional<T> buscarPorId(String id);
    void eliminar(String id);
}
