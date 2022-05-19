package dat.startcode.model.services;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private double width;
    private double height;

    private final String headerTemplate = "<svg height=\"%s%%\" " +
            "width=\"%s%%\" " +
            "viewBox=\"%s\"" +
            "x=\"%s\"" +
            "y=\"%s\"" +
            "preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\"\n" +
            "          style=\"stroke:#000000; fill: #ffffff\"/>";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(int x, int y, double height, double width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addSvg(SVG innerSVG){
        svg.append(innerSVG.toString());
    }

    public String toString(){
        return svg.toString() + "</svg>";
    }
}
