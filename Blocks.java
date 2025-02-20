import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Blocks {

    String type;
    List<Pair> currentShape;
    Color color;
    List<String> blockType = List.of("I", "J", "O", "T", "Z", "L", "S");

    List<Pair> baseBlocks = new ArrayList<>();

    List<Pair> Ishape = List.of(new Pair(13, -1), new Pair(13, 0), new Pair(13, 1), new Pair(13, 2));
    List<Pair> Oshape = List.of(new Pair(13, 1), new Pair(13, 2), new Pair(14, 1), new Pair(14, 2));
    List<Pair> Jshape = List.of(new Pair(13, 0), new Pair(13, 1), new Pair(13, 2), new Pair(12, 2));
    List<Pair> Lshape = List.of(new Pair(13, 0), new Pair(13, 1), new Pair(13, 2), new Pair(14, 2));
    List<Pair> Tshape = List.of(new Pair(13, 1), new Pair(14, 1), new Pair(15, 1), new Pair(14, 2));
    List<Pair> Zshape = List.of(new Pair(12, 1), new Pair(13, 1), new Pair(13, 2), new Pair(14, 2));
    List<Pair> Sshape = List.of(new Pair(14, 1), new Pair(13, 1), new Pair(13, 2), new Pair(12, 2));

    public Blocks() {
        this.type = getRandomType();
        this.currentShape = getShape(this.type);

    }
    public String getRandomType(){
        int randomIndex = (int)(Math.random() * blockType.size());
        return blockType.get(randomIndex);
    }

    public List<Pair> getNewShape(){
        Blocks blocks = new Blocks();
        return blocks.currentShape;
    }

    public List<Pair> getShape(String type) {
        switch (type){
            case "I":
                this.color = Color.GREEN;
                return Ishape;
            case "J":
                this.color = Color.CYAN;
                return Jshape;
            case "O":
                this.color = Color.ORANGE;
                return Oshape;
            case "T":
                this.color = Color.RED;
                return Tshape;
            case "Z":
                this.color = Color.YELLOW;
                return Zshape;
            case "L":
                this.color = Color.PINK;
                return Lshape;
            case "S":
                this.color = Color.BLUE;
                return Sshape;
            default:
                return null;
        }
    }


    public List<Pair> Rotate() {
        if (type.equals("O"))
            return currentShape;

        List<Pair> rotatedShape = new ArrayList<>();
        Pair center = currentShape.get(1);
        for (Pair block : currentShape) {
            int newX = center.getX() + (center.getY() - block.getY());
            int newY = center.getY() - (center.getX() - block.getX());
            rotatedShape.add(new Pair(newX, newY));
        }


        currentShape = rotatedShape;
        return currentShape;
    }
}
