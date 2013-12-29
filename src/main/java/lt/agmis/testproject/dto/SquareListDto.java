/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.dto;

import lt.agmis.testproject.domain.Point;

import java.util.List;

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
