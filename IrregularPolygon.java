import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool


public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        if (myPolygon.size() < 2) return 0.0;

        double sum = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double start = myPolygon.get(i);
            Point2D.Double end = myPolygon.get((i + 1) % myPolygon.size());
            sum += start.distance(end);
        }
        return sum;
    }

    public double area() {
        int n = myPolygon.size();
        if (n < 3) return 0.0;
    
        double area = 0.0;
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n);
            area += p1.x * p2.y - p2.x * p1.y;
        }
        return Math.abs(area / 2.0);
    }

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
        if (myPolygon.size() > 0) {
            Point2D.Double first = myPolygon.get(0);
            pen.up();
            pen.move(first.x, first.y);
            pen.down();

            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double point = myPolygon.get(i);
                pen.move(point.x, point.y);
            }
            pen.move(first.x, first.y); 
        }
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
