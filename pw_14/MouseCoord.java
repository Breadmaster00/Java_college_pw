import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseCoord {
    public static void main(String[] args) {
            JFrame frame = new JFrame("Координаты мыши");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);
            frame.setLayout(new BorderLayout());
            
            JLabel coordLabel = new JLabel("Наведите мышь на панель", SwingConstants.CENTER);
            coordLabel.setFont(new Font("Arial", Font.BOLD, 16));
            coordLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JPanel mousePanel = new JPanel();
            mousePanel.setBackground(Color.LIGHT_GRAY);
            mousePanel.setPreferredSize(new Dimension(800, 500));
            mousePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            
            mousePanel.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    coordLabel.setText("Координаты: X=" + e.getX() + ", Y=" + e.getY());
                }
            });
            
            mousePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Клик по координатам: x=" + e.getX() + ", y=" + e.getY());
                }
            });
            
            JButton clearButton = new JButton("Очистить");
            clearButton.setFont(new Font("Arial", Font.PLAIN, 14));
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    coordLabel.setText("Наведите мышь на панель");
                }
            });
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(clearButton);
            
            frame.add(coordLabel, BorderLayout.NORTH);
            frame.add(mousePanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            
            frame.setVisible(true);
    }
};
