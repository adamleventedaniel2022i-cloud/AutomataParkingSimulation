package core;
import Exceptions.InvalidZoneException;
import config.Config;

public class Transaction {
    private String zone;
    private String min;
    private int fee;
    private int change;
    private Config config;

    public Transaction(String zone, String min, Config config) {
        setConfig(config);
        setZone(zone);
        setMin(min);
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
    }

    public int getMin() {
        return Integer.parseInt(this.min);
    }

    public void setMin(String min) {
        this.min = min;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int calculateFee() {
        if (getZone() == null || getZone().trim().isEmpty()) {
            throw new InvalidZoneException("Zóna nincs megadva!");
        }
        int hours = getMin() / 60;
        String upperZone = getZone().toUpperCase();
        switch (upperZone) {
            case "A":
                int baseFeeA = 300 * hours;
                if (getMin() > 180) {
                    int overtimeMinA = getMin() - 180;
                    int buntetes = (baseFeeA + (config.getOverstep()*overtimeMinA) / 100)  ;
                    return baseFeeA + buntetes;
                }
                return baseFeeA;
            case "B":
                int baseFeeB = 200 * hours;
                if (getMin() > 360) {
                    int overtimeMinB = getMin() - 360;
                    int buntetes = (baseFeeB + (config.getOverstep()*overtimeMinB) / 100)  ;
                    return baseFeeB + buntetes;
                }
                return baseFeeB;
            case "C":
                int baseFeeC = 150 * hours;
                if (getMin() > 720) {
                    int overtimeMinC = getMin() - 720;
                    int buntetes = (baseFeeC + (config.getOverstep()*overtimeMinC) / 100)  ;
                    return baseFeeC + buntetes;
                }
                return baseFeeC;
            default:
                throw new InvalidZoneException("Hiba: érvénytelen zóna '" + getZone() + "'");
        }
    }


}
