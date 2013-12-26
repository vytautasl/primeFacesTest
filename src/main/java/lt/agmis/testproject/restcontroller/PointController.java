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

    @RequestMapping(produces = {"application/json"},  method = RequestMethod.POST)
    @ResponseBody
    public CreateResult addPoint(@RequestBody Point point) {
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        result.setId(pointService.storePoint(point));
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CreateResult updatePoint(@RequestBody Point point) {
        pointService.updatePoint(point);
        CreateResult result = new CreateResult();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deletePoint(@PathVariable("id") int id) {
        pointService.deletePoint(id);
    }
}
