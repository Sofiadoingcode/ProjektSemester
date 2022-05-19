package dat.startcode.model.entities;

public class Carport {
    private double height;
    private double length;
    private double width;
    private String roofMaterial;
    private String roofShape;
    private int roofAngle;
    private int idShed;


    public Carport(double height, double length, double width, String roofMaterial, String roofShape, int roofAngle, int idShed) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.roofMaterial = roofMaterial;
        this.roofShape = roofShape;
        this.roofAngle = roofAngle;
        this.idShed = idShed;
    }

    public String getRoofShape() {
        return roofShape;
    }

    public void setRoofShape(String roofShape) {
        this.roofShape = roofShape;
    }

    public String getRoofMaterial() {
        return roofMaterial;
    }

    public void setRoofMaterial(String roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public void setRoofAngle(int roofAngle) {
        this.roofAngle = roofAngle;
    }

    public int getIdShed() {
        return idShed;
    }

    public void setIdShed(int idShed) {
        this.idShed = idShed;
    }
}
