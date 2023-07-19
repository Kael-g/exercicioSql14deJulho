package model;

public class Inventario {
    private int id_rebelde;
    private int id_produto;
    private int qtd_produto;

    public Inventario(int id_rebelde, int id_produto, int qtd_produto) {
        this.id_rebelde = id_rebelde;
        this.id_produto = id_produto;
        this.qtd_produto = qtd_produto;
    }

    public int getId_rebelde() {
        return id_rebelde;
    }

    public int getId_produto() {
        return id_produto;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(int qtd_produto) {
        this.qtd_produto = qtd_produto;
    }
}
