package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.exceptions.DataProcessingException;

public interface GenericService<T, ID> {
    T create(T entity) throws DataProcessingException;

    T get(ID entityId) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean deleteById(ID entityId) throws DataProcessingException;

    boolean delete(T entity) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
