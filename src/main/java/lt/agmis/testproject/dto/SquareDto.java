package lt.agmis.testproject.dto;

import lt.agmis.testproject.domain.Point;

/**
 * Created with IntelliJ IDEA.
 * User: VytautasL
 * Date: 12/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SquareDto {
    Point p1;
    Point p2;
    int squareNumber;

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public int getSquareNumber() {
        return squareNumber;
    }

    public void setSquareNumber(int squareNumber) {
        this.squareNumber = squareNumber;
    }
}
