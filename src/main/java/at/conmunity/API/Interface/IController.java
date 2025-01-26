package at.conmunity.API.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<T> {
    ResponseEntity<T> create(T obj);
    ResponseEntity<List<T>> get(Long ID);
    ResponseEntity<T> update(T obj);
    ResponseEntity<T> delete(Long id);
}
