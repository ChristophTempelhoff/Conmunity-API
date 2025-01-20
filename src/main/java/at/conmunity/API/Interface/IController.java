package at.conmunity.API.Interface;

import java.util.List;
import java.util.UUID;

public interface IController<T> {
    T create(T obj);
    List<T> getAll();
    T getByID(Long id);
    T update(T obj);
    void delete(Long id);
}
