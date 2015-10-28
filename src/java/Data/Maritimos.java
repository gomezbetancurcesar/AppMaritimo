package Data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "maritimos")
@XmlAccessorType (XmlAccessType.FIELD)
public class Maritimos{
    @XmlElement(name = "maritimo")
    private List<Maritimo> maritimos = null;
 
    public List<Maritimo> getEmployees() {
        return maritimos;
    }
 
    public void setEmployees(List<Maritimo> maritimos) {
        this.maritimos = maritimos;
    }
}
