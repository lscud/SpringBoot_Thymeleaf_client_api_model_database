package br.com.mobguide.controller;

import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import br.com.mobguide.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private CrudService<UserModel> service;


    @GetMapping("/find/{id}")
    public ResponseEntity<UserModel> getUserByID(@PathVariable ("id") final int id) {
        UserModel user = service.readById(id);
        if(user == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/find")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> list = service.read();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public  ResponseEntity<Integer> createUser(@RequestBody final UserModel user){
        int response = service.create(user);
        if(response == -1){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Boolean> delete(@PathVariable ("id") final int id){
        service.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Boolean> update(@PathVariable ("id") final int id,
                                           @RequestBody final UserModel user){

        boolean response = service.updateById(id,user);

        if(!response){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}
