package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VisualizarInformacoes {
    public void verRebelde(Statement statement, int id_rebelde){
        String sql = String.format("select * from rebeldes where id_rebelde = %d", id_rebelde);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_rebelde");
                String nome = resultSet.getString("nome");
                String status = (resultSet.getInt("denuncias")<3 ? "REBELDE" : "TRAIDOR");
                int idade = resultSet.getInt("idade");
                String genero = resultSet.getString("genero");
                String base = resultSet.getString("base");
                System.out.printf("ID: %d \t Nome: %s (%s) \t Idade: %d \t Gênero: %s \t Base atual: %s\n\n", id, nome, status, idade, genero, base);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verInventario(Statement statement, int id_rebelde){
        String sql = String.format("select rebeldes.nome, loja.produto, inventario.qtd_produto from rebeldes inner join inventario on rebeldes.id_rebelde = inventario.id_rebelde inner join loja on inventario.id_produto = loja.id_produto where rebeldes.id_rebelde =  %d", id_rebelde);
        boolean primeiroLoop = true;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (primeiroLoop){
                    System.out.printf("Inventário de %s\n", resultSet.getString("nome"));
                    System.out.printf("%-20s %s\n", "PRODUTO", "QUANTIDADE");
                    primeiroLoop=false;
                }
                System.out.printf("%-20s %d\n", resultSet.getString("produto"), resultSet.getInt("qtd_produto"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void mostrarTodosOsRebeldes(Statement statement){
        String sql =  "select * from rebeldes";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_rebelde");
                String nome = resultSet.getString("nome");
                String status = (resultSet.getInt("denuncias")<3 ? "REBELDE" : "TRAIDOR");
                int idade = resultSet.getInt("idade");
                String genero = resultSet.getString("genero");
                String base = resultSet.getString("base");
                System.out.printf("ID: %d \t Nome: %s (%s) \t Idade: %d \t Gênero: %s \t Base atual: %s\n\n", id, nome, status, idade, genero, base);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verLoja(Statement statement){
        String sql = "select * from loja";
        boolean primeiroLaco = true;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (primeiroLaco){
                    System.out.printf("%-20s %-30s %s\n", "ID DO PRODUTO", "PRODUTO", "VALOR");
                    primeiroLaco = false;
                }
                System.out.printf("%-20s %-30s %s\n", resultSet.getInt("id_produto"), resultSet.getString("produto"), resultSet.getInt("valor"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
