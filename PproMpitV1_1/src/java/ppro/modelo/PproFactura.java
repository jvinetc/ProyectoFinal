/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author casa
 */
@Entity
@Table(name = "ppro_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PproFactura.findAll", query = "SELECT p FROM PproFactura p"),
    @NamedQuery(name = "PproFactura.findByFacId", query = "SELECT p FROM PproFactura p WHERE p.facId = :facId"),
    @NamedQuery(name = "PproFactura.findByFacMonto", query = "SELECT p FROM PproFactura p WHERE p.facMonto = :facMonto"),
    @NamedQuery(name = "PproFactura.findByFacIva", query = "SELECT p FROM PproFactura p WHERE p.facIva = :facIva"),
    @NamedQuery(name = "PproFactura.findByFacNeto", query = "SELECT p FROM PproFactura p WHERE p.facNeto = :facNeto"),
    @NamedQuery(name = "PproFactura.findByFacObsevacion", query = "SELECT p FROM PproFactura p WHERE p.facObsevacion = :facObsevacion"),
    @NamedQuery(name = "PproFactura.findByFacDocId", query = "SELECT p FROM PproFactura p WHERE p.facDocId = :facDocId")})
@ManagedBean
@SessionScoped
public class PproFactura implements Serializable {

    
    @Column(name = "fac_fecha")
    @Temporal(TemporalType.DATE)
    private Date facFecha;

   

   

    @JoinColumn(name = "fac_doc_id", referencedColumnName = "doc_id")
    @ManyToOne
    private PproDocumento facDocId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fac_id")
    private Integer facId;
    @Column(name = "fac_monto")
    private Integer facMonto;
    @Column(name = "fac_iva")
    private Integer facIva;
    @Column(name = "fac_neto")
    private Integer facNeto;
    @Size(max = 255)
    @Column(name = "fac_obsevacion")
    private String facObsevacion;
    

    public PproFactura() {
    }

    public PproFactura(Integer facId) {
        this.facId = facId;
    }

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }

    public Integer getFacMonto() {
        return facMonto;
    }

    public void setFacMonto(Integer facMonto) {
        this.facMonto = facMonto;
    }

    public Integer getFacIva() {
        return facIva;
    }

    public void setFacIva(Integer facIva) {
        this.facIva = facIva;
    }

    public Integer getFacNeto() {
        return facNeto;
    }

    public void setFacNeto(Integer facNeto) {
        this.facNeto = facNeto;
    }

    public String getFacObsevacion() {
        return facObsevacion;
    }

    public void setFacObsevacion(String facObsevacion) {
        this.facObsevacion = facObsevacion;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facId != null ? facId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PproFactura)) {
            return false;
        }
        PproFactura other = (PproFactura) object;
        if ((this.facId == null && other.facId != null) || (this.facId != null && !this.facId.equals(other.facId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ppro.modelo.PproFactura[ facId=" + facId + " ]";
    }

    public PproDocumento getFacDocId() {
        return facDocId;
    }

    public void setFacDocId(PproDocumento facDocId) {
        this.facDocId = facDocId;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }



    
}
