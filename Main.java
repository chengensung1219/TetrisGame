import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        frame.setSize(900, 900);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Draw draw = new Draw();
        frame.add(draw);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
