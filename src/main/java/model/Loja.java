package model;

public class Loja {
    private int id_produto;
    private String produto;
    private int valor;

    public Loja(int id_produto, String produto, int valor) {
        this.id_produto = id_produto;
        this.produto = produto;
        this.valor = valor;
    }

    public int getId_produto() {
        return id_produto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
