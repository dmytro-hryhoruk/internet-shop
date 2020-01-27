package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exceptions.DataProcessingException;

public interface GenericDao<T, ID> {
    T create(T entity) throws DataProcessingException;

    Optional<T> get(ID entityId) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean deleteById(ID entityId) throws DataProcessingException;

    boolean delete(T entity) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
