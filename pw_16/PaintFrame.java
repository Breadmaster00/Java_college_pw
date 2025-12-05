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

        paintPanel = new PaintPanel();
        clearButton = new JButton("Очистить холст");
        thicknessSlider = new JSlider(1, 30, 5);
        thicknessSlider.setMajorTickSpacing(5);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);

        colors = new Color[]{
            Color.BLACK,    
            Color.RED,      
            Color.BLUE,     
            Color.GREEN,    
            Color.YELLOW,   
            Color.MAGENTA,  
            Color.CYAN,     
            Color.ORANGE,   
            Color.PINK,    
            Color.GRAY,    
            new Color(139, 69, 19), 
            Color.WHITE     
        };

        colorButtons = new JButton[colors.length];
        JPanel colorPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colors[i]);
            colorButtons[i].setPreferredSize(new Dimension(40, 40)); 
            

            colorButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            colorButtons[i].setFocusPainted(false);
            colorButtons[i].setContentAreaFilled(true);
            colorButtons[i].setOpaque(true);
            
            colorPanel.add(colorButtons[i]);
        }


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        
        JPanel colorControlPanel = new JPanel();
        colorControlPanel.add(new JLabel("Цвет кисти:"));
        colorControlPanel.add(colorPanel);
        
        JPanel toolsPanel = new JPanel();
        toolsPanel.add(new JLabel("Толщина линии:"));
        toolsPanel.add(thicknessSlider);
        toolsPanel.add(Box.createHorizontalStrut(20)); // Отступ
        toolsPanel.add(clearButton);
        
        controlPanel.add(colorControlPanel);
        controlPanel.add(toolsPanel);

        PaintController controller = new PaintController(
            paintPanel, clearButton, colorButtons, colors, thicknessSlider
        );
        
        add(paintPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        
        colorButtons[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }
    
}