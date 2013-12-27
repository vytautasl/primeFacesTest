package lt.agmis.testproject.faces;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.CreateResult;
import lt.agmis.testproject.dto.SquareDto;
import lt.agmis.testproject.dto.SquareListDto;
import lt.agmis.testproject.faces.utils.CallUtils;
import org.primefaces.event.RowEditEvent;
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
    private double newLat;
    private double newLng;

    @PostConstruct
    public void init()
    {
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context");
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

    public void updateSelection(RowEditEvent event)
    {
        Point point = (Point)event.getObject();
        CallUtils.postCall("http://localhost:8080/api/points/update", point, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
    }

    public void deletePoint(int id)
    {
        CallUtils.getCall("http://localhost:8080/api/points/delete/" + id, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
    }

    public void addPoint()
    {
        Point point = new Point();
        point.setInDataset(true);
        point.setLat(getNewLat());
        point.setLng(getNewLng());
        CallUtils.postCall("http://localhost:8080/api/points/add", point, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
    }

    public void deleteAll()
    {
        CallUtils.getCall("http://localhost:8080/api/points/deleteAll", CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
    }

    public void loadDefault()
    {
        CallUtils.getCall("http://localhost:8080/api/points/loadDefault", CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall("http://localhost:8080/api/points/", SquareListDto.class, new HashMap());
    }

    public Integer getSelectedPointId() {
        return selectedPointId;
    }

    public void setSelectedPointId(Integer selectedPointId) {
        this.selectedPointId = selectedPointId;
    }

    public double getNewLat() {
        return newLat;
    }

    public void setNewLat(double newLat) {
        this.newLat = newLat;
    }

    public double getNewLng() {
        return newLng;
    }

    public void setNewLng(double newLng) {
        this.newLng = newLng;
    }
}
