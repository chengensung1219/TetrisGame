import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class GamePlay extends JPanel {
    // Enum to manage the game states
    enum GameState {
        WELCOME, RUNNING, PAUSE, GAMEOVER
    }
    private Timer timer;
    private GameState gameState = GameState.WELCOME;
    Blocks blocks = new Blocks();
    private int row = 30;
    private int col = 14;
    FontImport font = new FontImport();
    private int score = 0;
    private int level = 1;
    private int levelUp = 0;

    private int speed = 0;

    public GamePlay() {
        // Add keyboard event listener to handle player input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                // Start the game when space is pressed
                if (keyCode == KeyEvent.VK_SPACE) {
                    gameState = GameState.RUNNING;
                }
                // Toggle pause when 'P' is pressed
                if (keyCode == KeyEvent.VK_P) {
                    togglePause();
                }
                // Restart game when 'R' is pressed and game is over
                if (keyCode == KeyEvent.VK_R && gameState == GameState.GAMEOVER) {
                    restartGame();
                }
                // Handle movement controls when the game is running
                if (gameState == GameState.RUNNING) {
                    if (canMoveLeft()){
                        switch (keyCode) {
                            case KeyEvent.VK_LEFT:
                                blocks.currentShape.forEach(pair -> pair.setX(pair.getX() - 1));
                                break;
                            default:
                                break;
                        }
                    }
                    if (canMoveRight()){
                        switch (keyCode) {
                            case KeyEvent.VK_RIGHT:
                                blocks.currentShape.forEach(pair -> pair.setX(pair.getX() + 1));
                                break;
                            default:
                                break;
                        }
                    }
                    if (canMoveDown()){
                        switch (keyCode) {
                            case KeyEvent.VK_DOWN:
                                blocks.currentShape.forEach(pair -> pair.setY(pair.getY() + 1));
                                break;
                            case KeyEvent.VK_UP:
                                blocks.Rotate();
                                canRotate();
                                break;
                            default:
                                break;
                        }
                        repaint();  // Redraw after movement
                    }
                }
            }
        });
        setFocusable(true);

        // Initialize game timer to control block dropping speed
        timer = new Timer(600 - speed, e -> {
            if (gameState == GameState.RUNNING) {
                if (!canMoveDown()) {
                    // Place block if it can't move down further
                    blocks.baseBlocks.addAll(blocks.currentShape);
                    blocks.currentShape = blocks.getNewShape();
                } else {
                    // Move the block down if possible
                    blocks.currentShape.forEach(pair ->
                            pair.setY(pair.getY() + 1));
                    repaint();
                }
                // Check if game is over
                if (gameOver()){
                    gameState = GameState.GAMEOVER;
                    repaint();
                }
                // Remove completed rows
                eliminateBlocks();
            }

        });
        timer.start();
    }
    // Toggles between running and paused states
    private void togglePause() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSE;
            repaint();
            timer.stop();
        } else if (gameState == GameState.PAUSE) {
            gameState = GameState.RUNNING;
            timer.start();
        }
    }
    // Checks if the block can move downward
    private boolean canMoveDown() {
        for (Pair p : blocks.currentShape) {

            if (p.getY() + 1 >= row - 2) {
                return false;
            }
            for (Pair base : blocks.baseBlocks) {
                if (p.getX() == base.getX() && p.getY() + 1 == base.getY()) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean canMoveLeft() {
        // Checks if the block can move left
        for (Pair p : blocks.currentShape) {
            if (p.getX()  <= 1) {
                return false;
            }
            for (Pair base : blocks.baseBlocks) {
                if (p.getX() == base.getX() + 1 && p.getY() == base.getY()) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean canMoveRight() {
        // Checks if the block can move right
        for (Pair p : blocks.currentShape) {
            if (p.getX() >= col) {
                return false;
            }
            for (Pair base : blocks.baseBlocks) {
                if (p.getX() == base.getX() - 1 && p.getY() == base.getY()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void canRotate() {

        for (Pair p : blocks.currentShape) {
            if (p.getX() > col) {
                //move to the left
                blocks.currentShape.forEach(pair -> pair.setX(pair.getX() - 1));
            }
            if (p.getX() < 1) {
                //move to the right
                blocks.currentShape.forEach(pair -> pair.setX(pair.getX() + 1));
            }
        }
    }
    // Ensures the block rotation stays within bounds
    private void eliminateBlocks() {
        List<Integer> eliminate = new ArrayList<>();

        for (int y = 0; y < row; y++) {
            int count = 0;
            for (Pair base : blocks.baseBlocks) {
                if (base.getY() == y) {
                    count++;
                }
            }

            if (count == col) {
                eliminate.add(y);
                score += 10;
                levelUp ++;
                if (levelUp == 4) {
                    levelUp = 0;
                    speed += 10;
                    level += 1;
                }
            }
        }

        if (!eliminate.isEmpty()) {
            for (int row : eliminate) {
                removeRow(row);
            }
        }
    }
    // Removes a row and shifts blocks above downward

    private void removeRow(int row) {
        // Remove all blocks in the specified row
        blocks.baseBlocks.removeIf(pair -> pair.getY() == row);

        // Move blocks above the removed row downward
        for (Pair pair : blocks.baseBlocks) {
            if (pair.getY() < row) {
                pair.setY(pair.getY() + 1);
            }
        }
    }

    // Checks if game over condition is met
    private boolean gameOver() {
        for (Pair p : blocks.currentShape) {
            for (Pair base : blocks.baseBlocks) {
                if (p.getY() < 3 && p.getY() == base.getY() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //Resets the game state for a new round
    private void restartGame(){
        gameState = GameState.RUNNING;

        blocks.baseBlocks.clear();
        blocks.currentShape = blocks.getNewShape();
        levelUp = 0;
        speed = 0;
        level = 1;
        score = 0;

        timer.restart();
        repaint();
    }

    // Display the view
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int squareSize = 25;

        if (this.gameState == GameState.WELCOME) {

            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, 575, 750, 0, 0);

            g.setFont(font.silkscreenTitle2);
            g.setColor(Color.BLUE);
            g.drawString("Tetris Game", 100, 320);

            g.setFont(font.silkscreenRegular3);
            g.setColor(Color.BLACK);
            g.drawString("Press", 135, 370);
            g.setColor(Color.RED);
            g.drawString("SPACE", 227, 370);
            g.setColor(Color.BLACK);
            g.drawString("to start", 320, 370);

        }
        // Game Running
        if (this.gameState == GameState.RUNNING) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(squareSize, squareSize, col * squareSize, (row - 3) * squareSize, 8, 8);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(400, squareSize, 150, 150, 8, 8);

            g.setFont(font.silkscreenRegular3);
            g.setColor(Color.BLACK);
            g.drawString("Level", 435, 350);
            String levelText = String.valueOf(level);
            g.drawString(levelText, 460, 400);

            g.setFont(font.silkscreenRegular3);
            g.setColor(Color.BLACK);
            g.drawString("Score", 435, 600);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(400, 625, 150, 75, 8, 8);

            g.setFont(font.silkscreenRegular3);
            g.setColor(Color.BLACK);
            String scoreText = String.valueOf(score);
            g.drawString(scoreText, 460, 670);

            g.setColor(blocks.nextColor);
            blocks.nextShape.forEach(pair ->
                    g.fillRoundRect(
                            pair.getX() * squareSize + 275,
                            pair.getY() * squareSize + 40,
                            squareSize - 2,
                            squareSize - 2,
                            8,8));

            g.setColor(blocks.currentColor);
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

        if (this.gameState == GameState.PAUSE) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(0, 0, 575, 750, 0, 0);

            g.setFont(font.silkscreenTitle3);
            g.setColor(Color.BLACK);
            g.drawString("PAUSED", 200, 350);
        }

        // Game Over View
        if (this.gameState == GameState.GAMEOVER) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(0, 0, 575, 750, 0, 0);

            g.setFont(font.silkscreenRegular2);
            g.setColor(Color.BLUE);
            String result = "score: " + score + " / " + "Level: " + level;
            g.drawString(result, 130, 280);

            g.setFont(font.silkscreenTitle1);
            g.setColor(Color.RED);
            g.drawString("Game Over", 100, 350);

            g.setFont(font.silkscreenRegular2);
            g.setColor(Color.BLACK);
            g.drawString("Press", 120, 410);
            g.setColor(Color.RED);
            g.drawString("R", 240, 410);
            g.setColor(Color.BLACK);
            g.drawString("to restart", 280, 410);
        }


    }
}
