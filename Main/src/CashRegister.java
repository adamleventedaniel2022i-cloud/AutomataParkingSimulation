import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CashRegister {
    private ArrayList<ArrayList<Integer>> denoms;


    public ArrayList<ArrayList<Integer>> getAutomataDenoms() {
        try ( BufferedReader br = new BufferedReader(new FileReader("balance.txt"));){
            String line;
            ArrayList<ArrayList<Integer>> denoms = new ArrayList<>();
            br.readLine();
            while((line = br.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line,"=");
                String denom = st.nextToken();

                StringTokenizer finalSt = new StringTokenizer(denom, "_");
                ArrayList<Integer> dataPairs = new ArrayList<>();
                finalSt.nextToken();
                dataPairs.add(Integer.parseInt(finalSt.nextToken()));
                dataPairs.add(Integer.parseInt(st.nextToken()));
                denoms.add(dataPairs);
            }
            return denoms;


            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


