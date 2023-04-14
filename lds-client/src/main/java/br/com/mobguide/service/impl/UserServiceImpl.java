package br.com.mobguide.service.impl;

import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.model.enums.UserType;
import br.com.mobguide.service.CrudService;
import br.com.mobguide.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements CrudService<UserModel> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/user";
    @Autowired
    private RestService<UserModel> restService;


    @Override
    public int create(UserModel data) {
        if(data == null){
            return -1;
        }
        if(data.getUsername().isEmpty() || data.getPassword().isEmpty() || data.getEmail().isEmpty()){
            return -1;
        }

        data.setType(UserType.CLIENT);
        data.setActive(true);

        int createdId = restService.post(BASE_ENDPOINT + "/create", data);
        return createdId;

    }

    @Override
    public boolean deleteById(int id) {
        return restService.delete(BASE_ENDPOINT + "/delete/" + id);
    }

    @Override
    public UserModel readById(int id) {
        UserModel user =  restService.getById(BASE_ENDPOINT + "/find/" + id, UserModel.class);
        return user;
    }

    @Override
    public List<UserModel> read() {

        List<UserModel> users = restService.get(BASE_ENDPOINT + "/find");
        return users;
    }

    @Override
    public boolean updateById(int id, UserModel data) {

        UserModel userModel = readById(data.getId());

        userModel.setFullName(data.getFullName());
        userModel.setEmail(data.getEmail());

        return restService.put(BASE_ENDPOINT + "/update/" + id, userModel);
    }
}
