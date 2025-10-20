public class Zona {
    private int ar;
    private int maxOra;
    private int parkolasiIdo;
    public Zona(String zona, int parkolasiIdo) {
        setParkolasiIdo(parkolasiIdo);
        switch (zona) {
            case "A":
                this.ar = 300;
                this.maxOra = 3;
                break;

            case "B":
                this.ar = 200;
                this.maxOra = 6;
                break;

            case "C":
                this.ar = 150;
                this.maxOra = 12;
                break;

            default:
                throw new IllegalArgumentException("Ismeretlen zóna: " + zona);
        }
    }
    public void setParkolasiIdo(int parkolasiIdo){
        this.parkolasiIdo=parkolasiIdo;
    }
    public int getAr() {
        return ar;
    }

    public int getMaxOra() {
        return maxOra;
    }
    public int getParkolasiIdo() {
        return parkolasiIdo;
    }
    public void fizetendo(){
        int fizetendo = (this.getParkolasiIdo()/60)*this.getAr();
        System.out.println("A fizetendő összeg: "+fizetendo);
        while (fizetendo>0){
            System.out.println("Kérem a pénzt (Hátra maradt összeg:"+fizetendo+"):");
            int penz = Integer.parseInt(Main.InputHelper.scanner.nextLine());
            switch (penz){
                case 100:
                    fizetendo-=100;
                    break;
                case 200:
                    fizetendo-=200;
                    break;
                case 500:
                    fizetendo-=500;
                    break;
                case 1000:
                    fizetendo-=1000;
                    break;
                case 2000:
                    fizetendo-=2000;
                    break;
                default:
                    System.out.println("nem megfelelő címlet(100,200,500,1000,2000)");
            }
            if (fizetendo<0){
                int visszajaro =fizetendo+penz;
                System.out.println(visszajaro);
            }

        }
        System.out.println("köszönjük parkolását");
    }
    

}

