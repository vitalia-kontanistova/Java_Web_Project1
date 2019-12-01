package by.epam.ellipse.entity;

public class Parameters {

    private Ellipse ellipse;
    private double area;
    private double perimeter;
    private boolean circle;
    private boolean crossingX;
    private boolean crossingY;


    public Parameters(Ellipse ellipse) {
        setEllipse(ellipse);
    }

    public Parameters() {
        this(new Ellipse());
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public void setEllipse(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public boolean isCircle() {
        return circle;
    }

    public boolean isCrossingX() {
        return crossingX;
    }

    public boolean isCrossingY() {
        return crossingY;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    public void setCrossingX(boolean crossingX) {
        this.crossingX = crossingX;
    }

    public void setCrossingY(boolean crossingY) {
        this.crossingY = crossingY;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "ellipse=" + ellipse +
                ", area=" + area +
                ", perimeter=" + perimeter +
                ", circle=" + circle +
                ", crossingX=" + crossingX +
                ", crossingY=" + crossingY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Double.compare(that.area, area) == 0 &&
                Double.compare(that.perimeter, perimeter) == 0 &&
                ellipse.equals(that.ellipse) &&
                circle == that.circle &&
                crossingX == that.crossingX &&
                crossingY == that.crossingY;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (int) Double.doubleToLongBits(area);
        result = result * 31 + (int) Double.doubleToLongBits(perimeter);
        result = result * 31 + ellipse.hashCode();
        result = result * 31 + ((circle) ? 1 : 0);
        result = result * 31 + ((crossingX) ? 1 : 0);
        result = result * 31 + ((crossingY) ? 1 : 0);
        return result;
    }
}