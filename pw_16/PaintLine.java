import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PaintLine implements Serializable {
    private ArrayList<Point> points;
    private Color color;
    private int thickness;

    public PaintLine(ArrayList<Point> points, Color color, int thickness) {
        this.points = new ArrayList<>(points);
        this.color = color;
        this.thickness = thickness;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }
}