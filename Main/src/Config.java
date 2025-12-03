import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
