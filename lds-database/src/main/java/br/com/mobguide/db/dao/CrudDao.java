package br.com.mobguide.db.dao;

import java.util.List;

public interface CrudDao<T> {

    int create(T data);

    boolean deleteById(int id);

    T readById(int id);

    List<T> read();

    boolean updateById(int id, T data);


}
