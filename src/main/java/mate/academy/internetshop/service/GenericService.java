package mate.academy.internetshop.service;

import java.util.List;

public interface GenericService<T, ID> {
    T create(T entity);

    T get(ID entityId);

    T update(T entity);

    boolean deleteById(ID entityId);

    boolean delete(T entity);

    List<T> getAll();
}
