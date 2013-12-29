/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.dao;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;

import java.util.List;

public interface PointDao {

    /**
     * Collects all points from database
     *
     * @return point list, or null if list is empty
     */
    List<Point> getPoints();

    /**
     * Finds point by identifier
     *
     * @param id Point identifier
     * @return found Point object
     */
    Point getPoint(Integer id);

    /**
     * Stores the point object and returns identifier, which this object gets
     *
     * @param point Point object to be stored
     * @return stored points identifier
     */
    Integer storePoint(Point point);

    /**
     * Updates point object's properties
     *
     * @param point Point object to be updated
     * @return identifier of the updated object
     */
    Integer updatePoint(Point point);

    /**
     * Deletes point from database
     *
     * @param point Point object to delete
     */
    void deletePoint(Point point);

    /**
     * Finds square list from all the points in database
     *
     * @return square list
     */
    List<SquareDto> getSquares();

    /**
     * Deletes all points in the database
     *
     * @return deleted points count
     */
    Integer deleteAll();

    /**
     * Loads default point set from the backup table. Deletes old points before loading
     *
     * @return loaded points count
     */
    Integer loadDefault();
}
