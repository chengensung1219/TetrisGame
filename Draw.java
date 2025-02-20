import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Draw extends JPanel {
    private Timer timer;
    Blocks blocks = new Blocks();
    private int row = 36;

    public Draw() {

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

                if (!canMoveDown()){
                    blocks.baseBlocks.addAll(blocks.currentShape);
                    blocks.currentShape = blocks.getNewShape(); // Get new block
                    return;
                }
                blocks.currentShape.forEach(pair ->
                        pair.setY(pair.getY() + 1));
                repaint();
            }
        });
        timer.start();
    }

    private boolean canMoveDown() {
        for (Pair p : blocks.currentShape) {
            // Check if it reaches the bottom
            if (p.getY() + 1 >= row - 2) {
                return false;
            }
            // Check if it collides with a base block
            for (Pair base : blocks.baseBlocks) {
                if (p.getX() == base.getX() && p.getY() + 1 == base.getY()) {
                    return false;
                }
            }
        }
        return true;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int squareSize = 25;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(squareSize, squareSize, 27 * squareSize, 33 * squareSize, 8, 8);

        g.setColor(Color.DARK_GRAY);
        g.drawRoundRect(squareSize, squareSize, 27 * squareSize, 33 * squareSize, 8, 8);

        g.setColor(blocks.color);
        blocks.currentShape.forEach(pair ->
            g.fillRoundRect(
                pair.getX() * squareSize + 1,
                pair.getY() * squareSize + 1,
                squareSize - 2,
                squareSize - 2,
                    8,8));


        g.setColor(Color.GRAY);
        blocks.baseBlocks.forEach(pair ->
                g.fillRoundRect(
                        pair.getX() * squareSize + 1,
                        pair.getY() * squareSize + 1,
                        squareSize - 2,
                        squareSize - 2,
                        8, 8
                ));
    }


}
