import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class BigRecLister {
    public static void main(String[] args) {
        List<Rectangle> rects = new ArrayList<>();

        rects.add(new Rectangle(1, 1, 1, 1));   // perimeter 4
        rects.add(new Rectangle(0, 0, 2, 2));   // perimeter 8
        rects.add(new Rectangle(0, 0, 3, 3));   // perimeter 12  ✓
        rects.add(new Rectangle(0, 0, 4, 1));   // perimeter 10  (not > 10)
        rects.add(new Rectangle(0, 0, 5, 1));   // perimeter 12  ✓
        rects.add(new Rectangle(0, 0, 2, 4));   // perimeter 12  ✓
        rects.add(new Rectangle(0, 0, 6, 0));   // perimeter 12  ✓ (height 0)
        rects.add(new Rectangle(0, 0, 10, 1));  // perimeter 22  ✓
        rects.add(new Rectangle(0, 0, 1, 5));   // perimeter 12  ✓
        rects.add(new Rectangle(0, 0, 2, 3));   // perimeter 10  (not > 10)

        Filter filter = new BigRectangleFilter();

        System.out.println("Rectangles with perimeter > 10:");
        for (Rectangle r : rects) {
            long per = 2L * (Math.round(r.getWidth()) + Math.round(r.getHeight()));
            if (filter.accept(r)) {
                System.out.println(String.format("Rectangle[w=%d, h=%d] perimeter=%d",
                        Math.round(r.getWidth()),
                        Math.round(r.getHeight()),
                        per));
            }
        }
    }
}

