package co.edu.uptcsoft.taller.repository;

import java.util.Optional;

public interface Repository<T> {
    T guardar(T element);
    Optional<T> buscarPorId(String id);
    boolean eliminar(String id);
}
