package br.com.mobguide.db.dao.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.db.dao.connection.ConnectionFactory;
import br.com.mobguide.model.entities.UserModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class UserDaoImpl implements CrudDao<UserModel> {
    @Override
    public int create(UserModel data) {
        return 0;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public UserModel readById(int id) {
        return null;
    }

    @Override
    public List<UserModel> read() {
        final List<UserModel> users = new ArrayList<>();

        Connection connection = ConnectionFactory.getConnection();

        final String sql = "SELECT * FROM usuario;";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("nome_completo"));
                user.setPassword(resultSet.getString("senha"));
                user.setUsername(resultSet.getString("nome_usuario"));
                user.setActive(resultSet.getBoolean("esta_ativo"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean updateById(int id, UserModel data) {
        return false;
    }
}
