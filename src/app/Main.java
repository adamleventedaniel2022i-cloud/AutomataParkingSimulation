package app;

import admin.Admin;
import config.Config;
import core.CashRegister;
import core.ParkingMachine;
import core.Transaction;
import validation.Validator;

import java.io.*;
import java.util.Scanner;
public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Boolean run = true;
    public static void main(String[] args) {
        CashRegister cr = new CashRegister();
        Config config = new Config(50000, 100000, 5000);
        Validator validator = new Validator();
        if (!new File("config.txt").exists()){
            config.writeConfigFile();
        }else {
            config.getConfig();
        }
        config.writefiles();
        String zone;
        String min;
        System.out.println("**********************");
        System.out.println("   Parkolóautomata");
        System.out.println("**********************\n");
        String valasz;
        do {
            do {
                System.out.println("Admin mód-1\nFelhasználói mód-2\nENTER a kilépéshez");
                System.out.print("válassz:");
                valasz = sc.nextLine();
                switch (valasz) {
                    case "1":
                        Admin admin = new Admin(config);
                        admin.run(cr, validator);
                        break;
                    case "2":
                        System.out.println("\n**********************");
                        System.out.println("     Üdvözöljük");
                        System.out.println("**********************");
                        do {
                            System.out.print("\nKérem adja meg a Zónát(A,B,C):");
                            zone = sc.nextLine().strip();
                            System.out.print("Adja meg parkolás időtartamát percben:");
                            min = sc.nextLine().strip();

                        } while (!validator.inputcheck(zone, min, config, new Transaction(zone, min,config),cr));
                        Transaction transaction = new Transaction(zone, min, config);
                        transaction.setFee(transaction.calculateFee());
                        ParkingMachine.transaction(transaction, cr, validator, config);
                        ParkingMachine.Change(transaction, cr);
                        config.writefiles(cr);
                        break;
                    default:
                        if (valasz.isBlank()) {
                            run=false;
                            System.out.println("\nKöszönjük hogy minket választott");

                        }

                }
            }while (!valasz.equals("1") && !valasz.equals("2") && !valasz.isBlank());
        }while (run);
    }
}
