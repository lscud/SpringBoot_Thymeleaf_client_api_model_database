package br.com.mobguide.db.dao.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.model.enums.UserType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserFakeDaoImpl implements CrudDao<UserModel> {

    private List<UserModel> users = new ArrayList<>();


    public UserFakeDaoImpl() {

        UserModel firstUser = new UserModel();
        firstUser.setId(1);
        firstUser.setUsername("pituca");
        firstUser.setFullName("pitucao do bune furado");
        firstUser.setEmail("pituquinha@y");
        firstUser.setActive(true);
        firstUser.setPassword("11111111");
        firstUser.setType(UserType.CLIENT);

        UserModel secondUser = new UserModel();
        secondUser.setId(2);
        secondUser.setUsername("tarzan");
        secondUser.setFullName("sem cipo");
        secondUser.setEmail("reidafloresta@y");
        secondUser.setActive(true);
        secondUser.setPassword("11111111");
        secondUser.setType(UserType.CLIENT);

        UserModel thirdUser = new UserModel();
        thirdUser.setId(3);
        thirdUser.setUsername("carvalho");
        thirdUser.setFullName("carvalho maciço da silva");
        thirdUser.setEmail("ciu@y");
        thirdUser.setActive(true);
        thirdUser.setPassword("11111111");
        thirdUser.setType(UserType.CLIENT);

        users.add(firstUser);
        users.add(secondUser);
        users.add(thirdUser);

    }

    @Override
    public int create(UserModel data) {
        users.add(data);
        return data.getId();
    }

    @Override
    public boolean deleteById(int id) {
        UserModel userToBeRemoved = null;
        for (UserModel user: users){
            if(user.getId() == id){
                userToBeRemoved = user;
                break;
            }
        }
        if(userToBeRemoved != null){
            users.remove(userToBeRemoved);
            return true;
        }
        return false;
    }

    @Override
    public UserModel readById(int id) {

        if(users.isEmpty()){
            return null;
        }

        for (UserModel auxUser: users){
            if(auxUser.getId() == id)
                return auxUser;
        }
        return null;
    }

    @Override
    public List<UserModel> read() {
        return users;
    }

    @Override
    public boolean updateById(int id, UserModel data) {
        UserModel userToBrUpdated = null;

        for (UserModel auxUser : users) {
            if (auxUser.getId() == id) {
                userToBrUpdated = auxUser;
                break;
            }
        }

        if (userToBrUpdated != null) {
            userToBrUpdated.setFullName(data.getFullName());
            userToBrUpdated.setEmail(data.getEmail());
            return true;
        }
        return false;
    }
}
