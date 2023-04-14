package br.com.mobguide.db.dao.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.model.entities.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.mobguide.model.enums.GameType.ACTION;

@Repository
public class GameFakeDaoImpl implements CrudDao<Game> {

    private List<Game> games = new ArrayList<>();


    public GameFakeDaoImpl() {

        Game gameOne = new Game();
        gameOne.setId(1);
        gameOne.setName("Sex on de beach");
        gameOne.setPrice(10);
        gameOne.setType(ACTION);


        Game gameTwo = new Game();
        gameTwo.setId(2);
        gameTwo.setName("Homer e Jerry");
        gameTwo.setPrice(123);
        gameTwo.setType(ACTION);

        Game gameThird = new Game();
        gameThird.setId(3);
        gameThird.setName("tacando o chinelo no pedrinho");
        gameThird.setPrice(1330);
        gameThird.setType(ACTION);

        games.add(gameOne);
        games.add(gameTwo);
        games.add(gameThird);
    }

    @Override
    public int create(Game data) {
        games.add(data);
        return data.getId();
    }

    @Override
    public boolean deleteById(int id) {
        Game gameToBeRemoved = null;
        for (Game game: games){
            if(game.getId() == id){
                gameToBeRemoved = game;
                break;
            }
        }
        if(gameToBeRemoved != null){
            games.remove(gameToBeRemoved);
            return true;
        }
        return false;
    }

    @Override
    public Game readById(int id) {

        if(games.isEmpty()){
            return null;
        }

        for (Game auxgame: games){
            if(auxgame.getId() == id)
                return auxgame;
        }
        return null;
    }

    @Override
    public List<Game> read() {
        return games;
    }

    @Override
    public boolean updateById(int id, Game data) {
        Game gameToBrUpdated = null;

        for (Game auxgame : games) {
            if (auxgame.getId() == id) {
                gameToBrUpdated = auxgame;
                break;
            }
        }

        if (gameToBrUpdated != null) {
            gameToBrUpdated.setName(data.getName());
            return true;
        }
        return false;
    }
}
