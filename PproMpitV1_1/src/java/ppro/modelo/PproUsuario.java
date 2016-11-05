/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author casa
 */
@Entity
@Table(name = "ppro_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PproUsuario.findAll", query = "SELECT p FROM PproUsuario p"),
    @NamedQuery(name = "PproUsuario.findByUsuId", query = "SELECT p FROM PproUsuario p WHERE p.usuId = :usuId"),
    @NamedQuery(name = "PproUsuario.findByUsuUsername", query = "SELECT p FROM PproUsuario p WHERE p.usuUsername = :usuUsername"),
    @NamedQuery(name = "PproUsuario.findByUsuPass", query = "SELECT p FROM PproUsuario p WHERE p.usuPass = :usuPass"),
    @NamedQuery(name = "PproUsuario.findByUsuEstado", query = "SELECT p FROM PproUsuario p WHERE p.usuEstado = :usuEstado"),
    @NamedQuery(name = "PproUsuario.findByUsuUsernameMd5", query = "SELECT p FROM PproUsuario p WHERE p.usuUsernameMd5 = :usuUsernameMd5"),
    @NamedQuery(name = "PproUsuario.login", query = "SELECT p FROM PproUsuario p INNER JOIN p.pproRelUsuarioPerfilCollection pp INNER JOIN pp.rupPerfId rup INNER JOIN p.usuPerId up WHERE p.usuUsername = :usuUsername AND p.usuPass = :usuPass")})
@ManagedBean
@SessionScoped
public class PproUsuario implements Serializable {

    @Size(max = 255)
    @Column(name = "usu_username_md5")
    private String usuUsernameMd5;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Size(max = 255)
    @Column(name = "usu_username")
    private String usuUsername;
    @Size(max = 255)
    @Column(name = "usu_pass")
    private String usuPass;
    @Column(name = "usu_estado")
    private Integer usuEstado;
    @ManagedProperty(value = "#{usuPerId}")
    @JoinColumn(name = "usu_per_id", referencedColumnName = "per_id")
    @ManyToOne
    private PproPersona usuPerId;
    @OneToMany(mappedBy = "docUsuIngresa")
    private Collection<PproDocumento> pproDocumentoCollection;
    @OneToMany(mappedBy = "docUsuAutoriza")
    private Collection<PproDocumento> pproDocumentoCollection1;
    @OneToMany(mappedBy = "docUsuModifica")
    private Collection<PproDocumento> pproDocumentoCollection2;
    @OneToMany(mappedBy = "rupUsuId")
    private Collection<PproRelUsuarioPerfil> pproRelUsuarioPerfilCollection;
    
    
    
    public PproUsuario() {
    }

    public PproUsuario(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuUsername() {
        return usuUsername;
    }

    public void setUsuUsername(String usuUsername) {
        this.usuUsername = usuUsername;
    }

    public String getUsuPass() {
        return usuPass;
    }

    public void setUsuPass(String usuPass) {
        this.usuPass = usuPass;
    }

    public Integer getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(Integer usuEstado) {
        this.usuEstado = usuEstado;
    }

    public PproPersona getUsuPerId() {
        return usuPerId;
    }

    public void setUsuPerId(PproPersona usuPerId) {
        this.usuPerId = usuPerId;
    }

    @XmlTransient
    public Collection<PproDocumento> getPproDocumentoCollection() {
        return pproDocumentoCollection;
    }

    public void setPproDocumentoCollection(Collection<PproDocumento> pproDocumentoCollection) {
        this.pproDocumentoCollection = pproDocumentoCollection;
    }

    @XmlTransient
    public Collection<PproDocumento> getPproDocumentoCollection1() {
        return pproDocumentoCollection1;
    }

    public void setPproDocumentoCollection1(Collection<PproDocumento> pproDocumentoCollection1) {
        this.pproDocumentoCollection1 = pproDocumentoCollection1;
    }

    @XmlTransient
    public Collection<PproDocumento> getPproDocumentoCollection2() {
        return pproDocumentoCollection2;
    }

    public void setPproDocumentoCollection2(Collection<PproDocumento> pproDocumentoCollection2) {
        this.pproDocumentoCollection2 = pproDocumentoCollection2;
    }

    @XmlTransient
    public Collection<PproRelUsuarioPerfil> getPproRelUsuarioPerfilCollection() {
        return pproRelUsuarioPerfilCollection;
    }

    public void setPproRelUsuarioPerfilCollection(Collection<PproRelUsuarioPerfil> pproRelUsuarioPerfilCollection) {
        this.pproRelUsuarioPerfilCollection = pproRelUsuarioPerfilCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PproUsuario)) {
            return false;
        }
        PproUsuario other = (PproUsuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ppro.modelo.PproUsuario[ usuId=" + usuId + " ]";
    }

    public String getUsuUsernameMd5() {
        return usuUsernameMd5;
    }

    public void setUsuUsernameMd5(String usuUsernameMd5) {
        this.usuUsernameMd5 = usuUsernameMd5;
    }

   
   
    
}
