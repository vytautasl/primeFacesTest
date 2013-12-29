/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.daohibernateimpl;

import lt.agmis.testproject.dao.PointDao;
import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PointDaoImpl implements PointDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    /**
     * {@inheritDoc}
     */
    public List<Point> getPoints() {
        List<Point> result = (List<Point>) sessionFactory.getCurrentSession()
                .createCriteria(Point.class)
                .list();
        return result;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Point getPoint(Integer id) {
        Point result = (Point) sessionFactory.getCurrentSession()
                .createCriteria(Point.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return result;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer storePoint(Point point) {
        sessionFactory.getCurrentSession().save(point);
        return point.getId();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer updatePoint(Point point) {
        Point dbPoint = getPoint(point.getId());
        dbPoint.setLat(point.getLat());
        dbPoint.setLng(point.getLng());
        dbPoint.setInDataset(point.isInDataset());
        sessionFactory.getCurrentSession().merge(point);
        return point.getId();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public void deletePoint(Point point) {
        sessionFactory.getCurrentSession().delete(point);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public List<SquareDto> getSquares() {
        List<SquareDto> squareDtoList = new ArrayList<SquareDto>();
        String sql =
                " SELECT p1.id as firstCorner, p2.id as secondCorner, p3.id as thirdCorner, p4.id as fourthCorner" +
                " FROM pt p1, pt p2, pt p3, pt p4" +
                " WHERE " +
                " pow(p1.lng-p2.lng, 2) + pow(p1.lat-p2.lat, 2) + pow(p2.lng-p3.lng, 2) + pow(p2.lat-p3.lat, 2) = pow(p1.lng-p3.lng, 2) + pow(p1.lat-p3.lat, 2)" +
                " AND pow(p2.lng-p3.lng, 2) + pow(p2.lat-p3.lat, 2) + pow(p3.lng-p4.lng, 2) + pow(p3.lat-p4.lat, 2) = pow(p2.lng-p4.lng, 2) + pow(p2.lat-p4.lat, 2)" +
                " AND p1.id <> p2.id" +
                " AND p2.id <> p3.id" +
                " AND p3.id <> p4.id" +
                " AND p4.id <> p1.id" +
                " AND p1.inDataset = 1" +
                " AND p2.inDataset = 1" +
                " AND p3.inDataset = 1" +
                " AND p4.inDataset = 1" +
                " AND pow(p4.lng-p1.lng, 2) + pow(p4.lat-p1.lat, 2)=pow(p1.lng-p2.lng, 2) + pow(p1.lat-p2.lat, 2)"
                ;
        List<Map> response = (List<Map>)sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        for (int i=0; i<response.size(); i++)
        {
            SquareDto squareDto = new SquareDto();
            Map idMap = response.get(i);
            Point p1 = getPoint((Integer)idMap.get("firstCorner"));
            Point p2 = getPoint((Integer)idMap.get("secondCorner"));
            Point p3 = getPoint((Integer)idMap.get("thirdCorner"));
            Point p4 = getPoint((Integer)idMap.get("fourthCorner"));
            squareDto.setP1(p1);
            squareDto.setP2(p2);
            squareDto.setP3(p3);
            squareDto.setP4(p4);
            squareDto.setSquareNumber(i);
            squareDtoList.add(squareDto);
        }

        return squareDtoList;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer deleteAll() {
        return sessionFactory.getCurrentSession().createSQLQuery("delete from pt").executeUpdate();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer loadDefault() {
        deleteAll();
        return sessionFactory.getCurrentSession().createSQLQuery("insert into pt (select * from pt_backup)").executeUpdate();
    }


}
