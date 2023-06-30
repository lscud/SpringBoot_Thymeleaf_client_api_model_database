package br.com.mobguide.db.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory chupetinha;

    private static Connection connection;

    private ConnectionFactory(){
        //eager loading
        chupetinha = new ConnectionFactory();
    }

    public static ConnectionFactory getChupetinha(){
        if(chupetinha == null){
            chupetinha = new ConnectionFactory();
        }
        //lazy loading
        return chupetinha;
    }

    public static Connection getConnection(){
        if (connection == null){
            final String url = "jdbc:postgresql://localhost:5432/db_lds";
            final String user = "postgres";
            final String password = "postgres";

            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
