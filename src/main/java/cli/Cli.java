package cli;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.StrokeBorder;
import java.awt.BasicStroke;


public class Cli extends JFrame {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.setTitle("SQL Generator");
        cli.setBounds(200,0,1500,1000);
        JTextField filePath = new JTextField("Enter File Path");
        filePath.setBounds(250,100,200,20);
        JButton button = new JButton("Submit");
        button.setBounds(250,150,200,20);
        button.setBorder(new StrokeBorder(new BasicStroke()));

        cli.add(filePath);
        cli.add(button);

        cli.setVisible(true);

    }
}
