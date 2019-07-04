package Controller;

import Model.Bebida;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Model.ICrud;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;

public class GerenciadorProdutos implements ICrud {

    Scanner input = new Scanner(System.in).useLocale(Locale.US);

    private ArrayList<Bebida> listaProdutos = new ArrayList<>();

    public GerenciadorProdutos() {
        importarProdutos(); // ler do arquivo
    }

    @Override
    public boolean cadastrar() {
        try {
            System.out.print("\t\t\t## CADASTRAR ##\n");
            System.out.print("-> Nome: ");
            String nome = input.nextLine();
            System.out.print("-> Descricao: ");
            String descricao = input.nextLine();
            System.out.print("-> Quantidade: ");
            int quantidade = input.nextInt();
            System.out.print("-> Preco (R$): ");
            double preco = input.nextDouble();
            System.out.print("-> Alcoolico (Digite true p/ alcoolico | Digite false p/ nao alcoolico): ");
            boolean alcoolico = input.nextBoolean();
            input.nextLine();
            System.out.print("-> Volume (mL, L): ");
            String volume = input.nextLine();

            listaProdutos.add(new Bebida(quantidade, preco, nome, descricao, alcoolico, volume));

        } catch (ArrayIndexOutOfBoundsException | ArithmeticException | NullPointerException | ClassCastException e2) {
            System.err.print("\nErro: " + e2);
            return false;
        }

        System.out.println("\t\t## :: PRODUTO CADASTRADO COM SUCESSO! :: ##\n");
        return true;
    }

    @Override
    public boolean alterar(String nome) {
        boolean achou = false;

        for (int i = 0; i < listaProdutos.size(); i++) {
            if (nome.equals(listaProdutos.get(i).getNome())) {
                achou = true;
                System.out.println("\t\t## :: ALTERAR PRODUTO: [" + nome + "] :: ##\n");

                listaProdutos.get(i).setId(listaProdutos.get(i).getId());
                listaProdutos.get(i).setEstoque(listaProdutos.get(i).getEstoque());
                System.out.println("-> Nome: ");
                listaProdutos.get(i).setNome(input.nextLine());

                System.out.println("-> Descricao: ");
                listaProdutos.get(i).setNome(input.nextLine());

                System.out.println("-> Volume: ");
                listaProdutos.get(i).setQuantidade(input.nextLine());

                System.out.println("-> Preco (R$): ");
                listaProdutos.get(i).setPreco(input.nextDouble());

                System.out.print("-> Alcoolico (Digite true p/ alcoolico | Digite false p/ nao alcoolico): ");
                listaProdutos.get(i).setAlcoolico(input.nextBoolean());
                input.nextLine();

            }
        }

        return achou;
    }

    @Override
    public boolean remover(String nome) {
        boolean achou = false;
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (nome.equals(listaProdutos.get(i).getNome())) {
                achou = true;
                listaProdutos.remove(i);
            }
        }
        return achou;
    }

    @Override
    public void consultarPorNome(String nome) {
        System.out.println("\t\t## :: CONSULTA PRODUTO POR NOME: [" + nome + "] :: ##\n");

        for (int j = 0; j < listaProdutos.size(); j++) {
            if (listaProdutos.get(j).getNome().startsWith(nome)) {
                System.out.print("\n-> Codigo: " + listaProdutos.get(j).getId());
                System.out.print("\n-> Nome: " + listaProdutos.get(j).getNome());
                System.out.print("\n-> Descricao: " + listaProdutos.get(j).getDescricao());
                System.out.print("\n-> Volume: " + listaProdutos.get(j).getQuantidade());
                System.out.print("\n-> Alcoolico: " + String.valueOf(listaProdutos.get(j).isAlcoolico() ? "Sim" : "Nao"));
                System.out.print("\n-> Estoque: " + listaProdutos.get(j).getEstoque());
                System.out.print("\n-> Preco (R$): " + listaProdutos.get(j).getPreco());
                System.out.print("\n");
            }
        }
    }

    @Override
    public void listar() {
        if (listaProdutos.isEmpty()) {
            System.out.println("\t\t## :: NENHUM PRODUTO CADASTRADO! :: ##\n");
            return;
        }

        System.out.println("\t\t## :: LISTAR TODOS OS PRODUTOS :: ##\n");

        for (int j = 0; j < listaProdutos.size(); j++) {
            System.out.print("\t\t\t ## PRODUTO N. [" + listaProdutos.get(j).getId() + "] ##\n");
            System.out.print("\n-> Codigo: " + listaProdutos.get(j).getId());
            System.out.print("\n-> Nome: " + listaProdutos.get(j).getNome());
            System.out.print("\n-> Descricao: " + listaProdutos.get(j).getDescricao());
            System.out.print("\n-> Volume: " + listaProdutos.get(j).getQuantidade());
            System.out.print("\n-> Alcoolico: " + String.valueOf(listaProdutos.get(j).isAlcoolico() ? "Sim" : "Nao"));
            System.out.print("\n-> Estoque: " + listaProdutos.get(j).getEstoque());
            System.out.print("\n-> Preco (R$): " + listaProdutos.get(j).getPreco());
            System.out.print("\n");
        }
    }

    private void importarProdutos() {
        String[] valores;

        try {
            FileReader arq = new FileReader("src/Data/produtos.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {
                valores = linha.split(";");
                listaProdutos.add(new Bebida(Integer.parseInt(valores[2]), Double.parseDouble(valores[3]), valores[0], valores[1], Boolean.parseBoolean(valores[4]), valores[5]));
                linha = lerArq.readLine();
            }

            arq.close();
            lerArq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }

    public boolean salvarEstoque() {
        try {
            FileWriter fw = new FileWriter("src/Data/produtos.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < listaProdutos.size(); i++) {
                pw.printf("%s;", listaProdutos.get(i).getNome());
                pw.printf("%s;", listaProdutos.get(i).getDescricao());
                pw.printf("%d;", listaProdutos.get(i).getEstoque());
                pw.printf(listaProdutos.get(i).getPreco()+";");
                pw.printf("%b;", listaProdutos.get(i).isAlcoolico());
                pw.printf("%s\n", listaProdutos.get(i).getQuantidade());
            }

            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return true;
    }

    public ArrayList<Bebida> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Bebida> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

}
