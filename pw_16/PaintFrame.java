import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class PaintFrame extends JFrame {
    private PaintPanel paintPanel;
    private JScrollPane scrollPane;
    private JButton clearButton, saveButton, loadButton, exportButton, resizeButton;
    private JSlider thicknessSlider;
    private JButton[] colorButtons;
    private Color[] colors;
    
    // RGB компоненты
    private JSlider redSlider, greenSlider, blueSlider;
    private JPanel colorPreviewPanel;
    private JButton applyColorButton;
    private JLabel rgbLabel;

    public PaintFrame() {
        setTitle("Мини Paint Pro");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создание холста
        paintPanel = new PaintPanel(800, 600);
        
        // Создание скроллпанели для холста
        scrollPane = new JScrollPane(paintPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        
        // Добавляем слушатель для скроллбаров
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                paintPanel.repaint();
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                paintPanel.repaint();
            }
        });

        // Создание кнопок
        clearButton = new JButton("Очистить");
        saveButton = new JButton("Сохранить");
        loadButton = new JButton("Загрузить");
        exportButton = new JButton("Экспорт");
        resizeButton = new JButton("Размер холста");
        
        // Слайдер толщины
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

        // Создаем квадратные кнопки цветов
        colorButtons = new JButton[colors.length];
        JPanel colorPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colors[i]);
            colorButtons[i].setPreferredSize(new Dimension(30, 30));
            colorButtons[i].setToolTipText(getColorName(colors[i]));
            colorButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            colorButtons[i].setFocusPainted(false);
            colorButtons[i].setContentAreaFilled(true);
            colorButtons[i].setOpaque(true);
            colorPanel.add(colorButtons[i]);
        }

        // Создание RGB палитры
        createRGBPalette();
        
        // Основная панель управления (справа)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Панель фиксированных цветов
        JPanel fixedColorsPanel = new JPanel();
        fixedColorsPanel.setLayout(new BoxLayout(fixedColorsPanel, BoxLayout.Y_AXIS));
        fixedColorsPanel.setBorder(BorderFactory.createTitledBorder("Быстрые цвета"));
        fixedColorsPanel.add(colorPanel);
        
        // Панель RGB
        JPanel rgbPanel = new JPanel();
        rgbPanel.setLayout(new BoxLayout(rgbPanel, BoxLayout.Y_AXIS));
        rgbPanel.setBorder(BorderFactory.createTitledBorder("RGB Палитра"));
        rgbPanel.add(colorPreviewPanel);
        rgbPanel.add(Box.createVerticalStrut(5));
        rgbPanel.add(new JLabel("Красный:"));
        rgbPanel.add(redSlider);
        rgbPanel.add(new JLabel("Зеленый:"));
        rgbPanel.add(greenSlider);
        rgbPanel.add(new JLabel("Синий:"));
        rgbPanel.add(blueSlider);
        rgbPanel.add(rgbLabel);
        rgbPanel.add(Box.createVerticalStrut(5));
        rgbPanel.add(applyColorButton);
        
        // Панель инструментов
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
        toolsPanel.setBorder(BorderFactory.createTitledBorder("Инструменты"));
        toolsPanel.add(new JLabel("Толщина линии:"));
        toolsPanel.add(thicknessSlider);
        toolsPanel.add(Box.createVerticalStrut(10));
        toolsPanel.add(clearButton);
        
        // Панель файловых операций
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
        filePanel.setBorder(BorderFactory.createTitledBorder("Файл"));
        filePanel.add(saveButton);
        filePanel.add(Box.createVerticalStrut(5));
        filePanel.add(loadButton);
        filePanel.add(Box.createVerticalStrut(5));
        filePanel.add(exportButton);
        filePanel.add(Box.createVerticalStrut(5));
        filePanel.add(resizeButton);
        
        // Добавляем все панели на правую панель
        rightPanel.add(fixedColorsPanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(rgbPanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(toolsPanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(filePanel);
        
        // Создание контроллера
        PaintController controller = new PaintController(
            paintPanel, clearButton, colorButtons, colors, thicknessSlider,
            saveButton, loadButton, exportButton, resizeButton, this,
            redSlider, greenSlider, blueSlider, colorPreviewPanel, rgbLabel, applyColorButton
        );

        // Добавление компонентов в окно
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        // Центрируем окно на экране
        setLocationRelativeTo(null);
        
        // Выделяем черный цвет по умолчанию
        colorButtons[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }
    
    // Создание RGB палитры
    private void createRGBPalette() {
        // Слайдеры RGB
        redSlider = new JSlider(0, 255, 0);
        greenSlider = new JSlider(0, 255, 0);
        blueSlider = new JSlider(0, 255, 0);
        
        redSlider.setMajorTickSpacing(50);
        greenSlider.setMajorTickSpacing(50);
        blueSlider.setMajorTickSpacing(50);
        
        redSlider.setPaintTicks(true);
        greenSlider.setPaintTicks(true);
        blueSlider.setPaintTicks(true);
        
        redSlider.setPaintLabels(true);
        greenSlider.setPaintLabels(true);
        blueSlider.setPaintLabels(true);
        
        // Панель предпросмотра цвета
        colorPreviewPanel = new JPanel();
        colorPreviewPanel.setPreferredSize(new Dimension(150, 50));
        colorPreviewPanel.setBackground(Color.BLACK);
        colorPreviewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // Метка с RGB значениями
        rgbLabel = new JLabel("RGB: 0, 0, 0");
        rgbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Кнопка применения цвета
        applyColorButton = new JButton("Применить цвет");
        applyColorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    
    // Метод для обновления скроллпанели при изменении размера холста
    public void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}