package br.com.mobguide.service.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements CrudService<UserModel> {

    @Autowired
    private CrudDao<UserModel> dao;

    @Override
    public int create(UserModel data) {
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
    public UserModel readById(int id) {
        if(id < 0){
            return null;
        }
        return dao.readById(id);
    }

    @Override
    public List<UserModel> read() {
        return dao.read();
    }

    @Override
    public boolean updateById(int id, UserModel data) {
        if(id < 0){
            return false;
        }
        if (data == null || data.getId() <= 0){
            return false;
        }
        if(id != data.getId()){
            return false;
        }

        if(data.getUsername().isEmpty()){
            return false;
        }
        return dao.updateById(id, data);
    }
}
