package br.com.mobguide.service.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.model.entities.Game;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements CrudService<Game> {

    @Autowired
    private CrudDao<Game> dao;

    @Override
    public int create(Game data) {
        if(data == null){
            return -1;
        }

        return dao.create(data);
    }

    @Override
    public boolean deleteById(int id) {
        if(id<0){
            return false;
        }
        return dao.deleteById(id);
    }

    @Override
    public Game readById(int id) {
        if(id < 0){
            return null;
        }
        return dao.readById(id);
    }

    @Override
    public List<Game> read() {
        return dao.read();
    }

    @Override
    public boolean updateById(int id, Game data) {
        if(id < 0){
            return false;
        }
        if (data == null || data.getId() <= 0){
            return false;
        }
        if(id != data.getId()){
            return false;
        }

        if(data.getName().isEmpty()){
            return false;
        }

        return dao.updateById(id, data);
    }
}
