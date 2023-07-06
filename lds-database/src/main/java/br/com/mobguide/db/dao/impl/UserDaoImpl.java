package br.com.mobguide.db.dao.impl;

import br.com.mobguide.db.dao.CrudDao;
import br.com.mobguide.db.dao.connection.ConnectionFactory;
import br.com.mobguide.model.entities.UserModel;
import br.com.mobguide.model.enums.UserType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class UserDaoImpl implements CrudDao<UserModel> {
    @Override
    public int create(UserModel data) {

        String sql = "INSERT INTO usuario(nome_usuario, senha, nome_completo, email, tipo, ";
        sql += " data_nascimento, ultimo_acesso, criado_em) ";
        sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        final Connection connection = ConnectionFactory.getConnection();

        try{
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getUsername());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.setString(3, data.getFullName());
            preparedStatement.setString(4, data.getEmail());
            preparedStatement.setString(5, data.getType().toString());

            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            preparedStatement.execute();
            ResultSet genenereteKeys = preparedStatement.getGeneratedKeys();
            if(genenereteKeys.next()){
                return genenereteKeys.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean deleteById(int id) {

        final String sql = "DELETE FROM usuario WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();
        final PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1 , id);

            preparedStatement.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UserModel readById(int id) {
        UserModel userModel;

        final Connection connection = ConnectionFactory.getConnection();

        final String sql = "SELECT * FROM usuario WHERE id = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new SQLException();
            }

            userModel = new UserModel();
            userModel.setUsername(resultSet.getString("nome_usuario"));
            userModel.setFullName(resultSet.getString("nome_completo"));
            userModel.setEmail(resultSet.getString("email"));
            userModel.setId(resultSet.getInt("id"));
            userModel.setPassword(resultSet.getString("senha"));
            userModel.setType(UserType.CLIENT);

        } catch (SQLException e){
            e.printStackTrace();
            userModel = null;
        }

        return userModel;
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

        boolean result = false;

        final String sql = "UPDATE usuario SET nome_completo = ?, email = ? WHERE id = ?";

        final Connection connection = ConnectionFactory.getConnection();

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, data.getFullName());
            preparedStatement.setString(2,data.getEmail());
            preparedStatement.setInt(3, data.getId());

            result = preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
