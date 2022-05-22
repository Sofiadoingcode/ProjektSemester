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

    private final String rectTemplate = "<rect x=\"%d\" " +
            "y=\"%d\" " +
            "height=\"%d\" " +
            "width=\"%d\"\n" +
            "style=\"stroke:#000000; " +
            "fill: #ffffff\"/>";

    private final String rectTemplateStolpe = "<rect x=\"%d\" " +
            "y=\"%d\" " +
            "height=\"%d\" " +
            "width=\"%d\"\n" +
            "transform=\"translate(-%d, -%d)\" " +
            "style=\"stroke:#000000; " +
            "fill: #ffffff\"/>";

    private final String dottedLineTemplate = "<line x1=\"%d\" " +
            "y1=\"%d\" " +
            "x2=\"%d\" " +
            "y2=\"%d\" " +
            "stroke=\"black\" " +
            "stroke-dasharray=\"%d\"/>";

    private final String textTemplate = "<text style=\"text-anchor: middle ; " +
            "font-size: %frem\" " +
            "transform=\"translate(%d,%d) " +
            "rotate(%d)\">%s</text>";

    private final String arrowTemplate = " <defs>\n" +
            "    <marker\n" +
            "    id=\"beginArrow\"\n" +
            "    markerWidth=\"12\"\n" +
            "    markerHeight=\"12\"\n" +
            "    refX=\"0\"\n" +
            "    refY=\"6\"\n" +
            "    orient=\"auto\">\n" +
            "      <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "    </marker>\n" +
            "    <marker\n" +
            "    id=\"endArrow\"\n" +
            "    markerWidth=\"12\"\n" +
            "    markerHeight=\"12\"\n" +
            "    refX=\"12\"\n" +
            "    refY=\"6\"\n" +
            "    orient=\"auto\">\n" +
            "      <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "    </marker>\n" +
            "  </defs>";

    private final String lineTemplate = "<line x1=\"%d\"  y1=\"%d\" x2=\"%d\"   y2=\"%d\"\n" +
            "    style=\"stroke: #000000;\n" +
            "    marker-start: %s\n" +
            "    marker-end: %s\"/>";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(int x, int y, int height, int width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addRectStolpe(int x, int y, int height, int width){
        svg.append(String.format(rectTemplateStolpe, x, y, height, width, (height/3), width/3   ));
    }

    public void addDottedLine(int x1, int y1, int x2, int y2, int dashArray){
        svg.append(String.format(dottedLineTemplate, x1, y1, x2, y2, dashArray));
    }

    public void addLine(int x1, int y1, int x2, int y2, String markerStart, String markerEnd){
        svg.append(arrowTemplate);
        svg.append(String.format(lineTemplate, x1, y1, x2, y2, markerStart, markerEnd));
    }

    public void addText(double fontSize, int transform1, int transform2, int rotate, String length){
        svg.append(String.format(textTemplate, fontSize, transform1, transform2, rotate, length));
    }

    public void addSvg(SVG innerSVG){
        svg.append(innerSVG.toString());
    }

    public String toString(){
        return svg.toString() + "</svg>";
    }
}
