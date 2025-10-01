import java.awt.Rectangle;

public class BigRectangleFilter implements Filter {
    @Override
    public boolean accept(Object x) {
        if (!(x instanceof Rectangle)) return false;
        Rectangle r = (Rectangle) x;
        // Perimeter = 2 * (width + height)
        long w = r.getWidth() < 0 ? 0 : Math.round(r.getWidth());
        long h = r.getHeight() < 0 ? 0 : Math.round(r.getHeight());
        long perimeter = 2 * (w + h);
        return perimeter > 10;
    }
}

