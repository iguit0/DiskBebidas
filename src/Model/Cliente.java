package Model;

public class Cliente extends Pessoa {

    private String cpf, email;

    public Cliente(String cpf, String email, String telefone, String nome, String data_nascimento) {
        super(telefone, nome, data_nascimento);
        this.cpf = cpf;
        this.email = email;
    }

    // getters e setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return cpf + ";" + email + ";" + telefone + ";" + nome + ";" + data_nascimento;
    }

}
