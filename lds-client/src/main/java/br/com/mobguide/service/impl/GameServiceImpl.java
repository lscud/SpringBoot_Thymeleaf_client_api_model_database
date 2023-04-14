package br.com.mobguide.service.impl;

import br.com.mobguide.model.entities.Game;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.model.enums.UserType;
import br.com.mobguide.service.CrudService;
import br.com.mobguide.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements CrudService<Game> {


    @Autowired
    private RestService<Game> restService;


    @Override
    public int create(Game data) {
        if(data == null){
            return -1;
        }
        if(data.getName().isEmpty()){
            return -1;
        }

        int createdId = restService.post("http://localhost:8081/api/game/create", data);
        return createdId;

    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Game readById(int id) {
        return null;
    }

    @Override
    public List<Game> read() {
        List<Game> games = restService.get("http://localhost:8081/api/game/find");
        return games;
    }

    @Override
    public boolean updateById(int id, Game data) {
        return false;
    }
}
