import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc =new Scanner(System.in);
    CashRegister cr = new CashRegister();

    config();
        System.out.println("**********************");
        System.out.println("   Parkolóautomata");
        System.out.println("**********************");
        System.out.print("Kérem adja meg a Zónát:");
        String zone = sc.nextLine().strip();
        System.out.print("Adja meg parkolás időtartamát percben:");
        int min = Integer.parseInt(sc.nextLine().strip());
        Transaction transaction = new Transaction(zone, min);
        transaction(transaction);
        Change(transaction,cr);

    }

    public static void config()  {
        File file = new File("balance.txt");
        try {
            FileWriter fw = new FileWriter("balance.txt");
            fw.write("BALANCE=50000\nDENOM_2000=10\nDENOM_1000=10\nDENOM_500=20\nDENOM_200=40\nDENOM_100=100");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void transaction(Transaction transaction){
        Scanner sc = new Scanner(System.in);
        System.out.println("Tranzakció");
        int fizetendo = transaction.getFee();
        System.out.println(fizetendo);
        while (fizetendo>0){
            System.out.println("Fizess (pénznemek: 2000, 1000, 500, 200, 100):");
            int money = Integer.parseInt(sc.nextLine().strip());
            switch (money) {
                case 2000:
                    fizetendo-=money;
                    break;
                case 1000:
                    fizetendo-=money;
                    break;
                case 500:
                    fizetendo-=money;
                    break;
                case 200:
                    fizetendo-=money;
                    break;
                case 100:
                    fizetendo-=money;
                    break;
                default:
                    System.out.println("Nem megfelelő pénznem (pénznemek: 2000, 1000, 500, 200, 100)");
            }
        }
        if (fizetendo<0){
            transaction.setChange(fizetendo*(-1));
        } else {
            transaction.setChange(0);
        }
    }

    public static void Change(Transaction transaction, CashRegister cashRegister ){
        int visszaJaro =transaction.getChange();
        ArrayList<ArrayList<Integer>> penz = cashRegister.getDenoms();
        System.out.println(visszaJaro);
        for (int i = 0; i < penz.size(); i++) {
            if(penz.get(i).get(0)< visszaJaro && penz.get(i).get(1) > 0){
                visszaJaro -= penz.get(i).get(0);
                System.out.println(visszaJaro);
            }
            if (visszaJaro<100){
                System.out.println("A maradák visszajárandó összeg: "+visszaJaro+"\n A legkisebb cimlet 100, ezért banki utálassal kapja mega fennmaradó összeget!");

            }
        }

    }
}
