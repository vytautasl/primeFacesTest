package lt.agmis.testproject.serviceimpl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import lt.agmis.testproject.dao.PointDao;
import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;
import lt.agmis.testproject.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ignas
 * Date: 10/3/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PointServiceImpl implements PointService {

    @Autowired
    private PointDao pointDao;

    @Override
    public List<SquareDto> getSquareList() {
        return pointDao.getSquares();
    }

    @Override
    public int storePoint(Point point) {
        pointDao.storePoint(point);
        return point.getId();
    }

    @Override
    public int updatePoint(Point point) {
        return pointDao.updatePoint(point);
    }

    @Override
    public void deletePoint(int id) {
        Point point = pointDao.getPoint(id);
        pointDao.deletePoint(point);
    }

    @Override
    public Point getPoint(int id) {
        return pointDao.getPoint(id);
    }

    @Override
    public List<Point> getPoints() {
        return pointDao.getPoints();
    }

}
