package lt.agmis.testproject.faces;

import lt.agmis.testproject.domain.Point;
import lt.agmis.testproject.dto.CreateResult;
import lt.agmis.testproject.dto.SquareDto;
import lt.agmis.testproject.dto.SquareListDto;
import lt.agmis.testproject.faces.utils.CallUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ManagedBean
@SessionScoped
public class PointBean {
    public static final String REST_PATH = "/api/points/";
    public static final String UPDATE_CALL = "update";
    public static final String DELETE_CALL = "delete/";
    public static final String ADD_CALL = "add";
    public static final String DELETE_ALL_CALL = "deleteAll";
    public static final String LOAD_DEFAULT_CALL = "loadDefault";
    SquareListDto squareListDto;
    SquareDto selectedSquare;
    Point selectedPoint;
    Integer selectedPointId;
    private double newLat;
    private double newLng;
    private String contextPath;
    private String deployServer;
    private String callPath;

    private void addMessage(CreateResult result)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        if (!result.isSuccess())
        {
            severity = FacesMessage.SEVERITY_ERROR;
        }
        if (result.getDetailedDescription()==null)
        {
            result.setDetailedDescription("");
        }
        context.addMessage(null, new FacesMessage(severity, result.getDescription(), result.getDetailedDescription()));
    }

    private String getContextPath() {
        return contextPath;
    }

    private void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getDeployServer() {
        return deployServer;
    }

    public void setDeployServer(String deployServer) {
        this.deployServer = deployServer;
    }

    public String getCallPath()
    {
        return callPath;
    }

    public void setCallPath(String callPath) {
        this.callPath = callPath;
    }

    @PostConstruct
    public void init()
    {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        setContextPath(request.getContextPath());
        int replaceIndex = request.getRequestURL().indexOf(request.getRequestURI());
        setDeployServer(request.getRequestURL().replace(replaceIndex, replaceIndex + request.getRequestURL().length(), "").toString());
        setCallPath(getDeployServer() + getContextPath() + REST_PATH);
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
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
        CreateResult result = (CreateResult)CallUtils.postCall(getCallPath()+ UPDATE_CALL, point, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
        if (result.isSuccess()) result.setDetailedDescription(squareListDto.getSquareList().size()+" squares formed");
        addMessage(result);
    }

    public void deletePoint(int id)
    {
        CreateResult result = (CreateResult)CallUtils.getCall(getCallPath()+ DELETE_CALL + id, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
        addMessage(result);
    }

    public void addPoint()
    {
        Point point = new Point();
        point.setInDataset(true);
        point.setLat(getNewLat());
        point.setLng(getNewLng());
        CreateResult result = (CreateResult)CallUtils.postCall(getCallPath() + ADD_CALL, point, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
        if (result.isSuccess()) result.setDetailedDescription(squareListDto.getSquareList().size()+" squares formed");
        addMessage(result);
    }

    public void deleteAll()
    {
        CreateResult result = (CreateResult)CallUtils.getCall(getCallPath()+ DELETE_ALL_CALL, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
        addMessage(result);
    }

    public void loadDefault()
    {
        CreateResult result = (CreateResult)CallUtils.getCall(getCallPath()+ LOAD_DEFAULT_CALL, CreateResult.class, new HashMap());
        squareListDto = (SquareListDto)CallUtils.getCall(getCallPath(), SquareListDto.class, new HashMap());
        addMessage(result);
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
