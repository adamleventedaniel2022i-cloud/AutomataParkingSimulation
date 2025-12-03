public class Validator {
    public boolean inputchek(String v1, String v2) {
        for (int i = 0; i < v2.length(); i++) {
            char c = v2.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Hibás Bemenet");
                return false;
            }
        }
        if (Integer.parseInt(v2) <= 0) {
            System.out.println("Hibás Bemenet");
            return false;
        }
        if (!(v1.equalsIgnoreCase("A") || v1.equalsIgnoreCase("B") || v1.equalsIgnoreCase("C"))) {
            System.out.println("Hibás Bemenet");
            return false;

        }
        return true;
    }
}
