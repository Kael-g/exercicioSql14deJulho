package service;

import model.Rebelde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenciadorRebeldes {

    public boolean adicionarRebelde(Statement statement, Rebelde rebelde){
        String sql = String.format("insert into rebeldes(nome, idade, genero, base, status, denuncias) values ('%s', %d, '%s', '%s', 'ativo', 0)", rebelde.getNome(), rebelde.getIdade(), rebelde.getGenero(), rebelde.getBase());
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizarLocalizacao (Statement statement, int id_rebelde, String novaBase){
        String sql = String.format("update rebeldes set base = '%s' where id_rebelde = %d", novaBase, id_rebelde);
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean reportarRebeldeTraidor(Statement statement, int id_delator, int id_suspeito){
        if (verificaIdRebeldeAtivo(statement, id_delator) && verificaDenunciaIdNovo(statement, id_delator, id_suspeito)) {
            registraDenunciaTabela(statement, id_delator, id_suspeito);
            atualizaDenuncias(statement, id_suspeito);
            atualizaStatus(statement);
            return true;
        }
        return false;
    }

    public void atualizaStatus (Statement statement){
        String sql = "update rebeldes set status = 'inativo' where denuncias >= 3";
        try{
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void registraDenunciaTabela(Statement statement, int id_delator, int id_suspeito){
        String sql = String.format("insert into denuncias(id_suspeito, id_delator) values (%d, %d)", id_suspeito, id_delator);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizaDenuncias(Statement statement, int id_rebelde){
        String sql = String.format("select denuncias from rebeldes where id_rebelde = %d", id_rebelde);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int denuncias = resultSet.getInt("denuncias");
                sql = String.format("update rebeldes set denuncias = %d where id_rebelde = %d", denuncias+1, id_rebelde);
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean verificaIdRebeldeAtivo(Statement statement, int id_rebelde){
        String sql = String.format("select id_rebelde, status from rebeldes where id_rebelde = %d", id_rebelde);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getString("status").equalsIgnoreCase("ativo")){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verificaDenunciaIdNovo(Statement statement, int id_delator, int id_suspeito){
        String sql = String.format("select id_suspeito, id_delator from denuncias where id_suspeito = %d", id_suspeito);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getInt("id_delator") == id_delator){
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
