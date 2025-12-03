public class Transaction {

    private String zone;
    private int min;
    private int fee;
    private int change;

    public Transaction(String zone, int min) {
        this.zone = zone;
        this.min = min;
        this.fee = calculateFee();
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
        this.fee = calculateFee();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        this.fee = calculateFee();
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    private int calculateFee() {
        int hours = min / 60;
        switch (zone) {
            case "A":
                if (min <= 180) {
                    return 300 * hours;
                } else {
                    return 300 * hours * ((min - 180) / 2);
                }
            case "B":
                if (min <= 360) {
                    return 200 * hours;
                } else {
                    return 200 * hours * ((min - 360) / 2);
                }
            case "C":
                if (min <= 720) {
                    return 150 * hours;
                } else {
                    return 150 * hours * ((min - 720) / 2);
                }
            default:
                throw new RuntimeException("Hiba a program nem talált ilyen zónát");
        }
    }


}
