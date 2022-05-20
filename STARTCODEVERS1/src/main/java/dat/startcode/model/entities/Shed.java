package dat.startcode.model.entities;

public class Shed {
    private double width;
    private double length;
    private String floorMaterial;

    public Shed(double width, double length, String floorMaterial) {
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

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
