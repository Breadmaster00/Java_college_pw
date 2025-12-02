import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PaintPanel extends JPanel {
    private ArrayList<PaintLine> lines;  // Храним отдельные линии
    private ArrayList<Point> currentPoints; // Текущая рисуемая линия
    private Color currentColor;
    private int currentThickness;

    public PaintPanel() {
        lines = new ArrayList<>();
        currentPoints = new ArrayList<>();
        currentColor = Color.BLACK;
        currentThickness = 5;
        setBackground(Color.WHITE);

        // Обработка событий мыши
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Начинаем новую линию
                currentPoints = new ArrayList<>();
                currentPoints.add(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Добавляем точку в текущую линию и перерисовываем
                currentPoints.add(e.getPoint());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Завершаем текущую линию и сохраняем ее
                if (!currentPoints.isEmpty()) {
                    lines.add(new PaintLine(
                        new ArrayList<>(currentPoints),
                        currentColor,
                        currentThickness
                    ));
                    currentPoints.clear();
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void setCurrentThickness(int thickness) {
        this.currentThickness = thickness;
    }

    public void clear() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Рисуем все сохраненные линии
        for (PaintLine line : lines) {
            line.draw(g2d);
        }

        // Рисуем текущую линию (если она есть)
        if (!currentPoints.isEmpty()) {
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(currentThickness));
            
            for (int i = 1; i < currentPoints.size(); i++) {
                Point p1 = currentPoints.get(i - 1);
                Point p2 = currentPoints.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}