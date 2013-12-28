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

    @Autowired
    private PointService pointService;

	@RequestMapping(produces = {"application/json"}, method = RequestMethod.GET)
	@ResponseBody
	public SquareListDto getSquaresAndPoints() {
        SquareListDto squareListDto = new SquareListDto();
        squareListDto.setSquareList(pointService.getSquareList());
        squareListDto.setPointList(pointService.getPoints());
		return squareListDto;
    }

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
            result.setDescription("The point has been updated");
        } else {
            result.setSuccess(false);
            result.setDescription("The point has not been updated");
        }
        result.setId(pointId);
        return result;
    }

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
            result.setDescription("The point was created");
        } else {
            result.setSuccess(false);
            result.setDescription("The point was not created");
        }
        result.setId(pointId);
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deletePoint(@PathVariable Integer id) {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            pointService.deletePoint(id);
            result.setSuccess(true);
            result.setDescription("The point was deleted");
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription("The point was not deleted");
        }
        result.setId(pointId);
        return result;
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deleteAll() {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            Integer deletedCount = pointService.deleteAll();
            result.setSuccess(true);
            result.setDescription(deletedCount + " points found in database");
            result.setDetailedDescription("All the points was deleted");
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription("Error during points deleting");
        }
        result.setId(pointId);
        return result;
    }

    @RequestMapping(value = "/loadDefault", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult loadDefault() {
        CreateResult result = new CreateResult();
        Integer pointId=null;
        try
        {
            Integer loadedCount = pointService.loadDefault();
            result.setSuccess(true);
            result.setDescription(loadedCount + " points found in database");
            result.setDetailedDescription("All the points was loaded from backup");
        } catch (Exception e)
        {
            result.setDetailedDescription(e.getMessage());
            result.setSuccess(false);
            result.setDescription("Error loading points");
        }
        result.setId(pointId);
        return result;
    }
}
