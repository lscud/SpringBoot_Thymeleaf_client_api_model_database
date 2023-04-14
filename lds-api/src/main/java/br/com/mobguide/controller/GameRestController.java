package br.com.mobguide.controller;

import br.com.mobguide.model.entities.Game;
import br.com.mobguide.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameRestController {
    @Autowired
    private CrudService<Game> service;

    @GetMapping("/find/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable ("id") final int id) {
        Game game = service.readById(id);
        if(game == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
    @GetMapping("/find")
    public ResponseEntity<List<Game>> getGame() {
        List<Game> list = service.read();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public  ResponseEntity<Integer> createGame(@RequestBody final Game user){
        int response = service.create(user);
        if(response == -1){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Boolean> deleteGame(@PathVariable ("id") final int id){
        return ResponseEntity.ok(true);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Boolean> updateGame(@PathVariable ("id") final int id,
                                           @RequestBody final Game game){

        boolean response = service.updateById(id,game);

        if(!response){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}
