package config;

import Exceptions.FileOperationException;
import core.CashRegister;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class Config {
    private int startBalance;
    private int limit;
    private int overstep;

    public Config(int startBalance, int limit, int overstep) {
        this.setStartBalance(startBalance);
        this.setLimit(limit);
        this.setOverstep(overstep);
    }

    public int getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(int startBalance) {
        this.startBalance = startBalance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOverstep() {
        return overstep;
    }

    public void setOverstep(int overstep) {
        this.overstep = overstep;
    }

    public void writefiles() {
        try {
            FileWriter fw = new FileWriter("balance.txt", false);
            int segedBalance = getStartBalance();
            int perc2000 = 30;
            int perc1000 = 20;
            int perc500 = 20;
            int perc200 = 20;
            int perc100 = 10;
            int denom2000 = (segedBalance * perc2000) / 100 / 2000;
            int denom1000 = (segedBalance * perc1000) / 100 / 1000;
            int denom500  = (segedBalance * perc500)  / 100 / 500;
            int denom200  = (segedBalance * perc200)  / 100 / 200;
            int denom100  = (segedBalance * perc100)  / 100 / 100;
            int allocated = denom2000*2000 + denom1000*1000 + denom500*500 + denom200*200 + denom100*100;
            int remainder = segedBalance - allocated;
            denom100 += remainder / 100;
            fw.write("BALANCE="+getStartBalance()+"\nDENOM_2000=" + denom2000 + "\nDENOM_1000=" + denom1000 + "\nDENOM_500="  + denom500  + "\nDENOM_200="  + denom200  + "\nDENOM_100="  + denom100  + "\n");
            fw.close();
        } catch (IOException e) {
            throw new FileOperationException("Fájl írás hiba transactions-ba: " + e.getMessage());
        }


    }
    public void writefiles(CashRegister cr) {
        try {
            FileWriter fw = new FileWriter("balance.txt", false);
            fw.write("BALANCE="+cr.getCurrentBalance());
            for (int i = 0; i < cr.getDenoms().size(); i++) {
                fw.write("\nDENOM_"+cr.getDenoms().get(i).get(0)+"="+cr.getDenoms().get(i).get(1));
            }
            fw.close();
        } catch (IOException e) {
            throw new FileOperationException("Fájl írás hiba transactions-ba: " + e.getMessage());
        }
    }
    public void writeConfigFile(){

        try {
            FileWriter fw = new FileWriter("config.txt");
            fw.write("START=" + getStartBalance() + "\n");
            fw.write("LIMIT=" + getLimit() + "\n");
            fw.write("OVERSTEP=" + getOverstep() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new FileOperationException("Fájl írás hiba transactions-ba: " + e.getMessage());
        }
    }
    public void getConfig(){
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"));) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "=");
                String name = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                switch (name){
                    case "START":
                        setStartBalance(value);
                        break;
                    case "LIMIT":
                        setLimit(value);
                        break;
                    case "OVERSTEP":
                        setOverstep(value);
                        break;
                    default:
                        System.out.println("Ismeretlen kulcs: " + name + ", kihagyva.");
                }
            }
        } catch (IOException e) {
            throw new FileOperationException("Fájl írás hiba transactions-ba: " + e.getMessage());
        }
    }
    public void logTransaction(String zone, int min, int income){
        File file = new File("transactions.txt");
        try {
            FileWriter fw = new FileWriter(file, true);
            Date date = new Date();
            fw.write(date.toString()+";"+zone+";"+min +";"+income+"\n");
            fw.close();
        } catch (IOException e) {
            throw new FileOperationException("Fájl írás hiba transactions-ba: " + e.getMessage());
        }

    }
}
