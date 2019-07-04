package Model;

public interface ICrud {

    abstract public boolean cadastrar();

    abstract public boolean alterar(String nome);

    abstract public boolean remover(String nome);

    abstract public void consultarPorNome(String nome);

    abstract public void listar();
}
