/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ApplicationScoped;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author casa
 */
@Entity
@Table(name = "ppro_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PproProveedor.findAll", query = "SELECT p FROM PproProveedor p"),
    @NamedQuery(name = "PproProveedor.findByProvId", query = "SELECT p FROM PproProveedor p WHERE p.provId = :provId"),
    @NamedQuery(name = "PproProveedor.findByProvRazonSocial", query = "SELECT p FROM PproProveedor p WHERE p.provRazonSocial = :provRazonSocial"),
    @NamedQuery(name = "PproProveedor.findByProvEstado", query = "SELECT p FROM PproProveedor p WHERE p.provEstado = :provEstado"),
    @NamedQuery(name = "PproProveedor.findByRutProv", query = "SELECT p FROM PproProveedor p INNER JOIN p.provPerId pper WHERE pper.perRut = :rutProv"),
    @NamedQuery(name = "PproProveedor.findByRutParecido", query = "SELECT p FROM PproProveedor p INNER JOIN p.provPerId pper WHERE pper.perRut LIKE :rutProv"),
    @NamedQuery(name = "PproProveedor.findByRutCompleto", query = "SELECT p FROM PproProveedor p INNER JOIN p.provPerId pper WHERE pper.perRutComp = :rutCompleto")})
@ManagedBean
@SessionScoped
public class PproProveedor implements Serializable {

    @Size(max = 255)
    @Column(name = "prov_email")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String provEmail;

    @JoinColumn(name = "prov_ent_financiera", referencedColumnName = "ent_fin_id")
    @ManyToOne
    private PproEntidadFinanciera provEntFinanciera;

    @Size(max = 255)
    @Column(name = "prov_n_cuenta")
    private String provNCuenta;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prov_id")
    private Integer provId;
    @Size(max = 255)
    @Column(name = "prov_razon_social")
    private String provRazonSocial;
    @Column(name = "prov_estado")
    private Integer provEstado;
    @OneToMany(mappedBy = "docProvId")
    private Collection<PproDocumento> pproDocumentoCollection;
    @JoinColumn(name = "prov_per_id", referencedColumnName = "per_id")
    @ManyToOne
    private PproPersona provPerId;
    
    @JoinColumn(name = "prov_tipro_id", referencedColumnName = "tipro_id")
    @ManyToOne
    private PproTipoProveedor provTiproId;

    public PproProveedor() {
    }

    public PproProveedor(Integer provId) {
        this.provId = provId;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProvRazonSocial() {
        return provRazonSocial;
    }

    public void setProvRazonSocial(String provRazonSocial) {
        this.provRazonSocial = provRazonSocial;
    }

    public Integer getProvEstado() {
        return provEstado;
    }

    public void setProvEstado(Integer provEstado) {
        this.provEstado = provEstado;
    }

    @XmlTransient
    public Collection<PproDocumento> getPproDocumentoCollection() {
        return pproDocumentoCollection;
    }

    public void setPproDocumentoCollection(Collection<PproDocumento> pproDocumentoCollection) {
        this.pproDocumentoCollection = pproDocumentoCollection;
    }


    public PproPersona getProvPerId() {
        return provPerId;
    }

    public void setProvPerId(PproPersona provPerId) {
        this.provPerId = provPerId;
    }

    public PproTipoProveedor getProvTiproId() {
        return provTiproId;
    }

    public void setProvTiproId(PproTipoProveedor provTiproId) {
        this.provTiproId = provTiproId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PproProveedor)) {
            return false;
        }
        PproProveedor other = (PproProveedor) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ppro.modelo.PproProveedor[ provId=" + provId + " ]";
    }

    public String getProvNCuenta() {
        return provNCuenta;
    }

    public void setProvNCuenta(String provNCuenta) {
        this.provNCuenta = provNCuenta;
    }

    public PproEntidadFinanciera getProvEntFinanciera() {
        return provEntFinanciera;
    }

    public void setProvEntFinanciera(PproEntidadFinanciera provEntFinanciera) {
        this.provEntFinanciera = provEntFinanciera;
    }

    public String getProvEmail() {
        return provEmail;
    }

    public void setProvEmail(String provEmail) {
        this.provEmail = provEmail;
    }
    
}
