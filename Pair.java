import java.awt.*;
import java.util.List;
public class Pair{
    private int x;
    private int y;
    private Color color;
    private List<Pair> shape;
    Pair( int x, int y){
        this.x = x;
        this.y = y;
    }

    Pair( Color c, List<Pair> shape){
        this.color = c;
        this.shape = shape;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void setColor(Color color){
        this.color = color;
    }
    public void setShape(List<Pair> shape){
        this.shape = shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
    public List<Pair> getShape() {
        return shape;
    }
}
