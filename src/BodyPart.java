import java.awt.*;

public class BodyPart {

    private int xCoordinate, yCoordinate, width, height;

    public BodyPart(int xCoordinate, int yCoordinate,
                    int tileSize) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = tileSize;
        this.height = tileSize;
    }

    public void tick() {

    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(xCoordinate * width,
                yCoordinate * height, width, height);
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
