import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintController {
    private PaintPanel paintPanel;
    private JButton clearButton;
    private JButton[] colorButtons;
    private Color[] colors;
    private JSlider thicknessSlider;
    private JButton saveButton, loadButton, exportButton, resizeButton;
    private JFrame parentFrame;
    
    // RGB компоненты
    private JSlider redSlider, greenSlider, blueSlider;
    private JPanel colorPreviewPanel;
    private JLabel rgbLabel;
    private JButton applyColorButton;
    
    public PaintController(PaintPanel paintPanel, JButton clearButton,
                          JButton[] colorButtons, Color[] colors, 
                          JSlider thicknessSlider,
                          JButton saveButton, JButton loadButton, 
                          JButton exportButton, JButton resizeButton,
                          JFrame parentFrame,
                          JSlider redSlider, JSlider greenSlider, JSlider blueSlider,
                          JPanel colorPreviewPanel, JLabel rgbLabel, JButton applyColorButton) {
        this.paintPanel = paintPanel;
        this.clearButton = clearButton;
        this.colorButtons = colorButtons;
        this.colors = colors;
        this.thicknessSlider = thicknessSlider;
        this.saveButton = saveButton;
        this.loadButton = loadButton;
        this.exportButton = exportButton;
        this.resizeButton = resizeButton;
        this.parentFrame = parentFrame;
        
        // RGB компоненты
        this.redSlider = redSlider;
        this.greenSlider = greenSlider;
        this.blueSlider = blueSlider;
        this.colorPreviewPanel = colorPreviewPanel;
        this.rgbLabel = rgbLabel;
        this.applyColorButton = applyColorButton;

        setupListeners();
    }

    private void setupListeners() {
        // Кнопка "Очистить"
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    parentFrame,
                    "Вы уверены, что хотите очистить холст?",
                    "Очистка холста",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (result == JOptionPane.YES_OPTION) {
                    paintPanel.clear();
                }
            }
        });

        // Кнопки выбора цвета
        for (int i = 0; i < colorButtons.length; i++) {
            final int index = i;
            colorButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color selectedColor = colors[index];
                    paintPanel.setCurrentColor(selectedColor);
                    
                    // Обновляем RGB слайдеры
                    redSlider.setValue(selectedColor.getRed());
                    greenSlider.setValue(selectedColor.getGreen());
                    blueSlider.setValue(selectedColor.getBlue());
                    
                    updateColorPreview();
                    
                    // Визуальная обратная связь - рамка для выбранного цвета
                    for (JButton button : colorButtons) {
                        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                    }
                    colorButtons[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                }
            });
        }

        // Ползунок толщины
        thicknessSlider.addChangeListener(e -> {
            paintPanel.setCurrentThickness(thicknessSlider.getValue());
        });
        
        // RGB слайдеры
        ChangeListener rgbChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColorPreview();
            }
        };
        
        redSlider.addChangeListener(rgbChangeListener);
        greenSlider.addChangeListener(rgbChangeListener);
        blueSlider.addChangeListener(rgbChangeListener);
        
        // Кнопка применения RGB цвета
        applyColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color rgbColor = new Color(
                    redSlider.getValue(),
                    greenSlider.getValue(),
                    blueSlider.getValue()
                );
                paintPanel.setCurrentColor(rgbColor);
                
                // Снимаем выделение с фиксированных цветов
                for (JButton button : colorButtons) {
                    button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                }
                
                JOptionPane.showMessageDialog(parentFrame, 
                    "Цвет применен! RGB: " + 
                    redSlider.getValue() + ", " + 
                    greenSlider.getValue() + ", " + 
                    blueSlider.getValue(), 
                    "Цвет применен", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Кнопка "Сохранить"
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Сохранить рисунок");
            fileChooser.setSelectedFile(new File("drawing.paint"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Paint Files (*.paint)", "paint"));
            
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".paint")) {
                    filePath += ".paint";
                }
                
                if (paintPanel.saveToFile(filePath)) {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Рисунок успешно сохранен!", 
                        "Сохранение", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Ошибка при сохранении рисунка!", 
                        "Ошибка", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Кнопка "Загрузить"
        loadButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(parentFrame,
                "Текущий рисунок будет потерян. Продолжить?",
                "Загрузка",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (result == JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Загрузить рисунок");
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Paint Files (*.paint)", "paint"));
                
                int userSelection = fileChooser.showOpenDialog(parentFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToLoad = fileChooser.getSelectedFile();
                    if (paintPanel.loadFromFile(fileToLoad.getAbsolutePath())) {
                        JOptionPane.showMessageDialog(parentFrame, 
                            "Рисунок успешно загружен!", 
                            "Загрузка", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, 
                            "Ошибка при загрузке рисунка!", 
                            "Ошибка", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // Кнопка "Экспорт"
        exportButton.addActionListener(e -> {
            if (paintPanel == null) {
                JOptionPane.showMessageDialog(parentFrame, 
                    "Нет данных для экспорта!", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String[] formats = {"PNG", "JPG", "BMP"};
            String format = (String) JOptionPane.showInputDialog(parentFrame,
                "Выберите формат экспорта:",
                "Экспорт рисунка",
                JOptionPane.QUESTION_MESSAGE,
                null,
                formats,
                formats[0]);
            
            if (format != null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Экспорт рисунка");
                fileChooser.setSelectedFile(new File("drawing." + format.toLowerCase()));
                
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();
                    if (!filePath.toLowerCase().endsWith("." + format.toLowerCase())) {
                        filePath += "." + format.toLowerCase();
                    }
                    
                    if (paintPanel.exportToImage(filePath, format)) {
                        JOptionPane.showMessageDialog(parentFrame, 
                            "Рисунок успешно экспортирован!", 
                            "Экспорт", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, 
                            "Ошибка при экспорте рисунка!", 
                            "Ошибка", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // Кнопка "Размер холста"
        resizeButton.addActionListener(e -> {
            Dimension currentSize = paintPanel.getCanvasSize();
            
            JTextField widthField = new JTextField(String.valueOf(currentSize.width), 5);
            JTextField heightField = new JTextField(String.valueOf(currentSize.height), 5);
            
            JPanel panel = new JPanel();
            panel.add(new JLabel("Ширина:"));
            panel.add(widthField);
            panel.add(Box.createHorizontalStrut(15));
            panel.add(new JLabel("Высота:"));
            panel.add(heightField);
            
            int result = JOptionPane.showConfirmDialog(parentFrame, panel, 
                "Задать размер холста", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    
                    if (width < 100 || width > 5000 || height < 100 || height > 5000) {
                        JOptionPane.showMessageDialog(parentFrame,
                            "Размеры должны быть в диапазоне 100-5000 пикселей!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    paintPanel.setCanvasSize(width, height);
                    ((PaintFrame)parentFrame).updateScrollPane();
                    
                    JOptionPane.showMessageDialog(parentFrame,
                        "Размер холста изменен на " + width + "x" + height + " пикселей",
                        "Размер изменен",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "Введите корректные числа!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    // Метод для обновления предпросмотра цвета
    private void updateColorPreview() {
        int r = redSlider.getValue();
        int g = greenSlider.getValue();
        int b = blueSlider.getValue();
        
        Color previewColor = new Color(r, g, b);
        colorPreviewPanel.setBackground(previewColor);
        rgbLabel.setText("RGB: " + r + ", " + g + ", " + b);
        
        // Изменяем цвет текста в зависимости от яркости фона
        double brightness = (r * 0.299 + g * 0.587 + b * 0.114) / 255;
        if (brightness > 0.5) {
            rgbLabel.setForeground(Color.BLACK);
        } else {
            rgbLabel.setForeground(Color.WHITE);
        }
    }
}