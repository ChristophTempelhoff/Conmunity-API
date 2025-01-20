package at.conmunity.API.Interface;

public interface IModel<T> {
    int hashCode();
    boolean equals(Object other);
    String toString();
    String toJSON();
    T doMap(T other);
}
