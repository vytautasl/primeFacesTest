/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.serviceimpl;

import lt.agmis.testproject.dao.PointDao;
import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;
import lt.agmis.testproject.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PointServiceImpl implements PointService {

    public static final String THE_POINT_ALREADY_EXISTS = "The point already exists";
    @Autowired
    private PointDao pointDao;

    @Override
    public List<SquareDto> getSquareList() {
        List<SquareDto> squareList = pointDao.getSquares();
        squareList = removeDuplicates(squareList);
        return squareList;
    }

    private List<SquareDto> removeDuplicates(List<SquareDto> squareList) {
        List<SquareDto> resultSquareList = new ArrayList<SquareDto>();
        for (SquareDto square:squareList)
        {
            if (!containsSquare(resultSquareList, square))
            {
                resultSquareList.add(square);
            }
        }
        return resultSquareList;
    }

    private boolean containsSquare(List<SquareDto> resultSquareList, SquareDto square) {
        boolean result = false;
        for (SquareDto squareElement:resultSquareList)
        {
            if (squaresEquals(squareElement, square))
            {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean squaresEquals(SquareDto square1, SquareDto square2) {
        return
                (pointsEquals(square1.getP1(), square2.getP1())||pointsEquals(square1.getP1(), square2.getP2())||pointsEquals(square1.getP1(), square2.getP3())||pointsEquals(square1.getP1(), square2.getP4()))&&
                (pointsEquals(square1.getP2(), square2.getP1())||pointsEquals(square1.getP2(), square2.getP2())||pointsEquals(square1.getP2(), square2.getP3())||pointsEquals(square1.getP2(), square2.getP4()))&&
                (pointsEquals(square1.getP3(), square2.getP1())||pointsEquals(square1.getP3(), square2.getP2())||pointsEquals(square1.getP3(), square2.getP3())||pointsEquals(square1.getP3(), square2.getP4()))&&
                (pointsEquals(square1.getP4(), square2.getP1())||pointsEquals(square1.getP4(), square2.getP2())||pointsEquals(square1.getP4(), square2.getP3())||pointsEquals(square1.getP4(), square2.getP4()))
                ;

    }

    private boolean pointsEquals(Point p1, Point p2) {
        return (p1.getLng()==p2.getLng())&&(p1.getLat()==p2.getLat())&&(p1.isInDataset()==p2.isInDataset());
    }

    private boolean duplicatedPoint(Point point) {
        List<Point> pointList = getPoints();
        for (Point pointElement:pointList)
        {
            if (pointsEquals(point, pointElement))
            {
                return true;
            }
        }
        return false;
    }



    @Override
    public Integer storePoint(Point point) throws Exception {
        if (!duplicatedPoint(point))
        {
            pointDao.storePoint(point);
        } else {
            throw new Exception(THE_POINT_ALREADY_EXISTS);
        }
        return point.getId();
    }

    @Override
    public Integer updatePoint(Point point) throws Exception {
        if (!duplicatedPoint(point))
        {
            return pointDao.updatePoint(point);
        } else {
            throw new Exception(THE_POINT_ALREADY_EXISTS);
        }
    }

    @Override
    public void deletePoint(Integer id) {
        Point point = pointDao.getPoint(id);
        pointDao.deletePoint(point);
    }

    @Override
    public Point getPoint(Integer id) {
        return pointDao.getPoint(id);
    }

    @Override
    public List<Point> getPoints() {
        return pointDao.getPoints();
    }

    @Override
    public Integer deleteAll() {
        return pointDao.deleteAll();
    }

    @Override
    public Integer loadDefault() {
        return pointDao.loadDefault();
    }

}
