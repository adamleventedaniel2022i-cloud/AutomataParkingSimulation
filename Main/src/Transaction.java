import java.util.Date;

public class Transaction {
    private Date date;
    private String zone;
    private int min;
    private int DENOM;
    private int fee;
    private int deposit;
    private int change;

    public Transaction(Date date, String zone, int min, int DENOM, int fee, int deposit, int change) {
        this.setDate(date);
        this.setZone(zone);
        this.setMin(min);
        this.setDENOM(DENOM);
        this.setFee(fee);
        this.setDeposit(deposit);
        this.setChange(change);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getDENOM() {
        return DENOM;
    }

    public void setDENOM(int DENOM) {
        this.DENOM = DENOM;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }
}
