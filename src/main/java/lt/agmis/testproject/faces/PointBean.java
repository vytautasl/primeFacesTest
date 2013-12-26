package lt.agmis.testproject.faces;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.CreateResult;
import lt.agmis.testproject.dto.SquareDto;
import lt.agmis.testproject.dto.SquareListDto;
import lt.agmis.testproject.faces.utils.CallUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ManagedBean
@SessionScoped
public class PointBean {
    SquareListDto squareListDto;
    SquareDto selectedSquare;
    Point selectedPoint;
    Integer selectedPointId;

    @PostConstruct
    public void init()
    {
        //if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest())
        {
            squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
/*
            List<Point> pointList = new ArrayList<Point>();
            for (Point point:squareListDto.getPointList())
            {
                if (point.isInDataset())
                {
                    pointList.add(point);
                }
            }
            selectedPoints = pointList.toArray(new Point[pointList.size()]);
*/
        }
    }

    public SquareListDto getSquareListDto() {
        return squareListDto;
    }

    public void setSquareListDto(SquareListDto squareListDto) {
        this.squareListDto = squareListDto;
    }

    public SquareDto getSelectedSquare() {
        return selectedSquare;
    }

    public void setSelectedSquare(SquareDto selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public Point getSelectedPoint() {
        return selectedPoint;
    }

    public void setSelectedPoint(Point selectedPoint) {
        this.selectedPoint = selectedPoint;
    }

    public void updateSelection()
    {
        selectedPoint.setInDataset(!selectedPoint.isInDataset());
        CallUtils.postCall("http://localhost:8080/api/points/update", selectedPoint, CreateResult.class, new HashMap());
    }

    public Integer getSelectedPointId() {
        return selectedPointId;
    }

    public void setSelectedPointId(Integer selectedPointId) {
        this.selectedPointId = selectedPointId;
    }

    /*
    private void updateSelection(Point[] newSelectedPoints, Point[] oldSelectedPoints) {
        for (int i=0; i<newSelectedPoints.length; i++)
        {
            for (int j=0; j<oldSelectedPoints.length; j++)
            {
                if (newSelectedPoints[i].getId().equals(oldSelectedPoints[j].getId()))
                {
                    if (newSelectedPoints[i].isInDataset()!=oldSelectedPoints[j].isInDataset())
                    {
                        CallUtils.postCall("http://localhost:8080/api/points/updatePoint/"+newSelectedPoints[i].getId(), newSelectedPoints[i], CreateResult.class, new HashMap());
                    }
                    break;
                }
            }
        }
    }
*/
}
