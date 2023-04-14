package br.com.mobguide.service;

import java.util.List;

public interface CrudService <T> {

    int create(T data);

    boolean deleteById(int id);

    T readById(int id);

    List<T> read();

    boolean updateById(int id, T data);

}
