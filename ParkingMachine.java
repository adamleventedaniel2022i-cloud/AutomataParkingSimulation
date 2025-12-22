package core;

import app.Main;
import config.Config;
import validation.Validator;

import java.util.ArrayList;

public class ParkingMachine {
    public static void transaction(Transaction transaction, CashRegister cr, Validator validator, Config config) {
        cr.getAutomataDenoms();
        System.out.println("\nTranzakció");
        int fizetendo = transaction.getFee();
        System.out.println("Fizetendő: " + fizetendo);
        while (fizetendo > 0) {
            String input;
            do {
                System.out.println("\nFizess (pénznemek: 2000, 1000, 500, 200, 100):");
                input = Main.sc.nextLine().strip();
            }while (!validator.isNumber(input));
            int money = Integer.parseInt(input);
            switch (money) {
                case 2000:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getDenoms().get(i);
                        if (denom.get(0).equals(2000)) {
                            denom.set(1, denom.get(1) + 1);
                        }
                    }
                    break;
                case 1000:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getDenoms().get(i);
                        if (denom.get(0).equals(1000)) {
                            denom.set(1, denom.get(1) + 1);
                        }
                    }
                    break;
                case 500:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getDenoms().get(i);
                        if (denom.get(0).equals(500)) {
                            denom.set(1, denom.get(1) + 1);
                        }
                    }
                    break;
                case 200:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getDenoms().get(i);
                        if (denom.get(0).equals(200)) {
                            denom.set(1, denom.get(1) + 1);
                        }
                    }
                    break;
                case 100:
                    fizetendo -= money;
                    for (int i = 0; i < cr.getDenoms().size(); i++) {
                        ArrayList<Integer> denom = cr.getDenoms().get(i);
                        if (denom.get(0).equals(100)) {
                            denom.set(1, denom.get(1) + 1);
                        }
                    }
                    break;
                default:
                    System.out.println("Nem megfelelő pénznem (pénznemek: 2000, 1000, 500, 200, 100)");
            }
            validator.limitChecker(cr, config);
            System.out.println("\nMaradék fizetendő: " + Math.max(fizetendo, 0));
        }
        config.logTransaction(transaction.getZone(),transaction.getMin(),transaction.getFee());
        transaction.setChange(Math.max(-fizetendo, 0));
    }
    public static void Change(Transaction transaction, CashRegister cr) {
        int visszaJaro = transaction.getChange();
        ArrayList<ArrayList<Integer>> denoms = cr.getDenoms();
        denoms.sort((a, b) -> a.get(0)-b.get(0));
        System.out.println("\nVisszajáró: " + visszaJaro);
        System.out.println("kiadás folyamatban...");
        for (int i = 0; i < denoms.size(); i++) {
            ArrayList<Integer> denom = denoms.get(i);
            int cimlet = denom.get(0);
            int darab = denom.get(1);
            while (visszaJaro >= cimlet && darab > 0) {
                visszaJaro -= cimlet;
                System.out.println(cimlet+"...");
                darab--;
            }
            denom.set(1, darab);
        }
        denoms.sort((a, b) -> b.get(0) - a.get(0));
        if (visszaJaro > 0) {
            System.out.println("A maradék visszajárandó összeg: " + visszaJaro + "\nIlyen címletünk nincsen, ezért banki utalással kapja meg a fennmaradó összeget!");
        }
        System.out.println("Köszönjük Befizetését\n");
    }
}