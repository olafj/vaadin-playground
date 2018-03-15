package de.olafj.vaadinplayground.grid;

public class GridCoords {

    private int x1;
    private int x2;
    private int y1;
    private int y2;


    public GridCoords(int x1, int y1) {
        this.x1 = x1;
        this.x2 = x1;
        this.y1 = y1;
        this.y2 = y1;
    }

    public GridCoords(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2;
    }

    public boolean startsOnX(int x) {
        return x1 == x;
    }

    public boolean endsOnX(int x) {
        return x2 == x;
    }

    public boolean startsOnY(int y) {
        return y1 == y;
    }

    public boolean endsOnY(int y) {
        return y2 == y;
    }

    public boolean isInRowOf(GridCoords other) {
        return !notInRowOf(other);
    }

    public boolean notInRowOf(GridCoords other) {
        return (other.getY2() < y1) || (other.getY1() > y2);
    }

    public boolean sameRightAlignment(GridCoords other) {
        return other.endsOnX(x2);
    }

    public boolean sameLeftAlignment(GridCoords other) {
        return other.startsOnX(x1);
    }

    public int colSpan() {
        return x2 - x1 + 1;
    }

    public int rowSpan() {
        return y2 - y1 + 1;
    }

    public boolean isRightNeighborOf(GridCoords other) {
        return isInRowOf(other) && other.endsOnX(x1-1);
    }

    public boolean isLeftNeighborOf(GridCoords other) {
        return isInRowOf(other) && other.startsOnX(x2+1);
    }
}
