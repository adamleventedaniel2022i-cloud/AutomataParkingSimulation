package validation;

import Exceptions.LimitExceededException;
import config.Config;
import core.CashRegister;
import core.Transaction;

public class Validator {
    public boolean inputcheck(String v1, String v2, Config config, Transaction transactionTEST) {
        if (!(v1.equalsIgnoreCase("A") || v1.equalsIgnoreCase("B") || v1.equalsIgnoreCase("C"))) {
            System.out.println("Hibás Bemenet");
            return false;
        }

        for (int i = 0; i < v2.length(); i++) {
            char c = v2.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        }
        try {
            if ( transactionTEST.calculateFee()  >= config.getLimit()) {
                System.out.println("Díj túl magas: " + v2 + " > limit " + config.getLimit());
                return false;
            }
            if (Integer.parseInt(v2) <= 0){
                System.out.println("A perc nem lehet (0), vagy kevesebb");
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás Bemenet");
            return false;
        }
        return true;
    }
    public boolean isNumber(String v1){
        for (int i = 0; i < v1.length(); i++) {
            char c = v1.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        }
        try {
            if (Integer.parseInt(v1) <= 0) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás Bemenet");
            return false;
        }
        return true;
    }
    public boolean isLimitExceededWithDenomFlowAndIsNumber(String v1, int denom, Config config){
        for (int i = 0; i < v1.length(); i++) {
            char c = v1.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        }
        if (Integer.parseInt(v1)*denom >= config.getLimit()){
            System.out.println("A bemenet meghaladná a Limitet: "+config.getLimit());
            return false;
        }
        try {
            if (Integer.parseInt(v1) <= 0) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás Bemenet");
            return false;
        }
        return true;
    }
    public boolean enterAndIsNumber(String v1){
        if (!v1.isBlank()){
            for (int i = 0; i < v1.length(); i++) {
                char c = v1.charAt(i);
                if (!Character.isDigit(c)) {
                    System.out.println("Hibás Bemenet");
                    return false;
                }
            }
            try {
                if (Integer.parseInt(v1) <= 0) {
                    System.out.println("Hibás Bemenet");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Hibás Bemenet");
                return false;
            }
            return true;
        }
        return true;
    }
    public void limitChecker(CashRegister cr, Config config) {
        if (cr.getCurrentBalance() >= config.getLimit()) {
            throw new LimitExceededException("Limit átlépve: " + cr.getCurrentBalance() + " >= " + config.getLimit() + "! Esetleges elmaradt pénz visszaszolgáltatása a következő automatánál lehetséges. Leállás!");
        }
    }
}
