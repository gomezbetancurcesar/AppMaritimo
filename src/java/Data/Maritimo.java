package Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Maritimo {
    private Long idCliente;
    private Date fechaTransporte;
    private Integer catidadTransporte;
    private String Origen;
    private String Destino;
    private Integer idTransporte;

    public Maritimo() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Date getFechaTransporte() {
        return fechaTransporte;
    }

    public Integer getCatidadTransporte() {
        return catidadTransporte;
    }

    public String getOrigen() {
        return Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public Integer getIdTransporte() {
        return idTransporte;
    }

    @XmlElement
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @XmlElement
    public void setFechaTransporte(Date fechaTransporte) {
        this.fechaTransporte = fechaTransporte;
    }

    @XmlElement
    public void setCatidadTransporte(Integer catidadTransporte) {
        this.catidadTransporte = catidadTransporte;
    }

    @XmlElement
    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    @XmlElement
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    @XmlElement
    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }
}
