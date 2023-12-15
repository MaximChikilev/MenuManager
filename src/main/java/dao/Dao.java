package dao;

import java.util.List;

public interface Dao<T> {
    void create(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> getAll();
}
