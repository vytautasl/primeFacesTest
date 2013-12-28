package lt.agmis.testproject.service;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ignas
 * Date: 10/3/13
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PointService {
    List<SquareDto> getSquareList();
    Integer storePoint(Point point) throws Exception;
    Integer updatePoint(Point point) throws Exception;
    void deletePoint(Integer id);
    Point getPoint(Integer id);
    List<Point> getPoints();
    Integer deleteAll();
    Integer loadDefault();
}
