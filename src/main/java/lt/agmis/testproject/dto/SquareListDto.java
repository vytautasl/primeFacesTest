package lt.agmis.testproject.dto;

import lt.agmis.testproject.domain.Point;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: VytautasL
 * Date: 12/25/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SquareListDto {
    List<SquareDto> squareList;
    List<Point> pointList;

    public List<SquareDto> getSquareList() {
        return squareList;
    }

    public void setSquareList(List<SquareDto> squareList) {
        this.squareList = squareList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

}
