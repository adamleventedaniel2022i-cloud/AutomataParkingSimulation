public class Transaction {

    private String zone;
    private int min;
    private int fee;
    private int change;
    private Config config;
    public Transaction(String zone, int min, Config config) {
        setZone(zone);
        setMin(min);
        setFee(calculateFee());
        setConfig(config);
    }


    public void setConfig(Config config) {
        this.config = config;
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
        int hours = getMin() / 60;
        switch (getZone()) {
            case "A":
                int baseFeeA = 300 * hours;
                if (getMin() > 180) {
                    int overtimeMinA = getMin() - 180;
                    fee = baseFeeA + ((overtimeMinA / 2) * config.getOverstep()) / 100;
                } else {
                    fee = baseFeeA;
                }
                return fee;
            case "B":
                int baseFeeB = 200 * hours;
                if (getMin() > 360) {
                    int overtimeMinB = getMin() - 360;
                    fee = baseFeeB + ((overtimeMinB / 2) * config.getOverstep()) / 100;
                } else {
                    fee = baseFeeB;
                }
                return fee;
            case "C":
                int baseFeeC = 150 * hours;
                if (getMin() > 720) {
                    int overtimeMinC = getMin() - 720;
                    fee = baseFeeC + ((overtimeMinC / 2) * config.getOverstep()) / 100;
                } else {
                    fee = baseFeeC;
                }
                return fee;
            default:
                throw new RuntimeException("Hiba a program nem talált ilyen zónát");
        }
    }


}
