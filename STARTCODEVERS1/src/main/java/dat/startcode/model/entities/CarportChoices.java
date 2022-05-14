package dat.startcode.model.entities;

public class CarportChoices {
    private double height;
    private double width;
    private double length;
//    private boolean wantsShed;
//    private double roofAngle;

    public CarportChoices (double height, double width, double length) {
        this.height = height;
        this.width = width;
//        this.length = length;
//        this.wantsShed = wantsShed;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

//    public boolean isWantsShed() {
//        return wantsShed;
//    }
//
//    public void setWantsShed(boolean wantsShed) {
//        this.wantsShed = wantsShed;
//    }
//
//    public double getRoofAngle() {
//        return roofAngle;
//    }
//
//    public void setRoofAngle(double roofAngle) {
//        this.roofAngle = roofAngle;
//    }
}
