import java.awt.*;
import java.util.List;

public class Pair {
    private int x; // X coordinate of the pair
    private int y; // Y coordinate of the pair
    private Color color; // Color associated with the pair
    private List<Pair> shape; // List representing a shape made of pairs

    // Constructor to initialize a pair with coordinates
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Constructor to initialize a pair with color and shape list
    public Pair(Color c, List<Pair> shape) {
        this.color = c;
        this.shape = shape;
    }

    // Setter for x-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Setter for y-coordinate
    public void setY(int y) {
        this.y = y;
    }

    // Setter for color
    public void setColor(Color color) {
        this.color = color;
    }

    // Setter for shape list
    public void setShape(List<Pair> shape) {
        this.shape = shape;
    }

    // Getter for x-coordinate
    public int getX() {
        return x;
    }

    // Getter for y-coordinate
    public int getY() {
        return y;
    }

    // Getter for color
    public Color getColor() {
        return color;
    }

    // Getter for shape list
    public List<Pair> getShape() {
        return shape;
    }
}
