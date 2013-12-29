/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.service;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.SquareDto;

import java.util.List;

public interface PointService {
    /**
     * Generates list of squares from points in database
     * @return List of calculated squares
     */
    List<SquareDto> getSquareList();

    /**
     * Stores the point to database and gives him the autoincremental identifier
     * @param point point to store
     * @return Identifier of the stored point
     * @throws Exception to identify the point duplication state
     */
    Integer storePoint(Point point) throws Exception;

    /**
     * Updates the point's coordinates and isInDataset flag
     * @param point point to update
     * @return Identifier of the updated point
     * @throws Exception to identify the point duplication state
     */
    Integer updatePoint(Point point) throws Exception;

    /**
     * Deletes the point from database
     * @param id identifier of point to delete
     */
    void deletePoint(Integer id);

    /**
     * Gets single point by its identifier
     * @param id identifier of point to find
     * @return Point object
     */
    Point getPoint(Integer id);

    /**
     * Gets all points from the working set table
     * @return List of points
     */
    List<Point> getPoints();

    /**
     * Deletes all points from the working set table
     * @return count of deleted points
     */
    Integer deleteAll();

    /**
     * Loads all points from backup table to main table. Old points in working table are deleted
     * @return count of loaded points
     */
    Integer loadDefault();
}
