package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    Connection connection;

    public boolean startConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/resistenciastarwars",
                    "postgres", "admin");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
