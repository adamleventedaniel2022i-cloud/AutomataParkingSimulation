import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    config();
    CashRegister cr = new CashRegister();
    cr.getDenoms();

    }

    public static void config()  {
        File file = new File("balance.txt");
        try {
            FileWriter fw = new FileWriter("balance.txt");
            fw.write("BALANCE=50000\nDENOM_2000_10\nDENOM_1000_10\nDENOM_500_20\nDENOM_200_40\nDENOM_100_100 ");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
