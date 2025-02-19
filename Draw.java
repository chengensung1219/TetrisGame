import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Draw extends JPanel {
    private int x, y, width, height;
    private Color color;
    private Timer timer;
    Blocks blocks = new Blocks();

    public Draw() {

        color = Color.BLUE;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        blocks.currentShape.forEach(pair -> pair.setX(pair.getX() - 1));
                        break;
                    case KeyEvent.VK_RIGHT:
                        blocks.currentShape.forEach(pair -> pair.setX(pair.getX() + 1));
                        break;
                    case KeyEvent.VK_DOWN:
                        blocks.currentShape.forEach(pair -> pair.setY(pair.getY() + 1));
                        break;
                    case KeyEvent.VK_UP:
                        blocks.Rotate();
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });

        setFocusable(true);
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blocks.currentShape.forEach(pair ->
                        pair.setY(pair.getY() + 1));

                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int squareSize = 25;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(squareSize, squareSize, 27 * squareSize, 33 * squareSize);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(squareSize, squareSize, 27 * squareSize, 33 * squareSize);

        g.setColor(color);
        blocks.currentShape.forEach(pair ->
            g.fillRect(
                pair.getX() * squareSize,
                pair.getY() * squareSize,
                squareSize,
                squareSize));
    }


}
