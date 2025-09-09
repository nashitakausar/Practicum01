public class ObjInputTest {
    public static void main(String[] args) {
        SafeInputObj in = new SafeInputObj();
        String s = in.getNonZeroLenString("Type a non-empty string");
        int year = in.getRangedInt("Type a YOB", 1940, 2010);
        double price = in.getDouble("Type any double");
        boolean ok = in.getYNConfirm("Is this fine?");
        String code = in.getRegExString("Enter 6 digits", "\\d{6}");

        System.out.println("String: " + s);
        System.out.println("YOB: " + year);
        System.out.println("Price: " + price);
        System.out.println("OK?: " + ok);
        System.out.println("Code: " + code);
    }
}