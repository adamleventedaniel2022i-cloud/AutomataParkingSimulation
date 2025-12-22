import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CashRegister {
    private final ArrayList<ArrayList<Integer>> denoms = new ArrayList<>();
    public int getCurrentBalance(){
        int currentBalance=0;
        for (int i = 0; i < getDenoms().size(); i++) {
            currentBalance += getDenoms().get(i).get(0)*getDenoms().get(i).get(1);
        }
        return currentBalance;
    }
    public ArrayList<ArrayList<Integer>> getDenoms(){
        return denoms;
    }

    public void getAutomataDenoms() throws IOException {
        getDenoms().clear();
        try(BufferedReader br = new BufferedReader(new FileReader("balance.txt"))){
            String line;
            br.readLine();
            while((line = br.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line,"=");
                String denom = st.nextToken();

                StringTokenizer finalSt = new StringTokenizer(denom,"_");
                ArrayList<Integer> dataPairs = new ArrayList<>();
                finalSt.nextToken();
                dataPairs.add(Integer.parseInt(finalSt.nextToken()));
                dataPairs.add(Integer.parseInt(st.nextToken()));
                getDenoms().add(dataPairs);
            }
        }catch (IOException e){
            throw new IOException("Fájlba írás hiba transactions-ba "+e.getMessage());
        }
    }
}
