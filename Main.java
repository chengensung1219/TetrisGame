import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        frame.setSize(575, 750);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePlay GamePlay = new GamePlay();
        frame.add(GamePlay);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
