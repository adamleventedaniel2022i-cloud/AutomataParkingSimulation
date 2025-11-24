import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    config();
    CashRegister cr = new CashRegister();
    cr.getDenoms();


        System.out.println("***********");
        System.out.println("   Hello");
        System.out.println("***********");

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
}
