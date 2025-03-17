package com.bookmyshowspring.demo.persistence;

import java.util.Optional;

public interface IPersistence<T> {

    T save(T entity);

    Optional<T> findById(String id);

    void deleteById(String id);
}
