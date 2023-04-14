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


    private static final String BASE_API_GAME = "http://localhost:8081/api/game";
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

        int createdId = restService.post(BASE_API_GAME + "/create", data);
        return createdId;

    }

    @Override
    public boolean deleteById(int id) {
        return restService.delete(BASE_API_GAME + "/delete/" + id);
    }

    @Override
    public Game readById(int id) {
       Game game = restService.getById( BASE_API_GAME + "/find/" + id, Game.class);
       return game;
    }

    @Override
    public List<Game> read() {
        List<Game> games = restService.get(BASE_API_GAME + "/find");
        return games;
    }

    @Override
    public boolean updateById(int id, Game data) {

        Game game = readById(data.getId());

        game.setName(data.getName());
        game.setPrice(data.getPrice());

        return restService.put(BASE_API_GAME + "/update/" + id, game);
    }

}
