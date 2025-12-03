import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CashRegister cr = new CashRegister();
        Config config = new Config(50000, 100000, 5000);
        Validator validator = new Validator();
        config.writefiles();
        String zone;
        String min;
        System.out.println("**********************");
        System.out.println("   Parkolóautomata");
        System.out.println("**********************");
        String valasz;
        do {
            System.out.println("Admin mód-1\nFelhasználói mód-2");
            System.out.print("válassz:");
            valasz = sc.nextLine();
            switch (valasz) {
                case "1":
                    Admin admin = new Admin(config);
                    admin.run(new Scanner(System.in), cr);
                    break;
                case "2":
                    do {
                        System.out.println("**********************");
                        System.out.println("     Üdvözöljük");
                        System.out.println("**********************");
                        System.out.print("Kérem adja meg a Zónát(A,B,C):");
                        zone = sc.nextLine().strip();
                        System.out.print("Adja meg parkolás időtartamát percben:");
                        min = sc.nextLine().strip();

                    } while (!validator.inputchek(zone, min));
                    Transaction transaction = new Transaction(zone, Integer.parseInt(min), config);
                    transaction(transaction, cr);
                    Change(transaction, cr);
                    break;
                default:
                    System.out.println("Hibás Bemenet");
            }
        }while (valasz!="1"||valasz!="2");

    }

    public static void transaction(Transaction transaction, CashRegister cr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tranzakció");
        int fizetendo = transaction.getFee();
        System.out.println("Fizetendő: " + fizetendo);
        while (fizetendo > 0) {
            System.out.println("Fizess (pénznemek: 2000, 1000, 500, 200, 100):");
            int money = Integer.parseInt(sc.nextLine().strip());
            switch (money) {
                case 2000:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getAutomataDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getAutomataDenoms().get(i);
                        if (denom.getFirst().equals(2000)) {
                            int lastIndex = denom.size() - 1;
                            denom.set(lastIndex, denom.get(lastIndex) + 1);
                        }
                    }
                    break;
                case 1000:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getAutomataDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getAutomataDenoms().get(i);
                        if (denom.getFirst().equals(1000)) {
                            int lastIndex = denom.size() - 1;
                            denom.set(lastIndex, denom.get(lastIndex) + 1);
                        }
                    }
                    break;
                case 500:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getAutomataDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getAutomataDenoms().get(i);
                        if (denom.getFirst().equals(500)) {
                            int lastIndex = denom.size() - 1;
                            denom.set(lastIndex, denom.get(lastIndex) + 1);
                        }
                    }
                    break;
                case 200:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getAutomataDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getAutomataDenoms().get(i);
                        if (denom.getFirst().equals(200)) {
                            int lastIndex = denom.size() - 1;
                            denom.set(lastIndex, denom.get(lastIndex) + 1);
                        }
                    }
                    break;
                case 100:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getAutomataDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getAutomataDenoms().get(i);
                        if (denom.getFirst().equals(100)) {
                            int lastIndex = denom.size() - 1;
                            denom.set(lastIndex, denom.get(lastIndex) + 1);
                        }
                    }
                    break;
                default:
                    System.out.println("Nem megfelelő pénznem (pénznemek: 2000, 1000, 500, 200, 100)");
            }
            System.out.println("Maradék fizetendő: " + Math.max(fizetendo, 0));
        }
        transaction.setChange(Math.max(-fizetendo, 0));
    }

    public static void Change(Transaction transaction, CashRegister cr) {
        int visszaJaro = transaction.getChange();
        ArrayList<ArrayList<Integer>> denoms = cr.getAutomataDenoms();
        System.out.println("Visszajáró: " + visszaJaro);
        for (int i = 0; i < denoms.size(); i++) {
            ArrayList<Integer> denom = denoms.get(i);
            int cimlet = denom.getFirst();
            int lastIndex = denom.size() - 1;
            int darab = denom.get(lastIndex);
            while (visszaJaro >= cimlet && darab > 0) {
                visszaJaro -= cimlet;
                darab--;
            }
            denom.set(lastIndex, darab);
        }
        if (visszaJaro > 0) {
            System.out.println("A maradék visszajárandó összeg: " + visszaJaro + "\nA legkisebb címlet 100, ezért banki utalással kapja meg a fennmaradó összeget!");
        }
    }


}
