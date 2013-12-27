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
        pointService.updatePoint(point);
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CreateResult addPoint(@RequestBody Point point) {
        pointService.storePoint(point);
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deletePoint(@PathVariable int id) {
        pointService.deletePoint(id);
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult deleteAll() {
        pointService.deleteAll();
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/loadDefault", method = RequestMethod.GET)
    @ResponseBody
    public CreateResult loadDefault() {
        pointService.loadDefault();
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }
}
