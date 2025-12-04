import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class PaintPanel extends JPanel {
    private ArrayList<PaintLine> lines;
    private ArrayList<Point> currentPoints;
    private Color currentColor;
    private int currentThickness;
    private int canvasWidth;
    private int canvasHeight;
    
    public PaintPanel() {
        this(800, 600); // Размер по умолчанию
    }
    
    public PaintPanel(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        lines = new ArrayList<>();
        currentPoints = new ArrayList<>();
        currentColor = Color.BLACK;
        currentThickness = 5;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        
        // Включаем двойную буферизацию для уменьшения мерцания
        setDoubleBuffered(true);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentPoints = new ArrayList<>();
                currentPoints.add(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentPoints.add(e.getPoint());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!currentPoints.isEmpty() && currentPoints.size() > 1) {
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
    
    // Метод для изменения размера холста
    public void setCanvasSize(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        setPreferredSize(new Dimension(width, height));
        revalidate(); // Перекомпоновываем компоненты
        repaint();    // Перерисовываем
    }
    
    // Метод для получения текущего размера холста
    public Dimension getCanvasSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }
    
    // Метод для сохранения рисунка в файл
    public boolean saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(lines);
            oos.writeInt(canvasWidth);
            oos.writeInt(canvasHeight);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
            return false;
        }
    }
    
    // Метод для загрузки рисунка из файла
    @SuppressWarnings("unchecked")
    public boolean loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            lines = (ArrayList<PaintLine>) ois.readObject();
            canvasWidth = ois.readInt();
            canvasHeight = ois.readInt();
            setPreferredSize(new Dimension(canvasWidth, canvasHeight));
            revalidate();
            repaint();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки: " + e.getMessage());
            return false;
        }
    }
    
    // Метод для экспорта в изображение
    public boolean exportToImage(String fileName, String format) {
        // Создаем изображение с прозрачным фоном
        BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Включаем антиалиасинг для лучшего качества
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Заливаем белым цветом
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvasWidth, canvasHeight);
        
        // Рисуем все линии
        for (PaintLine line : lines) {
            line.draw(g2d);
        }
        
        g2d.dispose();
        
        try {
            return javax.imageio.ImageIO.write(image, format, new File(fileName));
        } catch (IOException e) {
            System.err.println("Ошибка экспорта: " + e.getMessage());
            return false;
        }
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
        
        // Включаем антиалиасинг для плавных линий
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Рисуем границу холста
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, canvasWidth - 1, canvasHeight - 1);

        // Рисуем все сохраненные линии
        for (PaintLine line : lines) {
            line.draw(g2d);
        }

        // Рисуем текущую линию (если она есть)
        if (!currentPoints.isEmpty() && currentPoints.size() > 1) {
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(currentThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
            for (int i = 1; i < currentPoints.size(); i++) {
                Point p1 = currentPoints.get(i - 1);
                Point p2 = currentPoints.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}