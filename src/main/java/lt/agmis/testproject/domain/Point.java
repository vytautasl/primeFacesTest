package lt.agmis.testproject.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pt")
public class Point {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false, unique=false)
    private double lng;

    @Column(nullable=false, unique=false)
    private double lat;

    @Column(nullable=true, unique=false)
    private boolean inDataset;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean isInDataset() {
        return inDataset;
    }

    public void setInDataset(boolean inDataset) {
        this.inDataset = inDataset;
    }
}
