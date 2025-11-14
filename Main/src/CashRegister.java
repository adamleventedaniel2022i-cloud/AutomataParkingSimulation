import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CashRegister {
    private ArrayList<ArrayList<Integer>> denoms;


    public ArrayList<ArrayList<Integer>> getDenoms() {
        try ( BufferedReader br = new BufferedReader(new FileReader("balance.txt"));){
            String line;
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            while((line = br.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line,"_");
                System.out.println(line);
            }


            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}


