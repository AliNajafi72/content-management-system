package ir.maktabsharif.database_jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DatabaseAccessObject<T> {
    Optional<T> get(long id);
    List<T> getAll() throws SQLException;
    void insert(T t) throws SQLException;
    void update(T t);
    void delete(T t);
}
