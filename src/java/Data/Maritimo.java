package Data;

import java.util.Date;

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

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public void setFechaTransporte(Date fechaTransporte) {
        this.fechaTransporte = fechaTransporte;
    }

    public void setCatidadTransporte(Integer catidadTransporte) {
        this.catidadTransporte = catidadTransporte;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }
    
    
}
