package by.epam.ellipse.entity;

public class Ellipse implements Cloneable{

    private int id;
    private Point pointA;
    private Point pointB;
    private double area;
    private double perimeter;
    private boolean circle;
    private boolean crossingX;
    private boolean crossingY;


    public Ellipse(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Ellipse() {
        this(new Point(-1.0, -1.0), new Point(1.0, 1.0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
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
        return "Ellipse{" +
                "id=" + id +
                ", pointA=" + pointA +
                ", pointB=" + pointB +
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
        Ellipse that = (Ellipse) o;
        return id == that.id &&
                pointA.equals(that.pointA) &&
                pointB.equals(that.pointB) &&
                Double.compare(that.area, area) == 0 &&
                Double.compare(that.perimeter, perimeter) == 0 &&
                circle == that.circle &&
                crossingX == that.crossingX &&
                crossingY == that.crossingY;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = result * 31 + pointA.hashCode();
        result = result * 31 + pointB.hashCode();
        result = result * 31 + (int) Double.doubleToLongBits(area);
        result = result * 31 + (int) Double.doubleToLongBits(perimeter);
        result = result * 31 + ((circle) ? 1 : 0);
        result = result * 31 + ((crossingX) ? 1 : 0);
        result = result * 31 + ((crossingY) ? 1 : 0);

        return result;
    }

    @Override
    public Ellipse clone() throws CloneNotSupportedException {
        Ellipse newEllipse = (Ellipse) super.clone();
        newEllipse.setPointA(pointA.clone());
        newEllipse.setPointB(pointB.clone());

        return newEllipse;
    }
}