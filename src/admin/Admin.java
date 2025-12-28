package admin;

import Exceptions.AuthenticationException;
import app.Main;
import config.Config;
import core.CashRegister;
import validation.Validator;

import java.util.ArrayList;

public record Admin(Config config) {
    public void run(CashRegister cr, Validator validator) {
        String jelszo="";
        System.out.print("\nAdmin jelszó vagy ENTER a kilépéshez:");
        jelszo = Main.sc.nextLine().strip();
        do {
            try {
                if (jelszo.isBlank()){
                    return;
                }
                if (!jelszo.equals("admin123")) {
                    throw new AuthenticationException("Hibás jelszó: hozzáférés megtagadva!");
                }
            } catch (AuthenticationException e) {
                System.err.println("\n"+e);
            }
            if (!jelszo.equals("admin123")){
                System.out.print("\nAdmin jelszó vagy ENTER a kilépéshez:");
                jelszo = Main.sc.nextLine().strip();
            }
        }while (!jelszo.equals("admin123"));

        String input;
        int choice;
        System.out.println("Admin mód aktív");
        do {
            do {
                System.out.println("1. Pénz feltöltése");
                System.out.println("2. Config módosítása");
                System.out.println("3. Balance megtekintése");
                System.out.println("4. Kilépés");
                System.out.print("Válasszon opciót:");
                input = Main.sc.nextLine().strip();
            }while (!validator.isNumber(input));
            choice =Integer.parseInt(input);
            switch (choice) {
                case 1:
                    penzFeltoltes(cr, validator);
                    config().writefiles(cr);
                    break;
                case 2:
                    configModositasa(validator,cr);
                    config.writeConfigFile();
                    break;
                case 3:
                    balanceMegtekintese(cr);
                    break;
                case 4:
                    System.out.println("Kilépés admin módból");
                    break;
                default:
                    System.out.println("Nem megfelelő választás!");
            }
        } while (choice != 4);
    }

    private void penzFeltoltes(CashRegister cr, Validator validator) {
        cr.getAutomataDenoms();
        System.out.println("Jelenlegi pénznemek a kasszában:");
        ArrayList<ArrayList<Integer>> penz = cr.getDenoms();
        for (ArrayList<Integer> integers : penz) {
            System.out.println("DENOM_" + integers.get(0) + ": " + integers.get(1) + " db");
        }

        int denom;
        int darabszam;
        String input;
        do {
            System.out.println("\nAdja meg a feltölteni kívánt pénznemet (2000, 1000, 500, 200, 100): ");
            input = Main.sc.nextLine().strip();
        }while (!validator.isNumber(input));
        denom = Integer.parseInt(input);
        input="";
        do {
            System.out.println("Hány darabot tölt fel? ");
            input = Main.sc.nextLine().strip();
        }while (!validator.isLimitExceededWithDenomFlowAndIsNumber(input, denom, config, cr));
        darabszam = Integer.parseInt(input);
        boolean talalt = false;
        for (ArrayList<Integer> pair : cr.getDenoms()) {
            if (pair.get(0) == denom) {
                talalt = true;
                if ((denom*darabszam)+ cr.getCurrentBalance() > config.getLimit()){
                    System.out.println("Sajnos nem lehetséges a feltöltés mivel meghaladná a limitet");
                }else {
                    pair.set(1, pair.get(1) + darabszam);
                    System.out.println("\nFeltöltve OK!");
                }
                break;
            }
        }
        if (!talalt) {
            System.out.println("\nNincs ilyen címlet, nem adunk hozzá újat! (Csak meglévő növelhető)");
        }

    }

    private void configModositasa(Validator validator, CashRegister cashRegister) {
        System.out.println("\nJelenlegi config:");
        System.out.println("Start Balance: " + config.getStartBalance());
        System.out.println("Limit: " + config.getLimit());
        System.out.println("Overstep: " + config.getOverstep());
        String ujStart;
        do {
            System.out.print("\nÚj Start Balance (vagy Enter a megtartáshoz): ");
            ujStart = Main.sc.nextLine().strip();
        } while (!validator.enterAndIsNumber(ujStart));

        if (!ujStart.isBlank()) {
            int start = Integer.parseInt(ujStart);

            if (start > config.getLimit()) {
                System.out.println("Figyelem a startBalance nem lehet több mint a LIMIT" + config.getLimit());
            } else if (isNotMultipleOf100(start)) {
                System.out.println("A startBalance összege nem osztható pontosan a rendelkezésre álló címletekkel, maradék keletkezne!");
            } else {
                config.setStartBalance(start);
            }
        }

        String ujLimit;
        do {
            System.out.print("\nÚj Limit (vagy Enter a megtartáshoz): ");
            ujLimit = Main.sc.nextLine().strip();
        } while (!validator.enterAndIsNumber(ujLimit));

        if (!ujLimit.isBlank()) {
            int limit = Integer.parseInt(ujLimit);

            if (limit < config.getStartBalance()||limit<cashRegister.getCurrentBalance()) {
                System.out.println("Figyelem a limit nem lehet kevesebb mint a STARTBALANCE:"+config.getStartBalance()+" vagy a CURRENTBALANCE:"+ cashRegister.getCurrentBalance());
            } else if (isNotMultipleOf100(limit)) {
                System.out.println("Az új limit nem osztható pontosan a rendelkezésre álló címletekkel.");
                System.out.println("A maradék 50 Ft vizsgálata értelmetlen, ajánlott kerekíteni.");
            } else {
                config.setLimit(limit);
            }
        }

        String ujOver;
        do {
            System.out.print("\nÚj Overstep (vagy Enter a megtartáshoz): ");
            ujOver = Main.sc.nextLine().strip();
        } while (!validator.enterAndIsNumber(ujOver));

        if (!ujOver.isBlank()) {
            config.setOverstep(Integer.parseInt(ujOver));
        }

        System.out.println("\nConfig frissítve!");
    }

    private boolean isNotMultipleOf100(int value) {
        return value % 100 != 0;
    }

    private void balanceMegtekintese(CashRegister cr) {
        cr.getAutomataDenoms();
        ArrayList<ArrayList<Integer>> penz = cr.getDenoms();
        int osszeg = 0;
        for (ArrayList<Integer> integers : penz) {
            osszeg += integers.get(0) * integers.get(1);
        }
        System.out.println("\nTeljes kassza egyenleg: " + osszeg + " Ft");
        System.out.println("Config Start Balance: " + config.getStartBalance());
        System.out.println("Limit: " + config.getLimit());
        System.out.println("Overstep: " + config.getOverstep());
    }
}