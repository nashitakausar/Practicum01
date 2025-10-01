public class ShortWordFilter implements Filter {
    @Override
    public boolean accept(Object x) {
        if (!(x instanceof String)) return false;
        String s = ((String) x).trim();
        return s.length() < 5;
    }
}
