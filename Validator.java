import javax.naming.LimitExceededException;

public class Validator {
    public boolean inputcheck(String v1, String v2){
        if (!(v1.equalsIgnoreCase("A")|| v1.equalsIgnoreCase("B") || v1.equalsIgnoreCase("C"))){
            System.out.println("Hibás bemenet");
            return false;
        }
        for (int i = 0; i < v2.length() ; i++) {
            char c = v2.charAt(i);
            if (!Character.isDigit(c)){
                System.out.println("Hibás bemenet");
                return false;
            }
        }
        try{
            if (Integer.parseInt(v2)<=0){
                System.out.println("Hibás bemenet");
                return false;
            }
        }catch (NumberFormatException e){
            System.out.println("Hibás bemenet");
            return false;
        }
        return true;
    }
    public boolean isNumber(String v1){
        for (int i = 0; i < v1.length(); i++) {
            char c = v1.charAt(i);
            if(!Character.isDigit(c)){
                System.out.println("Hibás bemenet");
                return false;
            }
        }
        try{
            if (Integer.parseInt(v1) <=0){
                System.out.println("Hibás bemenet");
                return false;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean enterAndIsNumber(String v1){
        if(!v1.isBlank()){
            for (int i = 0; i < v1.length(); i++) {
                char c = v1.charAt(i);
                if (!Character.isDigit(c)){
                    System.out.println("Hibás bemenet");
                    return false;
                }
            }
            try{
                if (Integer.parseInt(v1)<=0){
                    System.out.println("Hibás bemenet");
                    return false;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return true;
    }
    public void limitChecker(CashRegister cr, Config config){
        if (cr.getCurrentBalance()>= config.getLimit()){
            throw new LimitExceededException("Limit átlépve: "+cr.getCurrentBalance()+ ">=" + config.getLimit()+" !Esetleges elmardt pénz visszaszolgáltatása a következő autómatánál lehetséges.")
        }
    }
}
