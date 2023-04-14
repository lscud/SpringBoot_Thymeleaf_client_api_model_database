package br.com.mobguide.controller;

import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import org.apache.catalina.User;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CrudService<UserModel> service;


    @GetMapping("/detail/{id}")
    public String getUserDetail(@PathVariable("id") int id, Model model){
        final UserModel userModel = service.readById(id);
        model.addAttribute("usuario", userModel);
        return "user/detail";
    }


    @GetMapping("/list")
    public String getUsersPage(final Model model){

        List<UserModel> user = service.read();

        if(user == null){
            model.addAttribute("usuarios", new ArrayList<>());
        }else{
            model.addAttribute("usuarios", user);

        }

        return "user/list";
    }

    @GetMapping("/create")
    public String getCreatePage(){
        return "user/create";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") final int id, final Model model){

        UserModel userModel = service.readById(id);

        model.addAttribute("usuario", userModel);

        return "user/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") final int id, final Model model){

        boolean response = service.deleteById(id);

        return getUsersPage(model);
    }

    @PostMapping("/sign-up")
    public String signUp(final Model model, final UserModel user){
        service.create(user);
        return getUsersPage(model);

    }

    @PostMapping("/update")
    public String update(final UserModel user, final Model model){

        boolean response = service.updateById(user.getId(), user);

        return getUserDetail(user.getId(), model);

    }
}
