package Controller;

import Model.Cliente;
import Model.ICrud;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorClientes implements ICrud {

    Scanner ler = new Scanner(System.in);

    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public GerenciadorClientes() {
        importarPessoas();
    }

    private void importarPessoas() {
        File arquivo = new File("src/Data/clientes.txt");
        String[] valores;

        try {
            FileReader ler = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(ler);
            String linha = br.readLine();

            while (linha != null) {
                //System.out.println(linha + " ");
                valores = linha.split(";");
                listaClientes.add(new Cliente(valores[0], valores[1], valores[2], valores[3], valores[4]));
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public boolean salvarClientes() {
        try {
            FileWriter fw = new FileWriter("src/Data/clientes.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < listaClientes.size(); i++) {
                pw.printf("%s;", listaClientes.get(i).getCpf());
                pw.printf("%s;", listaClientes.get(i).getEmail());
                pw.printf("%s;", listaClientes.get(i).getTelefone());
                pw.printf("%s;", listaClientes.get(i).getNome());
                pw.printf(listaClientes.get(i).getData_nascimento() + "\n");
            }

            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return true;
    }

    @Override
    public boolean cadastrar() {
        try {
            System.out.print("\t\t\t## CADASTRAR ##\n");
            System.out.print("-> Nome: ");
            String nome = ler.nextLine();
            System.out.print("-> CPF: ");
            String cpf = ler.nextLine();
            System.out.print("-> Data Nascimento: ");
            String data_nascimento = ler.nextLine();
            System.out.print("-> E-mail: ");
            String email = ler.nextLine();
            System.out.print("-> Telefone: ");
            String telefone = ler.nextLine();

            listaClientes.add(new Cliente(cpf, email, telefone, nome, data_nascimento));
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException | NullPointerException | ClassCastException e) {
            System.err.println("Erro: " + e);
            return false;
        }
        System.out.println("\t\t## :: CLIENTE CADASTRADO COM SUCESSO! :: ##\n");
        return true;
    }

    @Override
    public boolean alterar(String cpf) {
        boolean achou = false;

        for (int i = 0; i < listaClientes.size(); i++) {
            if (cpf.equals(listaClientes.get(i).getCpf())) {
                achou = true;
                System.out.println("\t\t## :: ALTERAR CLIENTE CPF: [" + cpf + "] :: ##\n");

                listaClientes.get(i).setId(listaClientes.get(i).getId());

                System.out.print("-> Nome: ");
                listaClientes.get(i).setNome(ler.nextLine());

                System.out.print("-> CPF: ");
                listaClientes.get(i).setCpf(ler.nextLine());

                System.out.print("-> Data Nascimento: ");
                listaClientes.get(i).setData_nascimento(ler.nextLine());

                System.out.print("-> E-mail: ");
                listaClientes.get(i).setEmail(ler.nextLine());

                System.out.print("-> Telefone: ");
                listaClientes.get(i).setTelefone(ler.nextLine());

            }
        }

        return achou;
    }

    @Override
    public boolean remover(String cpf) {
        boolean achou = false;
        for (int i = 0; i < listaClientes.size(); i++) {
            if (cpf.equals(listaClientes.get(i).getCpf())) {
                achou = true;
                listaClientes.remove(i);
            }
        }
        return achou;
    }

    @Override
    public void consultarPorNome(String nome) {
        System.out.println("\t\t## :: CONSULTA CLIENTE POR NOME: [" + nome + "] :: ##\n");

        for (int j = 0; j < listaClientes.size(); j++) {
            if (listaClientes.get(j).getNome().startsWith(nome)) {
                System.out.print("\n-> Codigo: " + listaClientes.get(j).getId());
                System.out.print("\n-> Nome: " + listaClientes.get(j).getNome());
                System.out.print("\n-> CPF: " + listaClientes.get(j).getCpf());
                System.out.print("\n-> Data Nascimento: " + listaClientes.get(j).getData_nascimento());
                System.out.print("\n-> E-mail: " + listaClientes.get(j).getEmail());
                System.out.print("\n-> Telefone: " + listaClientes.get(j).getTelefone());
                System.out.print("\n");
            }
        }
    }

    @Override
    public void listar() {
        if (listaClientes.isEmpty()) {
            System.out.println("\t\t## :: NENHUM CLIENTE CADASTRADO! :: ##\n");
            return;
        }

        System.out.println("\t\t## :: LISTAR TODOS OS CLIENTES :: ##\n");

        for (int j = 0; j < listaClientes.size(); j++) {
            System.out.print("\t\t\t ## CLIENTE N. [" + listaClientes.get(j).getId() + "] ##\n");
            System.out.print("\n-> Codigo: " + listaClientes.get(j).getId());
            System.out.print("\n-> Nome: " + listaClientes.get(j).getNome());
            System.out.print("\n-> CPF: " + listaClientes.get(j).getCpf());
            System.out.print("\n-> Data Nascimento: " + listaClientes.get(j).getData_nascimento());
            System.out.print("\n-> E-mail: " + listaClientes.get(j).getEmail());
            System.out.print("\n-> Telefone: " + listaClientes.get(j).getTelefone());
            System.out.print("\n");
        }
    }

    // getters e setters
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
