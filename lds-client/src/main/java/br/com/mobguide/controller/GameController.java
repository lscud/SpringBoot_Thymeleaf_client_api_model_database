package br.com.mobguide.controller;

import br.com.mobguide.model.entities.Game;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CrudService<Game> service;

    @GetMapping("/detail/{id}")
    public String getGameDetail(@PathVariable("id") int id, Model model){ //Esse Model é transparente pra gent ené? digo, quando clica no botaozinho la assume-se que vem um Model quando clciamos
        final Game game = service.readById(id);
        model.addAttribute("jogo", game);
        return "game/detail";
    }


    @GetMapping("/list")
    public String getGamePage(final Model model) {

        List<Game> game = service.read();

        if (game == null) {
            model.addAttribute("jogos", new ArrayList<>());
        } else {
            model.addAttribute("jogos", game);

        }

        return "game/list";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "game/create";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") final int id, final Model model) {

        Game game = service.readById(id);

        model.addAttribute("jogo", game);

        return "game/edit";
    }

    @PostMapping("/sign-up")
    public String signUp(final Model model, final Game game){
        service.create(game);
        return getGamePage(model);

    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") final int id, final Model model){

        boolean response = service.deleteById(id);

        return getGamePage(model);
    }

    @PostMapping("/update")
    public String update(final Model model, final Game game){

        boolean response = service.updateById(game.getId(), game);

        return getGameDetail(game.getId(), model);

    }
}