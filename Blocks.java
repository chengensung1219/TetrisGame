import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Blocks {

    String currentType;
    String nextType;
    List<Pair> currentShape;
    List<Pair> nextShape;
    Color currentColor;
    Color nextColor;
    List<String> blockType = List.of("I", "J", "O", "T", "Z", "L", "S");
    List<Pair> baseBlocks = new ArrayList<>();
    List<Pair> Ishape = List.of(new Pair(6, 2), new Pair(7, 2), new Pair(8, 2), new Pair(9, 2));
    List<Pair> Oshape = List.of(new Pair(7, 1), new Pair(7, 2), new Pair(8, 1), new Pair(8, 2));
    List<Pair> Jshape = List.of(new Pair(8, 1), new Pair(8, 2), new Pair(8, 3), new Pair(7, 3));
    List<Pair> Lshape = List.of(new Pair(7, 1), new Pair(7, 2), new Pair(7, 3), new Pair(8, 3));
    List<Pair> Tshape = List.of(new Pair(7, 1), new Pair(7, 2), new Pair(7, 3), new Pair(8, 2));
    List<Pair> Zshape = List.of(new Pair(8, 1), new Pair(8, 2), new Pair(7, 2), new Pair(7, 3));
    List<Pair> Sshape = List.of(new Pair(7, 1), new Pair(7, 2), new Pair(8, 2), new Pair(8, 3));

    public Blocks() {
        this.currentType = getRandomType();
        this.currentShape = getShape(this.currentType).getShape();
        this.currentColor = getShape(this.currentType).getColor();
        this.nextType = getRandomType();
        while (currentType.equals(nextType)){
            this.nextType = getRandomType();
        }
        this.nextShape = getShape(this.nextType).getShape();
        this.nextColor = getShape(this.nextType).getColor();

    }
    public String getRandomType(){
        int randomIndex = (int)(Math.random() * blockType.size());
        return blockType.get(randomIndex);
    }

    public List<Pair> getNewShape(){
        this.currentShape = this.nextShape;
        this.currentColor = this.nextColor;
        Blocks blocks = new Blocks();
        this.nextShape = blocks.currentShape;
        this.nextColor = blocks.currentColor;
        return this.currentShape;
    }

    public Pair getShape(String type) {
        switch (type){
            case "I":
                return new Pair(Color.GREEN, Ishape);
            case "J":
                return new Pair(Color.CYAN, Jshape);
            case "O":
                return new Pair(Color.ORANGE, Oshape);
            case "T":
                return new Pair(Color.RED, Tshape);
            case "Z":
                return new Pair(Color.YELLOW, Zshape);
            case "L":
                return new Pair(Color.PINK, Lshape);
            case "S":
                return new Pair(Color.BLUE, Sshape);
            default:
                return null;
        }
    }


    public List<Pair> Rotate() {
        if (currentType.equals("O"))
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
