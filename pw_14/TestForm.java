import javax.swing.*;
import java.awt.*;

public class TestForm extends JFrame {
    public TestForm() {
        super("Тестовое приложение");
        super.setBounds(200, 100, 250, 100);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));

        JLabel name = new JLabel("Приветик пока!");
        JTextField name_field = new JTextField("", 1);
        JLabel name_e = new JLabel("Приветик пока!");
        JTextField name_f = new JTextField("", 1);

        container.add(name);
        container.add(name_field);
        container.add(name_e);
        
    }
}