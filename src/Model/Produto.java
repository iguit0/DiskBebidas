package Model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Produto {

    protected static final AtomicInteger cont = new AtomicInteger(0);
    protected int id;
    protected int estoque;
    protected double preco;
    protected String nome, descricao;

    public Produto(int estoque, double preco, String nome, String descricao) {
        this.id = cont.incrementAndGet();
        this.estoque = estoque;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
