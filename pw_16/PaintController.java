import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintController {
    private PaintPanel paintPanel;
    private JButton clearButton;
    private JButton[] colorButtons;
    private Color[] colors;
    private JSlider thicknessSlider;

    public PaintController(PaintPanel paintPanel, JButton clearButton,
                          JButton[] colorButtons, Color[] colors, 
                          JSlider thicknessSlider) {
        this.paintPanel = paintPanel;
        this.clearButton = clearButton;
        this.colorButtons = colorButtons;
        this.colors = colors;
        this.thicknessSlider = thicknessSlider;

        setupListeners();
    }

    private void setupListeners() {
        // Кнопка "Очистить холст"
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    null,
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

        // Кнопки выбора цвета (теперь это цветные квадраты)
        for (int i = 0; i < colorButtons.length; i++) {
            final int index = i;
            colorButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    paintPanel.setCurrentColor(colors[index]);
                    
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
    }
}