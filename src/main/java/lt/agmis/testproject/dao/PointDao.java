package lt.agmis.testproject.dao;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ignas
 * Date: 10/3/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PointDao {
    List<Point> getPoints();
    Point getPoint(Integer id);
    Integer storePoint(Point point);
    Integer updatePoint(Point point);
    void deletePoint(Point point);
    List<SquareDto> getSquares();
    Integer deleteAll();
    Integer loadDefault();
}
