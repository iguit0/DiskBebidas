package Model;

public class Bebida extends Produto {

    private boolean alcoolico;
    private String quantidade;

    public Bebida(int estoque, double preco, String nome, String descricao, boolean alcoolico, String quantidade) {
        super(estoque, preco, nome, descricao);
        this.alcoolico = alcoolico;
        this.quantidade = quantidade;
    }

    // getters and setters
    public boolean isAlcoolico() {
        return alcoolico;
    }

    public void setAlcoolico(boolean alcoolico) {
        this.alcoolico = alcoolico;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return nome + ";" + descricao + ";" + estoque + ";" + preco + ";" + alcoolico + ";" + quantidade;
    }

}
