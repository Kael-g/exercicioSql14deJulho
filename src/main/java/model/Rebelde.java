package model;

public class Rebelde {
    private int id_rebelde;
    private String nome;
    private int idade;
    private String genero;
    private String base;
    private String status;
    private int denuncias;

    public Rebelde(int id_rebelde, String nome, int idade, String genero, String base, String status, int denuncias) {
        this.id_rebelde = id_rebelde;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.base = base;
        this.status = status;
        this.denuncias = denuncias;
    }

    public Rebelde(String nome, int idade, String genero, String base) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.base = base;
    }

    public int getId_rebelde() {
        return id_rebelde;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(int denuncias) {
        this.denuncias = denuncias;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
