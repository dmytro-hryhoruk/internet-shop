package mate.academy.internetshop.dao;

import java.util.Optional;

public interface GenericDao<T, ID> {
    T create(T entity);

    Optional<T> get(ID entityId);

    T update(T entity);

    boolean deleteById(ID entityId);

    boolean delete(T entity);
}
