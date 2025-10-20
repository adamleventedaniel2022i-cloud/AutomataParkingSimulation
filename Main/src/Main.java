import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public class InputHelper {
        public static final Scanner scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
//        Minden tranzakció:
//        1. Zóna választás (A/B/C) pipa(tick)
//        2. Időtartam (perc) megadás
//        3. Fizetendő számítás
//        4. Befizetés (elfogadott címlettel)
//        5. Visszajáró kiadás
//        6. Készlet frissítés
//        7. Naplózás fájlba
//        8. Ha készlet >= 100 000 → LEÁLLóra = perc / 60
//         fizetendo = (óra * zóna_ár)

//        parkolo_naplo.txt sor:
//        "2025-10-20 14:30:00,A,90,450"
//        dátum,zóna,perc,bevétel




        File file = new File("Aktuális_készlet.txt");
        if (!file.exists()){
            try {
                FileWriter fw = new FileWriter("Aktuális_készlet.txt");
                fw.write(500000+" FT");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Hello, melyik zónában szeretnél parkolni? Válaszadás pl.:\n (A 60 23)");

        String valasz = InputHelper.scanner.nextLine();

        Zona zona = new Zona(valasz.split(" ")[0].strip(), Integer.parseInt(valasz.split(" ")[1].strip()));
        zona.fizetendo();


    }

}
