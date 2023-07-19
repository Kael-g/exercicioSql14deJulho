package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Compras {

    public boolean comprar(Statement statement, int id_rebelde, int id_produto, int quantidade, boolean podeComprar){
        if (podeComprar){
            boolean novoProduto = true;
            String sql = String.format("select loja.id_produto, inventario.qtd_produto from loja full join inventario using (id_produto) full join rebeldes using(id_rebelde) where rebeldes.id_rebelde = %d", id_rebelde);
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    if (resultSet.getInt("id_produto") == id_produto){
                        novoProduto = false;
                    }
                }
                if (novoProduto){
                    sql = String.format("insert into inventario values (%d, %d, %d)", id_rebelde, id_produto, quantidade);

                } else {
                    sql = String.format("update inventario set qtd_produto = %d where id_rebelde = %d and id_produto = %d", resultSet.getInt("qtd_produto")+quantidade, id_rebelde, id_produto);
                }
                statement.executeUpdate(sql);
                return true;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

}
