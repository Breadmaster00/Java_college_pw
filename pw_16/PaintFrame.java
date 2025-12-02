import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {
    private PaintPanel paintPanel;
    private JButton clearButton;
    private JSlider thicknessSlider;
    private JButton[] colorButtons;
    private Color[] colors;

    public PaintFrame() {
        setTitle("Мини Paint");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создание компонентов
        paintPanel = new PaintPanel();
        clearButton = new JButton("Очистить холст");
        thicknessSlider = new JSlider(1, 30, 5);
        thicknessSlider.setMajorTickSpacing(5);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);

        // Определяем цвета для квадратов
        colors = new Color[]{
            Color.BLACK,    // Черный
            Color.RED,      // Красный
            Color.BLUE,     // Синий
            Color.GREEN,    // Зеленый
            Color.YELLOW,   // Желтый
            Color.MAGENTA,  // Пурпурный
            Color.CYAN,     // Голубой
            Color.ORANGE,   // Оранжевый
            Color.PINK,     // Розовый
            Color.GRAY,     // Серый
            new Color(139, 69, 19), // Коричневый
            Color.WHITE     // Белый
        };

        // Создаем квадратные кнопки цветов (без текста)
        colorButtons = new JButton[colors.length];
        JPanel colorPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colors[i]);
            colorButtons[i].setPreferredSize(new Dimension(40, 40)); // Квадраты 40x40
            colorButtons[i].setToolTipText(getColorName(colors[i])); // Всплывающая подсказка
            
            // Убираем границы и отступы для минималистичного вида
            colorButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            colorButtons[i].setFocusPainted(false);
            colorButtons[i].setContentAreaFilled(true);
            colorButtons[i].setOpaque(true);
            
            colorPanel.add(colorButtons[i]);
        }

        // Панель управления - верхняя часть
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        
        // Панель для цвета
        JPanel colorControlPanel = new JPanel();
        colorControlPanel.add(new JLabel("Цвет кисти:"));
        colorControlPanel.add(colorPanel);
        
        // Панель для толщины и очистки
        JPanel toolsPanel = new JPanel();
        toolsPanel.add(new JLabel("Толщина линии:"));
        toolsPanel.add(thicknessSlider);
        toolsPanel.add(Box.createHorizontalStrut(20)); // Отступ
        toolsPanel.add(clearButton);
        
        // Добавляем все на основную панель управления
        controlPanel.add(colorControlPanel);
        controlPanel.add(toolsPanel);

        // Создание контроллера
        PaintController controller = new PaintController(
            paintPanel, clearButton, colorButtons, colors, thicknessSlider
        );

        // Добавление компонентов в окно
        add(paintPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        // Центрируем окно на экране
        setLocationRelativeTo(null);
        
        // Выделяем черный цвет по умолчанию
        colorButtons[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }
    
    // Вспомогательный метод для получения названия цвета
    private String getColorName(Color color) {
        if (color.equals(Color.BLACK)) return "Черный";
        if (color.equals(Color.RED)) return "Красный";
        if (color.equals(Color.BLUE)) return "Синий";
        if (color.equals(Color.GREEN)) return "Зеленый";
        if (color.equals(Color.YELLOW)) return "Желтый";
        if (color.equals(Color.MAGENTA)) return "Пурпурный";
        if (color.equals(Color.CYAN)) return "Голубой";
        if (color.equals(Color.ORANGE)) return "Оранжевый";
        if (color.equals(Color.PINK)) return "Розовый";
        if (color.equals(Color.GRAY)) return "Серый";
        if (color.equals(new Color(139, 69, 19))) return "Коричневый";
        if (color.equals(Color.WHITE)) return "Белый";
        return "Неизвестный цвет";
    }
}