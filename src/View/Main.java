package View;

import java.util.Scanner;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        //Scanner e instâncias    
        Scanner ler = new Scanner(System.in);
        Menu m = new Menu();
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();

        //implementações de Data e seu formato
        Locale Brasil = new Locale("pt", "BR"); //Retorna do país e a língua
        DateFormat f1 = DateFormat.getDateInstance(DateFormat.FULL, Brasil);
        //atributos e controladores..
        int Opcao;
        boolean verificador = false;

        try {
            do {
                System.out.println("\t\t\tBem vindo(a) ao DiskBebidas do Joaozinho\n");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("\t\tHora do sistema: " + f1.format(data) + " " + dtf.format(now) + "\n");
                System.out.println("1 - Entrar no Sistema");
                System.out.println("2 - Conheca os Desenvolvedores");
                System.out.println("0 - Sair");
                System.out.print("Opcao -> ");
                Opcao = ler.nextInt();
                ler.nextLine();
                switch (Opcao) {
                    case 0:
                        System.out.println("Você saiu...");
                        verificador = true;
                        break;
                    case 1:
                        m.menu();
                        verificador = false;
                        break;

                    case 2:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("\t\t-> Igor Alves (3902).\n\n");
                        System.out.print("\t      >> DISK BEBIDAS DO JOAOZINHO <<\n");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                        verificador = false;
                        break;

                    default:
                        System.out.println("Voce digitou uma opcao incorreta!!\n");
                }
            } while (verificador == false);

        } catch (InputMismatchException | IndexOutOfBoundsException | ArithmeticException e1) {
            System.err.println("Erro " + e1);
        }
    }
}
