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
}
