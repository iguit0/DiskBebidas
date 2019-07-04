package Model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Pessoa {

    protected static final AtomicInteger cont = new AtomicInteger(0);
    protected int id;
    protected String nome, telefone, data_nascimento;

    public Pessoa(String telefone, String nome, String data_nascimento) {
        this.id = cont.incrementAndGet();
        this.telefone = telefone;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

}
