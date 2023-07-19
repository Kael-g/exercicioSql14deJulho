package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Relatorios {
    public double contarPorcentagemTraidores(Statement statement){
        int total=0, traidores=0;
        String sql = "select status from rebeldes";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                total++;
                if (resultSet.getString("status").equalsIgnoreCase("inativo")){
                    traidores++;
                }
            }
            if (total > 0){
                return (double) (traidores /total)*100;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public double contarPorcentagemRebeldes(Statement statement){
        int total=0, rebeldes=0;
        String sql = "select status from rebeldes";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                total++;
                if (resultSet.getString("status").equalsIgnoreCase("ativo")){
                    rebeldes++;
                }
            }
            if (total > 0){
                return (double) (rebeldes/total)*100;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
