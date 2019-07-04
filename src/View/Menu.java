package View;

import Controller.GerenciadorClientes;
import Controller.GerenciadorProdutos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Menu {

    //Scanner e instâncias 
    Scanner ler = new Scanner(System.in);

    GerenciadorProdutos gP = new GerenciadorProdutos();
    GerenciadorClientes gC = new GerenciadorClientes();

    //atributos e controladores..
    String descPesquisa, codigo;
    private int opcaoMenu;
    boolean verificador = true;

    public void menu() {
        do {
            System.out.println("\t\t## :: DiskBebidas :: ##\n");
            System.out.print("                  ==============================\n");
            System.out.println("                  |     1 - Cadastrar Bebida  |");
            System.out.println("                  |     2 - Cadastrar Cliente |");
            System.out.println("                  |     3 - Listar Bebida     |");
            System.out.println("                  |     4 - Listar Cliente    |");
            System.out.println("                  |     5 - Alterar Bebida    |");
            System.out.println("                  |     6 - Alterar Cliente   |");
            System.out.println("                  |     7 - Remover Bebida    |");
            System.out.println("                  |     8 - Remover Cliente   |");
            System.out.println("                  |     9 - Realizar Venda    |");
            System.out.println("                  |     10 - Salvar Produtos  |");
            System.out.println("                  |     11 - Salvar Clientes  |");
            System.out.println("                  |     0 - Sair              |");
            System.out.print("                  ==============================\n");
            System.out.print("Digite sua Opcao -> ");
            opcaoMenu = ler.nextInt();
            switch (opcaoMenu) {
                case 1:
                    gP.cadastrar();
                    break;
                case 2:
                    gC.cadastrar();
                    break;
                case 3:
                    menuListaProdutos();
                    break;
                case 4:
                    menuListaClientes();
                    break;
                case 5:
                    if (gP.getListaProdutos().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM PRODUTO CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tNome do produto p/ alterar: ");
                        codigo = ler.next();
                        if (gP.alterar(codigo)) {
                            System.out.println("\t\t## :: PRODUTO ALTERADO COM SUCESSO! :: ##\n");
                        } else {
                            System.out.println("\t\t## :: PRODUTO NAO ENCONTRADO! :: ##\n");
                        }
                    }
                    break;
                case 6:
                    if (gC.getListaClientes().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM CLIENTE CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tCPF do cliente p/ alterar: ");
                        codigo = ler.next();
                        if (gC.alterar(codigo)) {
                            System.out.println("\t\t## :: CLIENTE ALTERADO COM SUCESSO! :: ##\n");
                        } else {
                            System.out.println("\t\t## :: CLIENTE NAO ENCONTRADO! :: ##\n");
                        }
                    }
                    break;
                case 7:
                    if (gP.getListaProdutos().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM PRODUTO CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tNome do produto p/ remover: ");
                        codigo = ler.next();
                        if (gP.remover(codigo)) {
                            System.out.println("\t\t## :: PRODUTO REMOVIDO COM SUCESSO! :: ##\n");
                        } else {
                            System.out.println("\t\t## :: PRODUTO NAO ENCONTRADO! :: ##\n");
                        }
                    }
                    break;
                case 8:
                    if (gC.getListaClientes().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM CLIENTE CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tCPF do cliente p/ remover: ");
                        codigo = ler.next();
                        if (gC.remover(codigo)) {
                            System.out.println("\t\t## :: CLIENTE REMOVIDO COM SUCESSO! :: ##\n");
                        } else {
                            System.out.println("\t\t## :: CLIENTE NAO ENCONTRADO! :: ##\n");
                        }
                    }
                    break;
                case 9:
                    System.out.print("\tCPF do cliente p/ venda: ");
                    codigo = ler.next();
                    System.out.print("\tNome do produto p/ venda: ");
                    descPesquisa = ler.next();
                    if (realizarVenda(codigo, descPesquisa)) {
                        System.out.println("\t\t## :: VENDA REALIZADA COM SUCESSO! :: ##\n");
                    } else {
                        System.out.println("\t\t## :: CLIENTE OU PRODUTO NAO ENCONTRADO! :: ##\n");
                    }
                    break;
                case 10:
                    if (gP.salvarEstoque()) {
                        System.out.println("\t\t## :: ARQUIVO SALVO COM SUCESSO! :: ##\n");
                    } else {
                        System.out.println("\t\t## :: ERRO AO SALVAR ARQUIVO! :: ##\n");
                    }
                    break;
                case 11:
                    if (gC.salvarClientes()) {
                        System.out.println("\t\t## :: ARQUIVO SALVO COM SUCESSO! :: ##\n");
                    } else {
                        System.out.println("\t\t## :: ERRO AO SALVAR ARQUIVO! :: ##\n");
                    }
                    break;
                case 0:
                    System.out.println("Saiu...");
                    break;
                default:
                    System.out.println("Voce digitou uma opcao incorreta!!");
            }
        } while (opcaoMenu != 0);

    }

    public void menuListaProdutos() {
        do {
            System.out.println("\t\t## :: EXIBIR PRODUTOS :: ##\n");
            System.out.println("1 - Mostrar todos");
            System.out.println("2 - Consultar por nome");
            System.out.println("0 - Voltar para Menu\n");
            System.out.print("Opcao -> ");
            opcaoMenu = ler.nextInt();
            switch (opcaoMenu) {
                case 1:
                    gP.listar();
                    break;
                case 2:
                    if (gP.getListaProdutos().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM PRODUTO CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tNome para listar: ");
                        descPesquisa = ler.next();
                        gP.consultarPorNome(descPesquisa);
                    }
                    break;
                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Digite uma opcao correta!!");
            }
        } while (opcaoMenu != 0);
    }

    public void menuListaClientes() {
        do {
            System.out.println("\t\t## :: EXIBIR CLIENTES :: ##\n");
            System.out.println("1 - Mostrar todos");
            System.out.println("2 - Consultar por nome");
            System.out.println("0 - Voltar para Menu\n");
            System.out.print("Opcao -> ");
            opcaoMenu = ler.nextInt();
            switch (opcaoMenu) {
                case 1:
                    gC.listar();
                    break;
                case 2:
                    if (gC.getListaClientes().isEmpty()) {
                        System.out.println("\t\t## :: NENHUM CLIENTE CADASTRADO! :: ##\n");
                        break;
                    } else {
                        System.out.print("\tNome para listar: ");
                        descPesquisa = ler.next();
                        gC.consultarPorNome(descPesquisa);
                    }
                    break;
                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Digite uma opcao correta!!");
            }
        } while (opcaoMenu != 0);
    }

    public boolean realizarVenda(String cpfCliente, String produtoNome) {
        boolean achou = false;
        if (gP.getListaProdutos().isEmpty() || gC.getListaClientes().isEmpty()) {
            System.out.println("Nao ha produto nem cliente cadastrado!");
            return achou;
        }

        int indiceCliente, indiceProduto = -1, estoque;

        System.out.println("\t\t## :: VENDA PRODUTO: [" + produtoNome + "] :: ##\n");
        System.out.println("\t\t## :: VENDA CLIENTE CPF: [" + cpfCliente + "] :: ##\n");

        for (int i = 0; i < gC.getListaClientes().size(); i++) {
            if (cpfCliente.equals(gC.getListaClientes().get(i).getCpf())) {
                indiceCliente = i;
            }
        }

        for (int j = 0; j < gP.getListaProdutos().size(); j++) {
            if (gP.getListaProdutos().get(j).getNome().startsWith(produtoNome)) {
                indiceProduto = j;
            }
        }

        for (int j = 0; j < gP.getListaProdutos().size(); j++) {
            if (produtoNome.equals(gP.getListaProdutos().get(j).getNome())) {
                estoque = gP.getListaProdutos().get(indiceProduto).getEstoque();
                estoque--;
                gP.getListaProdutos().get(j).setEstoque(estoque);
                achou = true;
            }
        }

        return achou;
    }

    public boolean salvarEstoque() {
        String caminho = "src/Data/produtos.txt";
        if (gP.getListaProdutos().isEmpty()) {
            System.out.println("Estoque vazio!");
            return false;
        }
        
        try {
            FileWriter fw = new FileWriter(caminho);
            Writer output = new BufferedWriter(fw);
            output.write(gP.getListaProdutos().toString() + "\n");
            output.close();
            return true;
        } catch (IOException ex) {
            System.err.println(ex);
            return false;
        }
    }

    public boolean salvarClientes() {
        String caminho = "src/Data/clientesSalvar.txt";
        if (gC.getListaClientes().isEmpty()) {
            System.out.println("Nenhum Cliente cadastrado!");
            return false;
        }
        try {
            FileWriter fw = new FileWriter(caminho);
            Writer output = new BufferedWriter(fw);
            output.write(gC.getListaClientes().toString() + "\n");
            output.close();
            return true;
        } catch (IOException ex) {
            System.err.println(ex);
            return false;
        }
    }

    public int getOpcaoMenu() {
        return opcaoMenu;
    }

    public void setOpcaoMenu(int opcaoMenu) {
        this.opcaoMenu = opcaoMenu;
    }

}
