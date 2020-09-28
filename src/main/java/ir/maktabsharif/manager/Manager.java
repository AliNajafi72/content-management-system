package ir.maktabsharif.manager;

import java.util.List;
import java.util.Optional;

public interface Manager<E> {
    Optional<E> get(long id);
    List<E> getAll();
    void insert(E t);
    void update(E t);
    void delete(E t);
}
