/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.restcontroller;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.CreateResult;
import lt.agmis.testproject.dto.SquareListDto;
import lt.agmis.testproject.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = {"/points"})
@Controller 
public class PointController {

    public static final String THE_POINT_HAS_BEEN_UPDATED = "The point has been updated";
    public static final String THE_POINT_HAS_NOT_BEEN_UPDATED = "The point has not been updated";
    public static final String THE_POINT_WAS_CREATED = "The point was created";
    public static final String THE_POINT_WAS_NOT_CREATED = "The point was not created";
    public static final String THE_POINT_WAS_DELETED = "The point was deleted";
    public static final String THE_POINT_WAS_NOT_DELETED = "The point was not deleted";
    public static final String POINTS_FOUND_IN_DATABASE = " points found in database";
    public static final String ALL_THE_POINTS_WERE_DELETED = "All the points were deleted";
    public static final String ERROR_DURING_POINTS_DELETING = "Error during points deleting";
    public static final String ALL_THE_POINTS_WERE_LOADED_FROM_BACKUP = "All the points were loaded from backup";
    public static final String ERROR_LOADING_POINTS = "Error loading points";

    @Autowired
    private PointService pointService;

    /**
     * Gets from database all squares and points
     *
     * @return Square List
     */
	@RequestMapping(produces = {"application/json"}, method = RequestMethod.GET)
	@ResponseBody
	public SquareListDto getSquaresAndPoints() {
        SquareListDto squareListDto = new SquareListDto();
        squareListDto.setSquareList(pointService.getSquareList());
        squareListDto.setPointList(pointService.getPoints());
		return squareListDto;
    }

    /**
     * Updates posted point coordinates and isInDataset flag
     *
     * @param point point to update
     * @return result object with response description and success flag
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CreateResult updatePoint(@RequestBody Point point) {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            pointId = pointService.updatePoint(point);
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
        }
        if (pointId!=null)
        {
            result.setSuccess(true);
            result.setDescription(THE_POINT_HAS_BEEN_UPDATED);
        } else {
            result.setSuccess(false);
            result.setDescription(THE_POINT_HAS_NOT_BEEN_UPDATED);
        }
        result.setId(pointId);
        return result;
    }

    /**
     *  adds posted point to database
     * @param point point to be added to database
     * @return result object with response description and success flag
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CreateResult addPoint(@RequestBody Point point) {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            pointId = pointService.storePoint(point);
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
        }
        if (pointId!=null)
        {
            result.setSuccess(true);
            result.setDescription(THE_POINT_WAS_CREATED);
        } else {
            result.setSuccess(false);
            result.setDescription(THE_POINT_WAS_NOT_CREATED);
        }
        result.setId(pointId);
        return result;
    }

    /**
     * Deletes point with given id from database
     * @param id identifier of point to be deleted
     * @return result object with response description and success flag
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deletePoint(@PathVariable Integer id) {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            pointService.deletePoint(id);
            result.setSuccess(true);
            result.setDescription(THE_POINT_WAS_DELETED);
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription(THE_POINT_WAS_NOT_DELETED);
        }
        result.setId(pointId);
        return result;
    }

    /**
     * Deletes all points from database
     * @return result object with response description and success flag
     */
    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deleteAll() {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            Integer deletedCount = pointService.deleteAll();
            result.setSuccess(true);
            result.setDescription(deletedCount + POINTS_FOUND_IN_DATABASE);
            result.setDetailedDescription(ALL_THE_POINTS_WERE_DELETED);
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription(ERROR_DURING_POINTS_DELETING);
        }
        result.setId(pointId);
        return result;
    }

    /**
     * Loads default dataset from backup
     * @return result object with response description and success flag
     */
    @RequestMapping(value = "/loadDefault", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult loadDefault() {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            Integer loadedCount = pointService.loadDefault();
            result.setSuccess(true);
            result.setDescription(loadedCount + POINTS_FOUND_IN_DATABASE);
            result.setDetailedDescription(ALL_THE_POINTS_WERE_LOADED_FROM_BACKUP);
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription(ERROR_LOADING_POINTS);
        }
        result.setId(pointId);
        return result;
    }
}
