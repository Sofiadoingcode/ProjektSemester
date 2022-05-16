package dat.startcode.model.entities;

public class Shed {
    private int width;
    private int length;
    private String floorMaterial;

    public Shed(int width, int length, String floorMaterial) {
        this.width = width;
        this.length = length;
        this.floorMaterial = floorMaterial;
    }

    public String getFloorMaterial() {
        return floorMaterial;
    }

    public void setFloorMaterial(String floorMaterial) {
        this.floorMaterial = floorMaterial;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
