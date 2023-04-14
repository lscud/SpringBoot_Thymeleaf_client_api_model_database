package br.com.mobguide.controller;

import br.com.mobguide.model.entities.Game;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CrudService<Game> service;

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

    @GetMapping("/edit")
    public String getEditPage() {
        return "game/edit";
    }

    @PostMapping("/sign-up")
    public String signUp(final Model model, final Game game){
        service.create(game);
        return getGamePage(model);

    }
}